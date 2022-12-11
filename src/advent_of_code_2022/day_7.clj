(ns advent-of-code-2022.day-7
  (:require
   [advent-of-code-2022.utils :refer [parse-long sum]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-7-input.txt"))))

(defn- is-command? [s]
  (string/starts-with? s "$"))

(defn- parse-command [s]
  (let [[_ cmd & args] (string/split s #" ")]
    {:cmd cmd
     :arg (first args)}))

(defn- parse-output-line [s]
  (let [[val name] (string/split s #" ")]
    (if (= val "dir")
      {:type :dir  :name name}
      {:type :file :name name :size (parse-long val)})))

(defn- parse-output [lines]
  {:output (->> lines
                (map parse-output-line)
                (into []))})

(defn- parse-command-and-output [lines]
  (let [cmd    (parse-command (first lines))
        output (parse-output (rest lines))]
    (merge cmd output)))

(defn- subseq-by
  "Returns coll split into subsequences, splitting into a new subsequence each time pred returns true."
  [pred coll]
  (when (seq coll)
    (lazy-cat
     [(cons (first coll) (take-while (complement pred) (rest coll)))]
     (subseq-by pred (drop-while (complement pred) (rest coll))))))

(defn parse-input [input]
  (->> (string/split input #"\n")
       (subseq-by is-command?)
       (map parse-command-and-output)))

;; Assumes that directories are visited in a top-down breadth-first order. This isn't stated in the problem, but the
;; example and problem input satify these assumptions and they make the solution a lot simpler.

(defn build-dir-sizes [cmds-and-output]
  (let [result (reduce
                (fn [{:keys [dir-sizes dir-stack] :as state} {:keys [cmd arg output]}]
                  (case cmd
                    "cd" (update state :dir-stack (if (= arg "..") rest (partial cons arg)))
                    "ls" (assoc-in state [:dir-sizes (string/join "/" (reverse dir-stack))]
                                   (let [{files :file dirs :dir} (group-by :type output)]
                                     {:sub-dirs   (map :name dirs)
                                      :file-sizes (->> files (map :size) (sum))}))))
                {:dir-sizes {} :dir-stack nil}
                cmds-and-output)]
    (:dir-sizes result)))

(defn total-dir-size [dir-sizes name]
  (let [file-sizes   (get-in dir-sizes [name :file-sizes])
        sub-dirs     (get-in dir-sizes [name :sub-dirs])
        abs-sub-dirs (map (fn [s] (str name "/" s)) sub-dirs)
        dir-sizes    (sum (map (partial total-dir-size dir-sizes) abs-sub-dirs))]
    (+ file-sizes dir-sizes)))

(defn solution-part-one [input]
  (let [cmds-and-output (parse-input input)
        dir-sizes       (build-dir-sizes cmds-and-output)]
    (->> (keys dir-sizes)
         (map (partial total-dir-size dir-sizes))
         (filter (fn [x] (<= x 100000)))
         (sum))))

;; Part two

(defn solution-part-two [input]
  (let [cmds-and-output (parse-input input)
        dir-sizes       (build-dir-sizes cmds-and-output)
        total-used      (total-dir-size dir-sizes "/")
        min-to-delete   (- total-used (- 70000000 30000000))]
    (->> (keys dir-sizes)
         (map (partial total-dir-size dir-sizes))
         (filter (fn [x] (>= x min-to-delete)))
         (sort)
         (first))))

