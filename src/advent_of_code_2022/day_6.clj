(ns advent-of-code-2022.day-6
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-6-input.txt"))))

(defn- is-marker? [xs]
  (= (count xs) (count (into #{} xs))))

(defn- find-first [pred coll]
  (first (filter pred coll)))

(defn- end-of-marker-index [s marker]
  (+ (.indexOf s (apply str marker)) (count marker)))

(defn solution-part-one [input]
  (->> input
       (partition 4 1)
       (find-first is-marker?)
       (end-of-marker-index input)))
