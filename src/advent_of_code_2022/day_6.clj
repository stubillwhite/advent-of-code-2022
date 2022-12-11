(ns advent-of-code-2022.day-6
  (:require
   [advent-of-code-2022.utils :refer [find-first]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-6-input.txt"))))

(defn- is-marker? [xs]
  (= (count xs) (count (into #{} xs))))

(defn- end-of-marker-index [s marker]
  (+ (.indexOf s (apply str marker)) (count marker)))

(defn- characters-before-message [window-size input]
  (->> input
       (partition window-size 1)
       (find-first is-marker?)
       (end-of-marker-index input)))

(defn solution-part-one [input]
  (characters-before-message 4 input))

;; Part two

(defn solution-part-two [input]
  (characters-before-message 14 input))
