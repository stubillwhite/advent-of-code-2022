(ns advent-of-code-2022.utils
  (:require [clojure.string :as string]))

(defmacro def-
  ([name & decls]
    (list* `def (with-meta name (assoc (meta name) :private true)) decls)))

(defmacro defmulti-
  [name & decls]
  (list* `defmulti (with-meta name (assoc (meta name) :private true)) decls))

(defmacro defmethod-
  [name & decls]
  (list* `defmethod (with-meta name (assoc (meta name) :private true)) decls))

(defn parse-long [s]
  (Long/parseLong (string/trim s)))

(defn sum [coll]
  (apply + coll))

(defn find-first [pred coll]
  (first (filter pred coll)))
