(ns ttt_clojure.board)

(defn board-empty? [board]
	(every? #(= "-" %) (flatten board)))

(defn- spot-empty? [board row col]
	(= "-" ((board row) col)))
	
(defn valid-move? [board row col]
	(spot-empty? board row col))

(defn- player-wins-in-group? [player group]
	(every? #(= player %) group))
	
(defn- winner-in-group? [group]
	(or (player-wins-in-group? "X" group) (player-wins-in-group? "O" group)))
	
(defn column [n board]
	(map #(% n) board))

(defn- columns [board]
	(map #(column % board) (range (count board))))

(defn- rows [board]
	board)
	
(defn- winner-in-rows? [board]
	(some #(winner-in-group? %) (rows board)))

(defn- winner-in-columns? [board]
	(some #(winner-in-group? %) (columns board)))

(defn- winner-in-diagonal? [board]
	(or (winner-in-group? [((board 0) 0) ((board 1) 1) ((board 2) 2)])
			(winner-in-group? [((board 2) 0) ((board 1) 1) ((board 0) 2)])))
	
(defn winner-exists? [board]
	(or (winner-in-rows? board) (winner-in-columns? board) (winner-in-diagonal? board)))
