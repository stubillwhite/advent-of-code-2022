(ns advent-of-code-2022.day-7-test
  (:require
   [advent-of-code-2022.day-7 :refer :all]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input
  (string/join "\n"
               ["$ cd /"
                "$ ls"
                "dir a"
                "14848514 b.txt"
                "8504156 c.dat"
                "dir d"
                "$ cd a"
                "$ ls"
                "dir e"
                "29116 f"
                "2557 g"
                "62596 h.lst"
                "$ cd e"
                "$ ls"
                "584 i"
                "$ cd .."
                "$ cd .."
                "$ cd d"
                "$ ls"
                "4060174 j"
                "8033020 d.log"
                "5626152 d.ext"
                "7214296 k"]))

(deftest build-dir-sizes-given-example-input
  (let [dir-sizes (build-dir-sizes (parse-input example-input))
        get-size  (partial total-dir-size dir-sizes)]
    (is (= 584      (get-size "//a/e")))
    (is (= 94853    (get-size "//a")))
    (is (= 24933642 (get-size "//d")))
    (is (= 48381165 (get-size "/")))))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 95437 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 1723892 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 24933642 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 8474158 (solution-part-two problem-input))))
