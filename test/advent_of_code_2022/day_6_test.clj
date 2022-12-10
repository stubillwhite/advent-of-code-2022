(ns advent-of-code-2022.day-6-test
  (:require
   [advent-of-code-2022.day-6 :refer :all]
   [clojure.test :refer :all]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 7  (solution-part-one "mjqjpqmgbljsphdztnvjfqwrcgsmlb")))
  (is (= 5  (solution-part-one "bvwbjplbgvbhsrlpgdmjqwftvncz")))
  (is (= 6  (solution-part-one "nppdvjthqldpwncqszvftbrmjlhg")))
  (is (= 10 (solution-part-one "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")))
  (is (= 11 (solution-part-one "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))))


