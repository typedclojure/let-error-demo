(ns let-error-demo.core
  (:require [clojure.core.typed :as t]))

(defn demo
  []
  (let [[a] #{:foo}]
    a))
