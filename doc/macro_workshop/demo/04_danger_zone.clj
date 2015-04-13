(ns macro-workshop.demo.04-danger-zone
  (:require [clojure.string :as string]))

(comment
  ;; there are actually several problems with this tiny macro
  (defmacro square [x]
    `(* ~x ~x))

  (let [x 1]
    (square 3))

  (square 5)

  (square (do (println "hi") 5)) ;; why?

  ;; we can fix this by only including the original code once
  (defmacro square [x]
    `(let [x# ~x]
       (* x# x#)))

  (square (do (println "hi") 5))

  ;; say it with me: "the inputs to a macro are *code*"

  (map square (range 5)) ;; aw man!

  ;; if you're like me, you may have tried...
  (map (partial #'square) (range 5))
  ;; ^^ wha? ^^

  (map (partial #'square {} {}) (range 5))

  (last (map (partial #'square {} {}) (range 5)))

  (map (comp eval (partial #'square {} {})) (range 5))

  ;; but that was silly. we can do much much better:
  (map #(square %) (range 5))
  (map #(+ % %) (range 5))

  (map square (range 5))
  ;; why did that work?


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

  ;; so we had to write a macro in order to use the underlying macro.
  ;; this is common.
  )

;; exercise time! doc/exercises/04_danger_zone.md


