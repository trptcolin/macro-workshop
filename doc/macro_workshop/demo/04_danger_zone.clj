(ns macro-workshop.demo.04-danger-zone
  (:require [clojure.string :as string]))

(comment
  ;; there are actually several problems with this tiny macro
  (defmacro square [x]
    `(* ~x ~x))

  (let [x 3]
    (square x))

  (square 5)

  (square (do (println "hi") 5)) ;; why?

  (macroexpand-1 '(square (do (println "hi") 5)))

  ;; we can fix this by only including the original code 1x in the output code
  (defmacro square [x]
    `(let [x# ~x]
       (* x# x#)))

  (square (do (println "hi") 5))

  ;; say it with me: "the arguments to a macro are *code*"

  (map square (range 5)) ;; aw man!

  square

  #'square
  (type #'square)
  (deref #'square)
  @#'square

  ;; if you're like me, you may have tried something like...
  (map @#'square (range 5))
  ;; ^^ wha? ^^


  ;; ignore the {} for now - you'll get there in the BONUS ROUND!!!
  (#'square {} {} 5)
  (eval (#'square {} {} 5))

  (map (partial #'square {} {}) (range 5))

  (last (map (partial #'square {} {}) (range 5)))

  (map (comp eval (partial #'square {} {})) (range 5))

  ;; but that was silly. we can do much much better:
  (map (fn [x] (square x)) (range 5))
  ;; why did that work?

  (macroexpand-1 '(square x))

  ;; SOOO: macros aren't values! they are *kind* of functions, but you'll find
  ;; when working with macros how important functions-as-values are to Clojure
  ;; in particular and FP in general


  ;; let's say we're working with a macro with variable arity:
  (defmacro log [& args]
    `(println (str "[INFO] " (string/join " | " ~(vec args)))))
  ;; ... and we also haven't yet discovered the joys of structured logging so
  ;; we don't have to do custom parsing for every server we deploy to.

  (log "column one")
  (log "column one" "column two")
  (log "column one" "column two" "column three")
  ;; cool.

  ;; so how do we define `log-row` to make this work?
  (log-row ["column one" "column two" "column three"])

  (defn log-row [row]
    (apply log row)) ;; nooooope.

  (defmacro log-row [row]
    `(log ~@row)) ;; yep.

  (log-row ["column one" "column two" "column three"])

  ;; so we had to write a macro in order to use the underlying macro.
  ;; this is common.


  ;; but WAIT: but does log-row *really* work?!?
  (let [x ["column one" "column two" "column three"]]
    (log-row x))

  )
;; exercise time! doc/exercises/04_danger_zone.md
