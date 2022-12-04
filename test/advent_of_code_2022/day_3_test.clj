(ns advent-of-code-2022.day-3-test
  (:require
   [advent-of-code-2022.day-3 :refer :all]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input
  (string/join "\n"
               ["vJrwpWtwJgWrhcsFMMfFFhFp"
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                "PmmdzqPrVvPwwTWBwg"
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                "ttgJtRGJQctTZtZT"
                "CrZsJsPPZsGzwwsLwLmpwMDw"
                ]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 157 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 7716 (solution-part-one problem-input))))
