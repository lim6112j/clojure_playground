(ns proj2.core
  (:gen-class)
  (:require
   clojure.java.io))
;; (defn greet [] (println "hello"))
;;(def greet (fn [] (println "hello")))
(def greet #(print "hell"))
(defn greeting
  ([] (str "hell, world"))
  ([x] (str "hello, " x))
  ([x y] (str x y)))
(defn do-nothing [x] x)
(defn always-thing [& a] 100)
(defn make-thingy [x]
  (fn [& a] x))
(defn triplicate [f] (f) (f) (f))
(defn opposite [f] (fn [& args] (not (apply f args))))
(defn triplicatee [f & args]
  (triplicate (fn [] (apply f args))))
(defn constFunc [x]
  (+ (Math/pow (Math/sin x) 2) (Math/pow (Math/cos x) 2)))
(defn http-get [url]
  (slurp (.openStream (java.net.URL. url))))
(defn one-less-arg [f x]
  (fn [& args] (f (apply + x args))))
(defn two-fns [f g] #(f (g %)))
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet)
  (always-thing)
  ((make-thingy 3))
  (triplicate greet)
  (triplicatee println 1 2 3)
  (greeting (do-nothing "world"))
  (http-get "https://www.w3.org")
  ((one-less-arg #(* % 3) 3) 1 2 3)
  ((two-fns println #(* % 2)) 3)
  (constFunc 1)
  (let [x 5] (cond
               (< x 2) "x is less than 2"
               (< x 10) "x is less than 10"))
  (println (loop [i 0]
             (if (< i 10) (recur (inc i)) i)))
  (try
    (throw (Exception. "something went wrong"))
    (catch Exception e (.getMessage e)))
  (try
    (throw (ex-info "There was a problem" {:detail 42}))
    (catch Exception e
      (prn (:detail (ex-data e)))))

  (let [f (clojure.java.io/writer "/tmp/new")]
    (try
      (.write f "some text")
      (finally
        (.close f))))
  (with-open [f (clojure.java.io/writer "/tmp/new")]
    (.write f "some text")))
