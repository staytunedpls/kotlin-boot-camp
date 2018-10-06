package io.rybalkinsd.kotlinbootcamp.practice

import java.lang.NumberFormatException

class RawProfile(val rawData: String)

enum class DataSource {
    FACEBOOK,
    VK,
    LINKEDIN
}

sealed class Profile(
        var id: Long,
        var firstName: String? = null,
        var lastName: String? = null,
        var age: Int? = null,
        var dataSource: DataSource
)


class FacebookProfile(id: Long, firstName: String?, lastName: String?, age: Int?) : Profile(
        dataSource = DataSource.FACEBOOK,
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age
)
class VkProfile(id: Long, firstName: String?, lastName: String?, age: Int?) : Profile(
        dataSource = DataSource.VK,
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age
)
class LinkedInProfile(id: Long, firstName: String?, lastName: String?, age: Int?) : Profile(
        dataSource = DataSource.LINKEDIN,
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age
)

fun convertToCleanProfile(rp: RawProfile, id: Long): Profile? {
    val dataList = rp.rawData.replace("\n", "").split(',')
    val dataMap = dataList
            .map {
                it.split('=')
                        .asSequence()
                        .zipWithNext()
                        .first()
            }.toMap()

    return when (dataMap["source"]?.toUpperCase()) {
        "FACEBOOK" -> FacebookProfile(
                id = id,
                firstName = dataMap["firstName"],
                lastName = dataMap["lastName"],
                age = try {dataMap["age"]?.toInt()} catch (e: NumberFormatException) {null}
                )
        "VK" -> VkProfile(
                id = id,
                firstName = dataMap["firstName"],
                lastName = dataMap["lastName"],
                age = try {dataMap["age"]?.toInt()} catch (e: NumberFormatException) {null}
        )
        "LINKEDIN" -> LinkedInProfile(
                id = id,
                firstName = dataMap["firstName"],
                lastName = dataMap["lastName"],
                age = try {dataMap["age"]?.toInt()} catch (e: NumberFormatException) {null}
        )
        else -> null
    }
}

val rawProfiles = listOf(
        RawProfile("""
            firstName=alice,
            age=16,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            firstName=Dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            firstName=alla,
            lastName=OloOlooLoasla,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bella,
            age=36,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=angel,
            age=I will not tell you =),
            source=facebook
            """.trimIndent()
        ),

        RawProfile(
                """
            lastName=carol,
            source=vk,
            age=49
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bob,
            lastName=John,
            age=47,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bob,
            lastName=John,
            age=47,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            lastName=kent,
            firstName=dent,
            age=88,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            lastName=kent,
            firstName=dent,
            age=88,
            source=vk
            """.trimIndent()
        )
)

val profiles: List<Profile?> = rawProfiles
        .asSequence()
        .mapIndexed { index, rawProfile -> convertToCleanProfile(rp = rawProfile, id = index.toLong())}
        .toList()

/**
 * Task #2
 * Find the average age for each datasource (for profiles that has age)
 */
val avgAge: Map<DataSource, Double> = DataSource
        .values()
        .map { it to profiles
                .asSequence()
                .filter(fun(profile) = profile?.dataSource == it)
                .mapNotNull { x -> x?.age }
                .average() as Double}
        .toMap()

/**
 * Task #3
 * Group all user ids together with all profiles of this user.
 * We can assume users equality by : firstName & lastName & age
 */

var counter = 0L
val groupedProfiles: Map<Long, List<Profile?>> = profiles
        .groupBy {listOf(it?.age, it?.firstName, it?.lastName)}
        .asIterable()
        .mapIndexed {index: Int, gr -> index.toLong() to gr.value}
        .toMap()

