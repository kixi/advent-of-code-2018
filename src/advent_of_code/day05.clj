(ns advent-of-code.day05)

(def input "dabAcCaCBAcCcaDA")
(def input (clojure.string/trim (slurp "src/advent_of_code/day05.input")))

(defn react? [unit1 unit2]
   (and (not= unit1 unit2)
        (= (Character/toUpperCase unit1)
           (Character/toUpperCase unit2))))

(defn react-poly [[x & more :as all] unit]
  (cond (nil? x) [unit]
        (react? x unit) more
        :else (cons unit all)))

;; part 1
(->>
 (reduce react-poly
         []
         input)
 count)

              
