(ns advent-of-code-2022.day-4
  (:require
   [advent-of-code-2022.utils :refer [parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-4-input.txt"))))

(defn- parse-pair [s]
  (let [parse-assignment (fn [s] (->> (string/split s #"-")
                                     (map parse-long)
                                     (into [])))]
    (->> (string/split s #",")
         (map parse-assignment)
         (into []))))

(defn- parse-input [input]
  (->> (string/split input #"\n")
       (map parse-pair)
       (into [])))

(defn- fully-contains? [[r1 r2]]
  (let [[[s1 e1] [s2 e2]] [r1 r2]]
    (or (<= s1 s2 e2 e1)
        (<= s2 s1 e1 e2))))

(defn solution-part-one [input]
  (->> (parse-input input)
       (filter fully-contains?)
       (count)))

;; Part two

(defn- overlaps? [[r1 r2]]
  (let [[[s1 e1] [s2 e2]] [r1 r2]]
    (or (<= s1 s2 e1 e2)
        (<= s2 s1 e2 e1)
        (fully-contains? [r1 r2]))))

(defn solution-part-two [input]
  (->> (parse-input input)
       (filter overlaps?)
       (count)))
