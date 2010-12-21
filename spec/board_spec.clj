(ns board-spec
  (:use [speclj.core]
        [ttt_clojure.board])
  (:require [ttt_clojure.board]))

(describe "Board"
  (it "is empty"
    (should (board-empty? [["-" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]])))
	(it "is not empty if X is in the first row"
		(should-not (board-empty? [["X" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]])))
	(it "is not empty if O is in the first row"
		(should-not (board-empty? [["O" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]])))
	(it "is not empty if X is in the second row"
		(should-not (board-empty? [["-" "-" "-"] ["-" "-" "X"] ["-" "-" "-"]])))
		
	(it "is a valid move if is isn't taken"
		(should (valid-move? [["-" "-" "-"] ["-" "-" "-"] ["-" "-" "-"]] 0 0)))
	(it "is not a valid move if is is taken"
		(should-not (valid-move? [["-" "X" "-"] ["-" "-" "-"] ["-" "-" "-"]] 0 1)))
	
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
)

(run-specs)
