(ns advent-of-code-2022.day-5-test
  (:require
   [advent-of-code-2022.day-5 :refer :all]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input
  (string/join "\n"
               ["    [D]    "
                "[N] [C]    "
                "[Z] [M] [P]"
                " 1   2   3 "
                ""
                "move 1 from 2 to 1"
                "move 3 from 1 to 3"
                "move 2 from 2 to 1"
                "move 1 from 1 to 2"]))

(deftest parse-input-given-example-input-then-parses-correctly
  (let [expected {:configuration [["N" "Z"]
                                  ["D" "C" "M"]
                                  ["P"]]
                  :instructions  [{:count 1 :from 2 :to 1}
                                  {:count 3 :from 1 :to 3}
                                  {:count 2 :from 2 :to 1}
                                  {:count 1 :from 1 :to 2}]}]
    (is (= expected (parse-input example-input)))))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= "CMZ" (solution-part-one example-input))))

(comment deftest solution-part-one-given-problem-input-then-correct-result
  (is (= "QPJPLMNNR" (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= "MCD" (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= "BQDNWJPVJ" (solution-part-two problem-input))))
