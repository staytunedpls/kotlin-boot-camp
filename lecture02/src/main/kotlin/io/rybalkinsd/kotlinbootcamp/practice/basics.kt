package io.rybalkinsd.kotlinbootcamp.practice

import java.lang.StringBuilder

/**
 * @param a - first integer
 * @param b - second integer
 *
 * @return math minimum of two integers
 *
 * example:
 * min(1 , 2) = 1
 * min(-16 , -1000) = -1000
 */
fun min(a: Int, b: Int) = if (a < b) a else b

/**
 * Concatenate all strings from
 * @param values
 * using a
 * @param separator
 *
 * example:
 * concat(listOf("a", "b", "c", "d"), "") = "abcd
 * concat(listOf("1", "2", "3", "4"), " + ") = "1 + 2 + 3 + 4"
 */
fun concat(values: List<String>, separator: String) = values.joinToString(separator = separator)
