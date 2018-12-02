(ns advent-of-code.day02)

(defn count-2-3 [s]
  (let [counts (->> s
                    (group-by identity)
                    (map (fn [[k v]] (count v)))
                    (into
                     #{}))]
    [(if (counts 2) 1 0)
     (if (counts 3) 1 0)]))

(def input (->
            "src/advent_of_code/day02.input"
            clojure.java.io/reader
            line-seq))
;; part1
(let [[s2 s3] (->> input
                   (map count-2-3)
                   (reduce (fn [sum x] (map + sum x))))]
  (* s2 s3))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def candidates (->> input
                     (filter (fn [s] (> (reduce + (count-2-3 s)) 0)))))

(defn chardiff [s0 s1]
  (reduce + (map #(if (= %1 %2) 0 1) s0 s1)))

;; part2
(let [[cb0 cb1] (->>
                  (for [c0 candidates c1 candidates]
                    (if (= (chardiff c0 c1) 1)
                      [c0 c1]))
                  (filter identity)
                  flatten
                  (into #{})
                  (into []))]
  (apply str (filter #(not= %1 \_)
                     (map (fn [c0 c1]
                            (if (= c0 c1) c0 \_))
                          cb0
                          cb1))))
  
