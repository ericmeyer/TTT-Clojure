(ns basics-spec
  (:use [speclj.core]
        [ttt_clojure.board])
  (:require [ttt_clojure.board]))

(describe "Board"

  (it "is empty"
    (should (board-empty? [[- - -] [- - - ] [- - -]])))

  (it "is not false"
    (should-not false)))

(run-specs)
