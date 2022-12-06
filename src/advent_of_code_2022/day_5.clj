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
         (map reverse)
         (map (partial take-while identity))
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

(defn- execute-instruction [configuration {:keys [count from to]}]
  (let [[from-idx to-idx] (map dec [from to])]
    (-> configuration
        (assoc-in [to-idx]   (concat (configuration to-idx) (take count (reverse (configuration from-idx)))))
        (assoc-in [from-idx] (drop-last count (configuration from-idx))))))

(defn- read-top-crates [configuration]
  (string/join "" (map last configuration)))

(defn solution-part-one [input]
  (let [{:keys [configuration instructions]} (parse-input input)]
    (read-top-crates (reduce execute-instruction configuration instructions))))




