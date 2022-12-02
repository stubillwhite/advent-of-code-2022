(ns advent-of-code-2022.day-1
  (:require [advent-of-code-2022.utils :refer [parse-long sum]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-1-input.txt"))))

(defn- parse-elf-load [s]
  (->> s
       (string/split-lines)
       (map parse-long)
       (into [])))

(defn- parse-input [input]
  (->> (string/split input #"\n\n")
       (map parse-elf-load)
       (into [])))

(defn solution-part-one [input]
  (->> (parse-input input)
       (map sum)
       (apply max)))

;; Part two

(defn solution-part-two [input]
  (->> (parse-input input)
       (map sum)
       (sort >)
       (take 3)
       (sum)))
