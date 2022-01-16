(ns websrv.core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]

            [websrv.middlewares.middlewares :as middlewares]
            [websrv.middlewares.cors :as cors]
            [websrv.routes.routes :as routes]
            [websrv.websocket.websocket :as websocket]))

(def app
  (-> routes/routes
      middlewares/wrap-exception
      (wrap-json-body {:keywords? true})
      wrap-json-response

      (cors/wrap-cors :access-control-allow-origin "*"
                      :access-control-allow-methods [:get :put :post :delete])
      middlewares/wrap-response-time))

(defn -main [& _]

  (websocket/run-websocket)
  (run-jetty (wrap-reload #'app) {:port
                                  (if (nil? (System/getenv "PORT"))
                                    8080
                                    (Integer/parseInt (System/getenv "PORT")))}))
