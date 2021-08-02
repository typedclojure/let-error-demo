(defproject let-error-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.typedclojure/typed.clj.checker "1.0.17"]]
  :profiles {:old-typed {:dependencies [[org.typedclojure/typed.clj.checker "1.0.16"]]}}
  :plugins [[lein-typed "0.4.6"]]
  :core.typed {:check [let-error-demo.core]}
  :repl-options {:init-ns let-error-demo.core})
