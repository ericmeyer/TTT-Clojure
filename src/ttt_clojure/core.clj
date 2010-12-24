(ns ttt_clojure.core
	(:use [ttt_clojure.rules])
  (:require [ttt_clojure.rules]))

(defn get-valid-move [board]
	(prn "Go X:")
	(def x (Integer/parseInt (read-line)))
	(prn "Go Y:")
	(def y (Integer/parseInt (read-line)))
	(if (valid-move? board x y)
		[x y]
		(get-valid-move board)))
	
(defn print-board [board]
	(prn (board 2))
	(prn (board 1))
	(prn (board 0)))
				
(defn take-next-turn [board current-player]
	(def new-board (make-move board (get-valid-move board) current-player))
	(print-board new-board)
	(if (not (game-over? new-board))
		(take-next-turn new-board (next-player current-player))))

(defn -main [& args]
	(take-next-turn (empty-board) "X"))

(-main)