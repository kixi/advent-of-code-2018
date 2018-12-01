(ns advent-of-code.day01)

(def input
  (read-string (str "[" (slurp "src/advent_of_code/day01.input") "]")))

;; part 1
(->> 
 input
 (apply +))

;; part 2
(->>
  (reductions + (cycle input))
  (reduce (fn [seen x]
            (if (seen x)
              (reduced x)
              (conj seen x)))
          #{}))
              
