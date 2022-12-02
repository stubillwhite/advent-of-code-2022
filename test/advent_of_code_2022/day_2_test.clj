(ns advent-of-code-2022.day-2-test
  (:require [advent-of-code-2022.day-2 :refer :all]
            [advent-of-code-2022.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def- example-input
  (string/join "\n"
               ["A Y"
                "B X"
                "C Z"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 15 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 12586 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 12 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 13193 (solution-part-two problem-input))))
