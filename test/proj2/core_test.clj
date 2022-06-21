(ns proj2.core-test
  (:require [clojure.test :refer :all]
            [proj2.core :refer :all]))

(deftest greeting-test
  (is (= "hell, world" (greeting))))

(deftest make-thingy-test
  (let [n (rand-int 30)
        f (make-thingy n)]
    (println n)
    (is (= n (f)))
    (is (= n (f 123)))
    (is (= n (apply f 123 (range))))))

(deftest http-get-test
  (is (.contains (http-get "https://www.w3.org") "html")))
