(ns websrv.routes.hello
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]))

(def ds (jdbc/get-datasource {:dbtype "mysql"
                              :dbname "ring1"
                              :user "admin"
                              :password "123456"
                              :host ""
                              :port "3306"}))

(defn get [_ id]
  (let [res (jdbc/execute! ds ["select name,age from user where id = ?" id] {:builder-fn rs/as-unqualified-lower-maps})]
    {:status 200
     :body {:data res}}))

(defn post [req body]
  {:status 200
   :body body})

