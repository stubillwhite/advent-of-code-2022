(ns advent-of-code-2022.day-8
  (:require
   [advent-of-code-2022.utils :refer [parse-long sum]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-8-input.txt"))))

(defn- transpose [input]
  (->> input
       (apply interleave)
       (partition (count (first input)))
       (map (partial into []))
       (into [])))

(defn parse-input [input]
  (->> (string/split input #"\n")
       (map (fn [s] (->> (seq s) (map (comp parse-long str)))))
       (transpose)))

(defn- coordinates [grid]
  (let [width  (count (first grid))
        height (count grid)]
    (for [y (range 0 height) x (range 0 width)] [x y])))

(defn- sightlines-through [grid]
  (let [width  (count (first grid))
        height (count grid)
        east   (partition width (coordinates grid))
        south  (transpose east)
        west   (map reverse east)
        north  (map reverse south)]
    {:east east :south south :west west :north north}))

(defn- map-over-sightlines [grid f sightlines]
  (let [get-grid-values (partial get-in grid)]
    (apply merge
           (for [sightline sightlines]
             (zipmap sightline (f (map get-grid-values sightline)))))))

(defn- map-vals [f coll]
  (into {} (for [[k v] coll] [k (f v)])))

(defn- filter-map [pred coll]
  (into {} (for [[k v] coll :when (pred k v)] [k v])))

(defn- map-over-sightlines-through-grid [grid f sightlines-through-grid]
  (map-vals (partial map-over-sightlines grid f) sightlines-through-grid))

(defn- visible-in-sightline [line]
  (:visible
   (reduce (fn [{:keys [visible min-height]} height]
            (if (> height min-height)
              {:visible (conj visible true)  :min-height height}
              {:visible (conj visible false) :min-height min-height}))
          {:visible [] :min-height -1}
          line)))

(defn- merge-views-with [f views]
  (let [coords               (->> views (vals) (first) (keys))
        dirs                 (keys views)
        get-values-for-coord (fn [coord] (for [dir dirs] (get-in views [dir coord])))]
    (into {}
          (for [coord coords]
            [coord (f (get-values-for-coord coord))]))))

(defn solution-part-one [input]
  (let [grid                 (parse-input input)
        sightlines           (sightlines-through grid)
        visible-by-dir       (map-over-sightlines-through-grid grid visible-in-sightline sightlines)
        visible-from-any-dir (merge-views-with (fn [vals] (if (some true? vals) true false)) visible-by-dir)]
    (->> visible-from-any-dir
         (filter-map (fn [k v] (true? v)))
         (keys)
         (count))))

;; Part two

(defn- viewing-distance-in-sightline [line]
  (let [rev-line      (reverse line)
        calc-distance (fn [height heights]
                        (reduce (fn [dist x] (if (> height x) (inc dist) (reduced (inc dist))))
                                0
                                heights))]
    (:distances
     (reduce (fn [{:keys [distances heights] :as acc} height]
               {:heights   (cons height heights)
                :distances (if (> height (first heights))
                             (cons (calc-distance height heights) distances)
                             (cons 1 distances))})
             {:heights   [(first rev-line)]
              :distances [0]}
             (rest rev-line)))))

(defn solution-part-two [input]
  (let [grid                  (parse-input input)
        sightlines            (sightlines-through grid)
        view-distances-by-dir (map-over-sightlines-through-grid grid viewing-distance-in-sightline sightlines)
        scenic-scores         (merge-views-with (fn [vals] (apply * vals)) view-distances-by-dir)]
    (->> scenic-scores
         (vals)
         (apply max))))
