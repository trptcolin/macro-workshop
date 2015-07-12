(ns macro-workshop.code-generation-spec
  (:require [macro-workshop.code-generation :as code]
            [speclj.core :refer :all]))

(describe "code-generation"
  (describe "generate-addition"
    (it "generates an addition expression"
      (pending)
      (let [code (code/generate-addition 1 2)]
        (should (instance? clojure.lang.ISeq code))
        (should= 3 (eval code)))
      (should= 7 (eval (code/generate-addition 3 4)))))

  (describe "generate-multiplication"
    (it "generates a multiplication expression"
      (pending)
      (let [code (code/generate-multiplication 3 4)]
        (should (instance? clojure.lang.ISeq code))
        (should= 12 (eval code)))
      (should= 20 (eval (code/generate-multiplication 4 5)))))

  (describe "generate-squarer"
    (it "generates a squaring function"
      (pending)
      (let [code (code/generate-squarer)]
        (should (instance? clojure.lang.ISeq code))
        (should= 81 ((eval code) 9)))
      (should= 100 ((eval (code/generate-squarer)) 10))))

  (describe "generate-hello-world-definition"
    (it "generates a Hello World function"
      (pending)
      (let [code (code/generate-hello-world-definition)]
        (should (instance? clojure.lang.ISeq code))
        (should= "Hello World!\n"
                 (let [hello-world-fn (eval code)]
                   (with-out-str (hello-world-fn))))))))
