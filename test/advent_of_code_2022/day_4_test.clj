(ns advent-of-code-2022.day-4-test
  (:require
   [advent-of-code-2022.day-4 :refer :all]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input
  (string/join "\n"
               ["2-4,6-8"
                "2-3,4-5"
                "5-7,7-9"
                "2-8,3-7"
                "6-6,4-6"
                "2-6,4-8"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 2 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 453 (solution-part-one problem-input))))
