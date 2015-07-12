(ns macro-workshop.demo.03-symbol-capture)

(comment

  ;; we've seen two ways to build functions
  (defmacro make-adder-verbose [x]
    (list 'fn '[y] (list '+ x 'y)))

  (defmacro make-adder [x]
    `(fn [~'y] (+ ~x ~'y)))

  ;; there's only a very slight difference we know about, which affects people
  ;; overriding clojure.core vars - and those people are being naughty anyway,
  ;; right?
  (macroexpand-1 '(make-adder-verbose 5))
  (macroexpand-1 '(make-adder 5))

  ;; this works as expected
  (assert (= 14 ((make-adder 4) 10)))

  ;; ok, let's say 4 comes from somewhere else; we'll just extract a local:
  (assert (= 14
             (let [y 4]
               ((make-adder y) 10))))

  (let [y 4]
    ((make-adder y) 10))

  (macroexpand-1 '(make-adder 4))
  (macroexpand-1 '(make-adder y)) ;; symbol *captured*!


  ;; we could pick an obscure symbol name that probably won't be captured, like
  ;; first-argument-to-make-adder, but there's a better and surer way to fix
  ;; the problem: gensym!
  (gensym)
  (gensym "prefix-can-go-here_")

  (defmacro make-adder-with-gensym [x]
    (let [y (gensym)]
      `(fn [~y] (+ ~x ~y))))

  (assert (= 14
             (let [y 4]
               ((make-adder-with-gensym y) 10))))
  ;; why? find out:
  (macroexpand-1 '(make-adder-with-gensym y))

  ;; we can make it more concise and avoid lots of bindings around expressions
  ;; that we're generating
  (defmacro make-adder-with-auto-gensym [x]
    `(fn [y#] (+ ~x y#)))

  (assert (= 14
             (let [y 4]
               ((make-adder-with-auto-gensym y) 10))))
  (macroexpand-1 '(make-adder-with-auto-gensym y))

  ;; syntax-quote gives you:
  ;; 3. auto-gensyms

  ;; the auto-gensym is guaranteed-unique, like normal gensym, and the same for
  ;; every instance in the syntax-quote
  `(x# x# x#)
  (apply = `(x# x# x#))

  ;; but outside the syntax-quote, the auto-gensym is different
  (= (first `(x#))
     (first `(x#)))

  ;; same for being lexically nested, but semantically separate syntax-quote
  ;; expressions (with an unquote intervening)
  (apply = `(x# ~(first `(x#))))

  ;; if your macro has nested syntax-quotes (i really, really hope it doesn't
  ;; come to that), then you may be interested in
  ;; https://github.com/ztellman/potemkin#unify-gensyms as a way to, well,
  ;; unify your gensyms across multiple syntax-quote expressions.
  )

;; time for the next exercise set! doc/exercises/03_symbol_capture.md

