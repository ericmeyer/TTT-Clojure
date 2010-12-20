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
)

(run-specs)
