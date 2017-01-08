(ns vittkosh.cli
  (:require [org.httpkit.client :as http]
            [net.cgrand.enlive-html :as html]))

(defn- get-dom
  []
  (let [response @(http/get "https://rbi.org.in/Scripts/bs_viewcontent.aspx?Id=2009")]
    (html/html-snippet (:body response))))

(defn- get-all-links [dom]
  (let [alinks (html/select dom-obj [:table :a])]
    (map (fn [record] {:bank (first (:content record)) :link (:href (:attrs record))}) alinks)))
