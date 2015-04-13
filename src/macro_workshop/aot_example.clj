(ns macro-workshop.aot-example
  (:gen-class))

(defmacro foo []
  (println "This prints during macroexpansion.")
  `(do (println "This prints at runtime.")
       (+ 1 2)))

(defn -main [& args]
  (if (some #{"--expand"} args)
    (do (println "Running: (eval (macroexpand-1 '(foo)))")
        (eval (macroexpand-1 `(foo))))
    (do (println "Running: (foo)")
        (foo))))
