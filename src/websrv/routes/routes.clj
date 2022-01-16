(ns websrv.routes.routes
  (:require
   [compojure.core :refer [defroutes GET POST context]]
   [compojure.route :refer [not-found]]

   [websrv.routes.hello :as hello]))

(defroutes routes
  (context "/web-gateway" []
    (POST "/hello" {body :body :as req} (hello/post req body))
    (GET "/hello/:id" [id :as req] (hello/get req id)))

  (not-found "<h1>Page not found</h1>"))

