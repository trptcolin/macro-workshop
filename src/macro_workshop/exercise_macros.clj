(ns macro-workshop.exercise-macros)

(defmacro if-nonempty-let
  "Like if-let, but treats any empty collection (as via `empty?`) as false"
  [bindings then else]
  :replace-me)

