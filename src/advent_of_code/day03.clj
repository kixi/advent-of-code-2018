(ns advent-of-code.day03
  (:require [clojure.string :as str]))



(defn line-to-claim [str-line]
  (let [[id dx dy lx ly] (read-string (str "[" (str/replace str-line #"[@#,:x]" " ") "]"))]
    {:id id
     :dx dx
     :dy dy
     :lx lx
     :ly ly}))

(def input-claims
    (->>
     (slurp "src/advent_of_code/day03.input")
     str/split-lines
     (map line-to-claim)))

(def input-claims (with-open [rdr (clojure.java.io/reader "src/advent_of_code/day03.input")]
                    (dorun (map line-to-claim (line-seq rdr)))))

(defn area-from-claim [claim]
  (for [x (range (:lx claim))
        y (range (:ly claim))]
    [[(+ (:dx claim) x)
      (+ (:dy claim) y)]
     (:id claim)]))


(def claims [{:lx 4 :ly 4 :dx 1 :dy 3 :id 1}
             {:lx 4 :ly 4 :dx 3 :dy 1 :id 2}
             {:lx 2 :ly 2 :dx 5 :dy 5 :id 3}])
             
(area-from-claim {:lx 4 :ly 4 :dx 1 :dy 3 :id 1})

(defn add-claim-to-area [area claim]
  (let [areas (area-from-claim claim)]
    (reduce (fn [area* [coord* id*]]
              (update area* coord* #(if %1 0 id*)))
            area areas)))

(add-claim-to-area {} {:lx 4 :ly 4 :dx 1 :dy 3 :id 1})

(defn part-1 []
  (let [area (reduce add-claim-to-area {} input-claims)]
    (count (filter (fn [[[x y] id]] (= id 0)) area))))

(part-1)

