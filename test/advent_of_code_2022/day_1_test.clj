(ns advent-of-code-2022.day-1-test
  (:require [advent-of-code-2022.day-1 :refer :all]
            [advent-of-code-2022.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def- example-input
  (string/join "\n" ["1000"
                     "2000"
                     "3000"
                     ""
                     "4000"
                     ""
                     "5000"
                     "6000"
                     ""
                     "7000"
                     "8000"
                     "9000"
                     ""
                     "10000"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 24000 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 75501 (solution-part-one problem-input))))
