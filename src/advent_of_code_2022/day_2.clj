(ns advent-of-code-2022.day-2
  (:require [advent-of-code-2022.utils :refer [parse-long sum]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-2-input.txt"))))

(defn- parse-line [s]
  (->> (string/split s #" ")
       (into [])))

(defn- parse-input [input]
  (->> (string/split input #"\n")
       (map parse-line)
       (into [])))

(defn- basic-decoder [line]
  (let [letter-to-move {"A" :rock "B" :paper "C" :scissors "X" :rock "Y" :paper "Z" :scissors}]
    (map letter-to-move line)))

(defn- round-outcome [my-move opponent-move]
  (let [loses-to {:rock     :scissors
                  :paper    :rock
                  :scissors :paper}]
    (cond
      (= my-move opponent-move)            :draw
      (= my-move (loses-to opponent-move)) :loss
      :else                                :win)))

(defn- score-round [decoder line]
  (let [move-score    {:rock 1 :paper 2 :scissors 3}
        outcome-score {:loss 0 :draw  3 :win      6}
        [opponent-move my-move] (decoder line)]
    (+ (move-score my-move) (outcome-score (round-outcome my-move opponent-move)))))

(defn solution-part-one [input]
  (->> (parse-input input)
       (map (partial score-round basic-decoder))
       (sum)))

;; Part two

(defn- find-first [pred coll]
  (first (filter pred coll)))

(defn- advanced-decoder [line]
  (let [letter-to-move          {"A" :rock "B" :paper "C" :scissors "X" :loss "Y" :draw "Z" :win}
        [opponent-move outcome] (map letter-to-move line)
        my-move                 (->> [:rock :paper :scissors]
                                     (find-first (fn [x] (= (round-outcome x opponent-move) outcome))))]
    [opponent-move my-move]))

(defn solution-part-two [input]
  (->> (parse-input input)
       (map (partial score-round advanced-decoder))
       (sum)))



