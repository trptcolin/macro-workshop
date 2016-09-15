(ns macro-workshop.demo.01-generating-code)

(comment

  ;; a macro is [just] a function that:
  ;; - runs at compile time
  ;; - takes code as input
  ;; - produces code as output
  ;; - and that code is evaluated at runtime

  ;; we can get almost *all* the macro skills we need without ever writing
  ;; `defmacro`. you'll see how trivial it is to go from code generation to
  ;; macros in one of the exercises here in a bit.


  ;; a bit of clojure code
  (+ 1 2 3)

  ;; another bit of clojure code
  ;; ... which, when eval'ed, produces clojure code
  (list '+ 1 2 3)

  (quote (+ 1 2 3))

  ;; back to the code interpretation...
  (eval (quote (+ 1 2 3)))

  ;; a shorthand for `quote`
  '(+ a b c)


  ;; quoting applies to each element
  (assert (= (list '+ 'a 'b 'c)
             '(+ a b c)))

  ;; and it's recursive for nested elements too!
  '(+ 1 (+ x y) (* 3 4))

  ;; undefined symbols
  (eval '(+ 1 (+ x y) (* 3 4))) ;; boom!

  ;; so let's define them
  (def x 1)
  (def y 2)

  ;; let's try this again...
  (eval '(+ 1 (+ x y) (* 3 4)))

  ;; [just in case we want to go back to the state where x & y are undefined]
  (ns-unmap *ns* 'x)
  (ns-unmap *ns* 'y)


  ;; how does this expression-building relate to macros?
  ;; macros produce code as output, and that code gets eval'ed.


  ;; macroexpand-1 says: just run the macro function - don't `eval` the result
  ;; it lets you see the output (code) from the macro function's execution
  (macroexpand-1 '(if-not false :oops))
  (macroexpand-1 (macroexpand-1 '(if-not false :oops)))

  ;; just keep expanding until the "verb" isn't a macro
  (macroexpand '(if-not false :oops))

  ;; running just the macroexpander can be useful to look at the generated code
  ;; vs. expanding and evaluating it all at once
  (if-not false :oops)
  (macroexpand '(if-not false :oops))
  )

;; on to the first exercise set: doc/exercises/01_generating_code.md
;; have fun!





















(def / *)
