package io.rybalkinsd.kotlinbootcamp.assignments

/**
 * Returns the greatest of `int` values.
 *
 * @param values an argument. !! Assume values.length > 0. !!
 * @return the largest of values.
 */
// fun max(values: list<int>): int {
//    var max= values[0]
//    for (value in values)
//        max = if (value > max) value else max
//    return max
// }

// fun max(values: List<Int>): Int {
//    var max : Int = values[0]
//    values.forEach { max = if (it > max) it else max }
//    return max
// }

fun max(values: List<Int>): Int? = values.max()

// Long is needed in case of large numbers, sum returns same value type as list (Int)
// So .toLong is required

// This version falls on "medium size list test" - I guess because of large int values
// fun sum(values: List<Int>): Long = values.sum().toLong()

// This one is better:

/**
 * Returns the sum of all `int` values.
 *
 * @param values an argument. Assume values.length > 0.
 * @return the sum of all values.
 */

fun sum(values: List<Int>): Long {
    var fullSum: Long = 0
    values.forEach { fullSum += it }
    return fullSum
}
