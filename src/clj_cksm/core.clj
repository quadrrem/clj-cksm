(ns clj-cksm.core)

(defn slurp-file 
  "Reads a file into a byte array"
  [file]
  (with-open [in (clojure.java.io/input-stream file)
              out (java.io.ByteArrayOutputStream.)]
    (clojure.java.io/copy in out)
    (.toByteArray out)))

(defn digest
  "Get message digest from a byte array"
  [bytes format]
  (let [md (java.security.MessageDigest/getInstance format)]
    (.digest md bytes)))

(defn toHex
  "Convert a byte array to the hex representation"
  [bytes]
  (javax.xml.bind.DatatypeConverter/printHexBinary bytes))

(defn- cmp [calculated format expected]
  (if (clojure.string/blank? expected)
    (println format " checksum is " calculated)
    (if (= (.toUpperCase calculated) (.toUpperCase expected))
      (println (str "Checksum is valid! " calculated))
      (do (println "Checksum is not valid!")
          (println (str "Expected checksum " expected))
          (println (str "but was " calculated))))))

(defn checksum [file format cks]
  (-> file
      (slurp-file)
      (digest format)
      (toHex)
      (cmp format cks)))

(defn -main
  ([] (println "Parameters: file format checksum"))
  ([file] (do (println "Using SHA1") (-main file "SHA1")))
  ([file format] (-main file format ""))
  ([file format cks & rest] (checksum file format cks)))