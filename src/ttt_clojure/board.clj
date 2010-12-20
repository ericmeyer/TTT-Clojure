(ns ttt_clojure.board)

(defn board-empty? [board]
	(every? #(= "-" %) (flatten board)))

(defn- spot-empty? [board row col]
	(= "-" ((board row) col)))
	
(defn valid-move? [board row col]
	(spot-empty? board row col))