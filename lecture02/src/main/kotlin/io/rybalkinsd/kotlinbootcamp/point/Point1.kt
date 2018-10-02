package io.rybalkinsd.kotlinbootcamp.point

class Point1 {
    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    val x: Int
    get() {
        println("Kek")
        return field
    }
    val y: Int

    override fun toString(): String = "Ulala"
}
