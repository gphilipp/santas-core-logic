(ns santas.core
  (:refer-clojure :exclude [==])
  (:require  [clojure.core.logic :refer :all]
             [clojure.core.logic.pldb :refer :all]))

(run 1  [q]
     (== 1 q))

(run 2 [q]
     (membero q [:a :b] ))

(def people-kw [[:Walter :White]
             [:Skyler :White]
             [:Gustavo :Fring]])

(run* [q]
     (fresh [f1 n1 tof1 ton1 ]
            (membero [f1 n1] people-kw)
            (membero [tof1 ton1] people-kw)
            (!= n1 ton1)
            (== q [f1 n1 tof1 ton1])))

(def people ["Walter White"
             "Skyler White"
             "Gustavo Fring"
             "Saul Goodman"
             "Jesse Pinkman"
             "Henry Shrader"
             "Marie Shrader"])

(db-rel siblings a b)
(db-rel character a)
(def facts (db [siblings "Walter White" "Skyler White"]
               [siblings "Henry Shrader" "Marie Shrader"]
               [character "Walter White"]
               [character "Skyler White"]
               [character "Gustavo Fring"]
               [character "Saul Goodman"]
               [character "Jesse Pinkman"]
               [character "Henry Shrader"]
               [character "Marie Shrader"]))

(with-db facts (
                run 1 [q]
                    (fresh [x y]
                           (character x)
                           (character y)
                           (nafc siblings x y)
                           (nafc siblings y x)
                           (!= x y)
                           (== q [x y]))))


(defn siblingso [x y]
  (conde
   [(siblings x y)]
   [(siblings y x)]))

(with-db facts (
                run 1 [q]
                    (fresh [x y]
                           (character x)
                           (character y)
                           (nafc siblingso x y)
                           (!= x y)
                           (== q [x y]))))


(with-db facts (
                run 1 [q]
                    (fresh [a1 a2
                            b1 b2
                            c1 c2
                            d1 d2
                            e1 e2
                            f1 f2 
                            g1 g2]
                           (character a1)
                           (character a2)
                           (character b1)
                           (character b2)
                           (character c1)
                           (character c2)
                           (character d1)
                           (character d2)
                           (character e1)
                           (character e2)
                           (character f1)
                           (character f2)
                           (character g1)
                           (character g2)
                           (nafc siblingso a1 a2)
                           (nafc siblingso b1 b2)
                           (nafc siblingso c1 c2)
                           (nafc siblingso d1 d2)
                           (nafc siblingso e1 e2)
                           (nafc siblingso f1 f2)
                           (nafc siblingso g1 g2)
                           (distincto [a1 a2 b1 b2 c1 c2 d1 d2 e1 e2 f1 f2 g1 g2])                           
                           (== q [[a1 a2]
                                  [b1 b2]
                                  [c1 c2]
                                  [d1 d2]
                                  [e1 e2]
                                  [f1 f2] 
                                  [g1 g2]]))))

