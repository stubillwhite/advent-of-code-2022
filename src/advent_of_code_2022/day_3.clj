(ns advent-of-code-2022.day-3
  (:require
   [advent-of-code-2022.utils :refer [sum]]
   [clojure.java.io :as io]
   [clojure.set :as set]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-3-input.txt"))))

(defn- parse-rucksack [s]
  (->> s
       (split-at (/ (count s) 2))
       (map string/join)
       (into [])))

(defn- parse-input [input]
  (->> (string/split input #"\n")
       (map parse-rucksack)
       (into [])))

(defn- common-item [contents]
  (->> contents
       (map (partial into #{}))
       (apply set/intersection)
       (first)))

(defn- is-lower-case? [ch]
  (= (string/lower-case ch) ch))

(defn- priority [ch]
  (if (is-lower-case? (str ch))
    (- (int ch) 96)
    (- (int ch) 38)))

(defn solution-part-one [input]
  (->> (parse-input input)
       (map common-item)
       (map priority)
       (sum)))


