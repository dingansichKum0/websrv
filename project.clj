(def jetty-version "10.0.7")

(defproject websrv "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring "1.9.4"]
                 [compojure "1.6.2"]
                 [ring/ring-json "0.5.1"]
                 [com.github.seancorfield/next.jdbc "1.2.761"]
                 [mysql/mysql-connector-java "8.0.19"]
                 [org.eclipse.jetty/jetty-server ~jetty-version]
                 [org.eclipse.jetty.websocket/websocket-jetty-api ~jetty-version]
                 [org.eclipse.jetty.websocket/websocket-jetty-server ~jetty-version]
                 [org.eclipse.jetty.websocket/websocket-servlet ~jetty-version]
                 [org.eclipse.jetty.http2/http2-server ~jetty-version]]
  :main websrv.core
  :repl-options {:init-ns websrv.core}
  :aot [websrv.core])
