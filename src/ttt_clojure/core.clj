(ns ttt_clojure.core
	(:use [ttt_clojure.rules])
  (:require [ttt_clojure.rules]))

(defn get-valid-number [prompt]
	(prn prompt)
	(def attempt (try (Integer/parseInt (read-line))
		(catch NumberFormatException e nil)))
		(if attempt
			attempt
			(get-valid-number prompt)))

(defn get-valid-move [board]
	(def x (get-valid-number "Go X:"))
	(def y (get-valid-number "Go Y:"))
	(if (valid-move? board y x)
		[y x]
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
	(print-board (empty-board))
	(take-next-turn (empty-board) "X"))

(-main)