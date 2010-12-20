(ns ttt_clojure.core)

(defn -main [& args]
	(prn "Enter a move")
	(let [move (read-line)]        
		(prn (str "You entered: " move))))
		
(-main)