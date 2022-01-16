(ns websrv.middlewares.middlewares
  (:require [clojure.set :as set]
            [ring.util.response :refer [response]]))

(defn wrap-response-time [handler]
  (fn [request]
    (let [start-time (System/currentTimeMillis)
          res (handler request)]

      (let [res-time (- (System/currentTimeMillis) start-time)]
        (println (format  "%s %s took %d ms" (name (:request-method request))  (:uri request) res-time))
        res))))

(defn wrap-exception [handler]
  (fn [request]
    (try
      (handler request)
      (catch Throwable e
        (response {:code 20001
                   :msg "error"})))))


