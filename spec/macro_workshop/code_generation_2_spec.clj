(ns macro-workshop.code-generation-2-spec
  (:require [macro-workshop.code-generation-2 :as code]
            [speclj.core :refer :all]))

(describe "code-generation-2"
  (describe "generate-addition"
    (it "generates an addition expression"
      (pending)
      (should= 3 (code/add 1 2))))

  (describe "generate-multiplication"
    (it "generates a multiplication expression"
      (pending)
      (should= 12 (code/multiply 3 4))))

  (describe "generate-squarer"
    (it "generates a squaring function"
      (pending)
      (should= 81 ((code/make-squarer)
                   9))))

  (describe "generate-hello-world-definition"
    (it "generates a Hello World function"
      (pending)
      (should= "Hello World!\n"
               (let [hello-world-fn (code/make-hello-world)]
                 (with-out-str (hello-world-fn)))))))
