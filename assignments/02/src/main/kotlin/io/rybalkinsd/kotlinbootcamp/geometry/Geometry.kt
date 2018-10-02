package io.rybalkinsd.kotlinbootcamp.geometry

/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
data class Point(val x: Int, val y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean {
        return when (other) {
            is Point -> this == other
            is Bar -> other.isColliding(this)
            else -> throw Exception("What was provided as an argument to Point?")
        }
    }
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
data class Bar(var firstCornerX: Int, var firstCornerY: Int, var secondCornerX: Int, var secondCornerY: Int) : Collider {
    init {
        val leftPointX: Int = minOf(this.firstCornerX, this.secondCornerX)
        val rightPointX: Int = maxOf(this.firstCornerX, this.secondCornerX)
        val leftPointY: Int = minOf(this.firstCornerY, this.secondCornerY)
        val rightPointY: Int = maxOf(this.firstCornerY, this.secondCornerY)
        this.firstCornerX = leftPointX
        this.firstCornerY = leftPointY
        this.secondCornerX = rightPointX
        this.secondCornerY = rightPointY
    }
    override fun isColliding(other: Collider): Boolean {
        when (other) {
            is Point -> {
                val xInside: Boolean = (this.firstCornerX <= other.x) && (other.x <= this.secondCornerX)
                val yInside: Boolean = (this.firstCornerY <= other.y) && (other.y <= this.secondCornerY)
                return xInside && yInside
            }
            is Bar -> {

                for (x in listOf(this.firstCornerX, this.secondCornerX))
                    for (y in listOf(this.firstCornerY, this.secondCornerY))
                        if (other.isColliding(Point(x, y))) return true
                for (x in listOf(other.firstCornerX, other.secondCornerX))
                    for (y in listOf(other.firstCornerY, other.secondCornerY))
                        if (this.isColliding(Point(x, y))) return true
                return false
            }
            else -> throw Exception("What argument did you provide to Bar.isColliding?")
        }
    }
}