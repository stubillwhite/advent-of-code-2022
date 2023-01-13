(ns advent-of-code-2022.day-8-test
  (:require
   [advent-of-code-2022.day-8 :refer :all]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input
  (string/join "\n" ["30373"
                     "25512"
                     "65332"
                     "33549"
                     "35390"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 21 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 1794 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 8 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 199272 (solution-part-two problem-input))))

