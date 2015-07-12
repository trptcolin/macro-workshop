(ns macro-workshop.secret-magic-spec
  (:require [macro-workshop.secret-magic :as secret]
            [speclj.core :refer :all]))

(describe "macro-workshop.secret-magic"
  (describe "local-env"
    (it "emits the locals active where the macro is expanded"
      (pending)
      (let [x 1
            y 2]
        (should= 1 (get (secret/local-env) 'x))
        (should= 2 (get (secret/local-env) 'y))))

    (it "doesn't include locals"
      (pending)
      (let [result (secret/local-env)]
        (should= nil (get result 'x))
        (should= nil (get result 'y))))))
