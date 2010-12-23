(ns rules-spec
  (:use [speclj.core]
        [ttt_clojure.rules])
  (:require [ttt_clojure.rules]))

(describe "Board"
  (it "is not full"
    (should-not (board-full? [["-" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]])))
	(it "is not full if it is partially filled"
		(should-not (board-full? [["X" "-" "-"] ["-" "O" "-"] ["-" "-" "X"]])))
	(it "is full"
		(should (board-full? [["O" "X" "O"] ["X" "O" "O"] ["O" "X" "X"]])))
		
	(it "is a valid move if is isn't taken"
		(should (valid-move? [["-" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]] 0 0)))
	(it "is not a valid move if is is taken"
		(should-not (valid-move? [["-" "X" "-"] ["-" "-" "-"] ["-" "-" "-"]] 0 1)))
	(it "is not valid if the row is too high"
		(should-not (valid-move? (empty-board) 5 0)))
	(it "is not valid if the row is too low"
		(should-not (valid-move? (empty-board) -1 0)))
	(it "is not valid if the col is too high"
		(should-not (valid-move? (empty-board) 0 6)))
	(it "is not valid if the col is too low"
		(should-not (valid-move? (empty-board) 0 -1)))

	(it "gets the first column"
		(should= ["1" "4" "7"] (column 0 [["1" "2" "3"] ["4" "5" "6"] ["7" "8" "9"]])))
	(it "gets the second column"
		(should= ["2" "5" "8"] (column 1 [["1" "2" "3"] ["4" "5" "6"] ["7" "8" "9"]])))
		
	(it "has no winner for an empty board"
		(should-not (winner-exists? [["-" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]])))
	(it "has a winner if X completes the first row"
		(should (winner-exists? [["X" "X" "X"] ["-" "-" "-"] ["-" "-" "-"]])))
	(it "has a winner if O completes the first row"
		(should (winner-exists? [["O" "O" "O"] ["-" "-" "-"] ["-" "-" "-"]])))
	(it "has a winner if X completes the second row"
		(should (winner-exists? [["O" "X" "O"] ["X" "X" "X"] ["-" "-" "-"]])))
	(it "has a winner if X completes the first column"
		(should (winner-exists? [["X" "-" "-"] ["X" "-" "-"] ["X" "-" "-"]])))
	(it "has a winner if O completes the first column"
		(should (winner-exists? [["O" "X" "O"] ["O" "-" "-"] ["O" "-" "-"]])))
	(it "has a winner if X completes the second column"
		(should (winner-exists? [["O" "X" "O"] ["-" "X" "O"] ["-" "X" "-"]])))
	(it "has a winner if X completes the third column"
		(should (winner-exists? [["O" "X" "X"] ["-" "O" "X"] ["-" "X" "X"]])))
	(it "has a winner if X completes one diagonal"
		(should (winner-exists? [["X" "X" "-"] ["-" "X" "X"] ["O" "-" "X"]])))
	(it "has a winner if X completes the other diagonal"
		(should (winner-exists? [["O" "X" "O"] ["-" "O" "X"] ["O" "-" "X"]])))
		
	(it "makes a move"
		(should= [["-" "-" "-"] ["-" "X" "-"] ["-" "-" "-"]] (make-move (empty-board) [1 1] "X")))
	(it "makes a different move for a different player"
		(should= [["-" "O" "-"] ["-" "-" "-"] ["-" "-" "-"]] (make-move (empty-board) [0 1] "O")))
;;	(it "can makes a two moves"
;;		(binding [board-with-move (make-move (empty-board) [1 1] "X")])
;;		(should= [["-" "O" "-"] ["-" "X" "-"] ["-" "-" "-"]] (make-move (board-with-move) [0 1] "O")))
		
	(it "is game over when there is a winner"
		(binding [winner-exists? (fn mock-winner-exists? [board] true)
							board-full? (fn mock-board-full? [board] false)]
			(should (game-over? (empty-board)))))
	(it "is not game over when there is a winner"
		(binding [winner-exists? (fn mock-winner-exists? [board] false)
							board-full? (fn mock-board-full? [board] false)]
			(should-not (game-over? (empty-board)))))
	(it "is game over when the board is full"
		(binding [winner-exists? (fn mock-winner-exists? [board] false)
							board-full? (fn mock-board-full? [board] true)]
			(should (game-over? (empty-board)))))
	(it "is game over when the board is full and a winner exists"
		(binding [winner-exists? (fn mock-winner-exists? [board] true)
							board-full? (fn mock-board-full? [board] true)]
			(should (game-over? (empty-board)))))

	(it "has a next player for X"
		(should= "O" (next-player "X")))
	(it "has a next player for O"
		(should= "X" (next-player "O")))
)

(run-specs)
