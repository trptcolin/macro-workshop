(ns macro-workshop.demo.02-fancy-quoting
  (:require [clojure.string :as string]))

(comment

  (def x 1)
  (def y 2)
  (+ x y)
  (eval '(+ x y))

  ;; when we're generating code, the namespace where it gets generated is often
  ;; different from the place it gets used
  (do (in-ns 'macro-workshop.demo.01-generating-code)
      (eval '(+ x y))) ;; boom!

  (do (in-ns 'macro-workshop.demo.01-generating-code)
      (eval `(+ x y))) ;; how?! wha?

  ;; ^^^ what was the difference? ^^^

  ;; syntax-quote gives you:
  ;; 1. namespace qualification
  '(+ a b)
  `(+ a b)

  `(* 12 4)

  `(string/join "," [1 2 3])

  ;; you don't always get exceptions on code interpretation errors
  (do (in-ns 'macro-workshop.demo.01-generating-code)
      (eval `(/ 12 4)))

  (do (in-ns 'macro-workshop.demo.01-generating-code)
      (eval '(/ 12 4)))
  ;; ^^ why? ^^

  ;; syntax-quote encourages interpretation of generated code using the context
  ;; where it was written


  ;; now, another usage for syntax-quote...
  ;; this is already kind of verbose
  (let [x 3]
    (list '+ 1 (list '* 2 x)))

  ;; syntax-quote gives you:
  ;; 2. unquoting
  (let [x 3]
    `(+ 1 (* 2 ~x)))

  ;; syntax-quote encourages code-generating code to look similar to the code
  ;; it's generating

  ;; we can add up several things, but this is getting unwieldy:
  (let [a 0 b 1 c 2 d 3 e 4 f 5 g 6 h 7 i 8 j 9]
    `(+ ~a ~b ~c ~d ~e ~f ~g ~h ~i ~j))

  ;; let's use our sequence functions!
  (let [xs (range 10)]
    `(+ ~xs))

  ;; would that work?
  (eval (let [xs (range 10)]
          `(+ ~xs)))
  ;; ^^^ why? ^^^

  ;; would apply help?
  (eval (let [xs (range 10)]
          `(apply + ~xs)))
  ;; ^^^ why? ^^^

  ;; notice that it's way easier to see what's going wrong when we look at the
  ;; code that's generated, not at the error messages from eval!!!
  ;; the same thing applies for debugging macros (so use `macroexpand-1`!)

  ;; OK, so there's another tiny bit of syntax here:
  (let [xs (range 10)]
    `(+ ~@xs))

  ;; ...but does it work?
  (eval (let [xs (range 10)]
          `(+ ~@xs)))
  ;; ^^^ woohoo! ^^^

  ;; hot tip: you don't actually *need* the syntax in this case:
  (let [xs (range 10)
        xs-vec (vec xs)]
    `(apply + ~xs-vec))
  ;; ^^^ wrap it in an eval if you don't believe me ^^^

  ;; but ~@ (unquote-splicing) sure is useful when you do need it!
  ;; we'll see why soon, but you can't always count on `apply` to save you.

  ;; so you want to generate some bindings...
  ;; the code we want to generate:
  (let [x 1]
    (+ x 2))

  (eval `(let [x 1]
           (+ x 2)))

  ;; let's put together some pieces we already know
  (eval `(let [~'x 1]
           (+ ~'x 2)))

  ;; wait wtf is ~' ?? let's break it down:
  `(+ ~(quote x) 2)
  ;; ' <===> quote

  `(+ ~'x 2)

  `(let [~'x 1]
     (+ ~'x 2)))

;; so we have ~, which gives us an escape hatch to insert whatever code we want
;; inside a syntax-quote, and we have ', which lets us have non-namespaced
;; symbols. that gives us enough to generate bindings in a way that works well
;; enough for now. soon, we'll see other contexts where ~' can cause
;; problems.

;; now for the next exercise set! doc/exercises/02_fancy_quoting.md
