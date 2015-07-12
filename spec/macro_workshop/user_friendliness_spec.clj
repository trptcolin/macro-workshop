(ns macro-workshop.user-friendliness-spec
  (:require [macro-workshop.exercise-macros :as macros]
            [speclj.core :refer :all]))

;; NOTE: Once you've made these tests pass, see what happens if we just call
;; the macro directly in the tests instead of quoting and using `eval`.
;; Why does that happen?

(describe "user-friendliness of if-nonempty-let"
  (it "provides a nice error message for bindings that don't look like bindings"
    (pending)
    (try
      (eval `(macros/if-nonempty-let x# :then-branch :else-branch))
      (should= true false) ;; shouldn't get here
      (catch AssertionError e
        (should (re-seq #"if-nonempty-let requires a name/value vector binding"
                        (.getMessage e))))))

  (it "provides a nice error message for bindings that "
    (pending)
    (try
      (eval `(macros/if-nonempty-let [x# 1] :then-branch :else-branch))
      (should= true false) ;; shouldn't get here
      (catch AssertionError e
        (should (re-seq #"if-nonempty-let requires bound values to be seqable"
                        (.getMessage e)))))))
