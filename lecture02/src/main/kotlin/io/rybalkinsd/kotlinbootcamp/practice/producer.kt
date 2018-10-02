package io.rybalkinsd.kotlinbootcamp.practice

/**
 * Produces a list of sorted integers
 *
 * @param from - lower bound of range (including)
 * @param to - upper bound of range (including)
 *
 * @return a sorted list of integers from lower bound to upper bound
 * example:
 * makeList(1, 4) = listOf(1, 2, 3)
 * makeList(1, 1) = listOf(1)
 * makeList(1, 0) = emptyList()
 *
 */
fun makeList(from: Int, to: Int): List<Int> {
    var counter = from
    var result = mutableListOf<Int>()
//    for (e in from..to) {
//
//    }
//    while (counter <= to) {
//        result.add(counter++)
//    }
//    return result
    return (from..to).toList()
}
