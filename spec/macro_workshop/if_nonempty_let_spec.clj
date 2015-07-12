(ns macro-workshop.if-nonempty-let-spec
  (:require [macro-workshop.exercise-macros :as macros]
            [speclj.core :refer :all]))

(describe "if-nonempty-let"
  (it "takes the non-empty clause when the binding is non-empty"
    (pending)
    (should= [:non-empty [0 1 2 3 4]]
             (macros/if-nonempty-let [xs (range 5)]
                                     [:non-empty xs]
                                     :empty)))

  (it "takes the empty clause when the binding is empty"
    (pending)
    (should= :empty
             (macros/if-nonempty-let [xs []]
                              [:non-empty xs]
                              :empty)))

  (it "takes the non-empty clause for strings"
    (pending)
    (should= "ohai there"
             (macros/if-nonempty-let [x "ohai"] (str x " there") :else)))

  (it "takes the empty clause for an empty string"
    (pending)
    (should= :else
             (macros/if-nonempty-let [x ""] x :else))))


