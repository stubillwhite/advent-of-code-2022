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

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 1757 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 19 (solution-part-two "mjqjpqmgbljsphdztnvjfqwrcgsmlb")))
  (is (= 23 (solution-part-two "bvwbjplbgvbhsrlpgdmjqwftvncz")))
  (is (= 23 (solution-part-two "nppdvjthqldpwncqszvftbrmjlhg")))
  (is (= 29 (solution-part-two "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")))
  (is (= 26 (solution-part-two "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 2950 (solution-part-two problem-input))))
