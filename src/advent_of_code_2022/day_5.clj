(ns advent-of-code-2022.day-5
  (:require
   [advent-of-code-2022.utils :refer [parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-5-input.txt"))))

(defn parse-configuration [s]
  (let [parse-line   (fn [s] (map last (re-seq #"(   |\[(.)\]) ?" s)))
        lines        (string/split s #"\n")
        parsed-lines (map parse-line (drop-last lines))
        columns      (count (first parsed-lines))
        rows         (count parsed-lines)]
    (->> (apply interleave parsed-lines)
         (partition rows)
         (map (partial filter identity))
         (into []))))

(defn- parse-instruction-line [s]
  (->> (re-seq #"move (\d+) from (\d+) to (\d+)" s)
       (first)
       (rest)
       (map parse-long)
       (zipmap [:count :from :to])))

(defn- parse-instructions [s]
  (->> (string/split s #"\n")
       (map parse-instruction-line)))

(defn parse-input [input]
  (let [[configuration instructions] (string/split input #"\n\n")]
    {:configuration (parse-configuration configuration)
     :instructions  (parse-instructions instructions)}))

(defn- execute-instruction [crane configuration {:keys [count from to]}]
  (let [[from-idx to-idx] (map dec [from to])]
    (-> configuration
        (assoc-in [to-idx]   (crane count (configuration from-idx) (configuration to-idx)))
        (assoc-in [from-idx] (drop count (configuration from-idx))))))

(defn- read-top-crates [configuration]
  (string/join "" (map first configuration)))

(defn- reversing-crane [count from-stack to-stack]
  (concat (reverse (take count from-stack)) to-stack))

(defn solution-part-one [input]
  (let [{:keys [configuration instructions]} (parse-input input)
        executor (partial execute-instruction reversing-crane)]
    (read-top-crates (reduce executor configuration instructions))))

;; Part two

(defn- non-reversing-crane [count from-stack to-stack]
  (concat (take count from-stack) to-stack))

(defn solution-part-two [input]
  (let [{:keys [configuration instructions]} (parse-input input)
        executor (partial execute-instruction non-reversing-crane)]
    (read-top-crates (reduce executor configuration instructions))))
