(ns websrv.websocket.websocket
  (:require
   [websrv.websocket.server :refer [run-jetty ws-upgrade-request? ws-upgrade-response send!]]
   [ring.middleware.reload :refer [wrap-reload]]))

(def ws-handler {:on-connect (fn [ws] (println "connect")
                               (send! ws "hahahah"))
                 :on-error (fn [ws e] (println "error"))
                 :on-close (fn [ws status-code reason] (println "close"))
                 :on-text (fn [ws text-message] (println text-message))
                 :on-bytes (fn [ws bytes offset len] (println "bytes"))
                 :on-ping (fn [ws bytebuffer] (println "ping"))
                 :on-pong (fn [ws bytebuffer] (println "pong"))})

(defn app [req]
  (if (ws-upgrade-request? req)
    (ws-upgrade-response ws-handler)))

(defn run-websocket []
  (future
    (run-jetty
     (wrap-reload #'app)
     {:port 8081})))







