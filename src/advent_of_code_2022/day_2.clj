(ns advent-of-code-2022.day-2
  (:require [advent-of-code-2022.utils :refer [parse-long sum]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-2-input.txt"))))

(defn- parse-line [s]
  (let [letter-to-move {"A" :rock
                        "B" :paper
                        "C" :scissors
                        "X" :rock
                        "Y" :paper
                        "Z" :scissors}]
    (->> (string/split s #" ")
         (map letter-to-move)
         (into []))))

(defn- parse-input [input]
  (->> (string/split input #"\n")
       (map parse-line)
       (into [])))

(defn- round-outcome [my-move opponent-move]
  (let [loses-to {:rock     :scissors
                  :paper    :rock
                  :scissors :paper}]
    (cond
      (= my-move opponent-move)            :draw
      (= my-move (loses-to opponent-move)) :loss
      :else                                :win)))

(defn- score-round [[opponent-move my-move]]
  (let [move-score    {:rock     1
                       :paper    2
                       :scissors 3}
        outcome-score {:loss 0
                       :draw 3
                       :win  6}]
    (+ (move-score my-move) (outcome-score (round-outcome my-move opponent-move)))))

(defn solution-part-one [input]
  (->> (parse-input input)
       (map score-round)
       (sum)))
