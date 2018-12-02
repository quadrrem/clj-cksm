(ns clj-cksm.core-test
  (:require [clojure.test :refer :all]
            [clj-cksm.core :refer :all]))

(deftest md5
  (testing "MD5 checksum"
    (is (= "9EAB500708CAD82AAD8E156BDA5ED03A" (toHex (digest (.getBytes "TestString1") "MD5"))))))


(deftest sha1
  (testing "SHA1 checksum"
    (is (= "042244B2F192294D7322CE9492E044B39322250E" (toHex (digest (.getBytes "TestString1") "SHA1"))))))

(deftest sha-256
  (testing "SHA-256 checksum"
    (is (= "230B94A79B35A026063A4B87469B4C42836E1C30CA13D5DC4AB42563A4D13B72" (toHex (digest (.getBytes "TestString1") "SHA-256"))))))    
