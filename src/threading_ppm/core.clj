(ns threading-ppm.core)

(def size 255)

(def head (str "P3\n" 255 " " 255 " " 255 "\n"))

(defn red []
  (map mod (range (* size size)) (repeat size))
                                        ;(->> (repeat size) map mod (range (* size size)))  
  )

(defn green []
  (flatten (map repeat (repeat size) (range size)))
                                        ; (->> (range size) (map repeat (repeat size)) flatten)
  )

(defn blue []
  (map mod (map * (red) (green)) (repeat size)))

;testing
(red)
(green)
(blue)

(defn image-data-vals []
  (map str (red) (repeat " ") (green) (repeat " ") (blue) (repeat " ")))

(image-data-vals)
(type (image-data-vals))

(defn image-data []
  (clojure.string/join "\n" image-data-vals))

(image-data)

(defn ppm-content [] (str head "\n" image-data))

(ppm-content)

(spit "threadppmdemo.ppm" ppm-content)
