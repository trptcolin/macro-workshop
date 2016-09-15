(ns macro-workshop.demo.00-what-are-macros)

(comment

  ;; what is a macro?


  ;; a macro is [just] a function that:
  ;; - runs at compile time
  ;; - takes code as input
  ;; - produces code as output
  ;; - and that code is evaluated at runtime


  ;; there are lots of reasons to use macros! a few examples:

  ;; - to evaluate code in a different context (bindings! guaranteed cleanup!)
  ;; - for performance (the fastest code at runtime is no code!)
  ;; - to eliminate nonessential code (easy-to-read APIs!)
  ;; - to build new control flow structures (loops! delimited continuations!)
  ;; - to add new language features (pattern matching! goroutines!)


  ;; BUT: this workshop is more tactical than strategic

  ;; so we'll dig deep into:
  ;; - how to write functions that generate code
  ;; - how to do ^^^ concisely and clearly
  ;; - tricky issues to watch out for & how to avoid them


  ;; interesting topics we *won't* have time to dig into:
  ;; - details of big macros like core.async / core.match
  ;; - all the use cases for macros


  ;; remember: a macro is a function that:
  ;; - runs at compile time
  ;; - takes code as input
  ;; - produces code as output
  ;; - and that code is evaluated at runtime


  ;; initial approximation of the difference between macros &
  ;; functions: evaluation order

  (when (= 1 2)
    (println "math doesn't work"))

  ;; let's try a simple version, skipping the possibility of varargs
  (defn when-fn [test then-expr]
    (if test then-expr))

  (when-fn (= 1 2)
    (println "math doesn't work"))

  (defn when-fn' [test then-expr]
    (println "top of when-fn")
    (if test then-expr)
    (println "bottom of when-fn"))

  (when-fn' (= 1 2)
    (println "math doesn't work"))

  (when (= 1 2)
    (println "math doesn't work"))

  ;; (special forms have this power too!)
  (if (= 1 2)
    (println "math doesn't work")
    (println "math works"))


  ;; why do macros have this super-power?
  ;; why can they control evaluation order?
  )
