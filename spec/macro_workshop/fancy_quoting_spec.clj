(ns macro-workshop.fancy-quoting-spec
  (:require [clojure.repl :as repl]
            [clojure.string :as string]
            [macro-workshop.fancy-quoting :as fancy]
            [speclj.core :refer :all]))

(describe "fancy-quoting"
  (describe "math-operations-set"
    (it "uses core-namespaced symbols"
      (pending)
      (should= #{'clojure.core/+
                 'clojure.core/-
                 'clojure.core/*
                 'clojure.core//}
               (fancy/math-operations-set)))

    (it "uses syntax-quote exactly once"
      (pending)
      (should= ["`"]
               (re-seq #"`"
                       (-> fancy/math-operations-set
                           repl/source
                           with-out-str)))))

  (describe "maybe-generator"
    (it "generates a None when given 0 arguments"
      (pending)
      (should= {:type `fancy/None} (fancy/construct-maybe)))

    (it "generates a Some when given 1 argument"
      (pending)
      (should= {:type `fancy/Some
                :value "hi"}
               (fancy/construct-maybe "hi")))))
