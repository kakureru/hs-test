package com.hammersystemstest.menu.data

import com.hammersystemstest.menu.data.db.MenuDao
import com.hammersystemstest.menu.data.db.model.toDomain
import com.hammersystemstest.menu.data.network.TheMealDbApi
import com.hammersystemstest.menu.data.network.dto.toDb
import com.hammersystemstest.menu.data.network.dto.toDomain
import com.hammersystemstest.menu.domain.Category
import com.hammersystemstest.menu.domain.Meal
import com.hammersystemstest.menu.domain.MenuRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MenuRepositoryImpl(
    private val theMealDbApi: TheMealDbApi,
    private val menuDao: MenuDao,
) : MenuRepository {

    override suspend fun getBannerUrls(): List<String> {
        delay(1000)
        return listOf(
            "https://s3-alpha-sig.figma.com/img/0e5f/8769/bbdcde65e7e95920e9f71a180817df1a?Expires=1699833600&Signature=GPwMGGQ8G4Kf-6GjHxHB4vgbFbLUFCD6wA7ggJTAEl4vemlv9e4R7QCi9KWYDYuQ2evFatJDQaD4rBQ2TgNlNgRf1z569J2vdCkxgJ1b4Dqt1TvuIo3Jcdo9-31~rO3zRojc5OaC22DZDUbcVxhbXjI4-VieD7LkCddkupuYCmiACFdY7Kmbeth5tmYfpGkKNWn2AKrIzZYq0e0EUPzfI-Job5e2MeKdfrdC~ojwSwh07KcS88eChYukE2PRW0koMkwgYTmuJsKo395-XgJ6tG1TQqbz2pR7ionSt3hFrjneHSoF7zT3dWuP76~J3iVEpIORJIXvxlwKLQAhK~tmoQ__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            "https://s3-alpha-sig.figma.com/img/b42b/b92c/f6acf7e8e259819d3dd44499cb49eb54?Expires=1699833600&Signature=iV8nFE-0yrg4IP631rhHYSP3tqem3cQnbkteGlXiHCU7WqSVnbEGC~te~wGFJij0ocZP6jx94YZoYfduCoaP6-sGQLi8SDvooznpxbJv-lINK46CWElz5vtPIwlQc3iuGC7xBddcIEgjfi5lrYxMXmib7sDM0qqrEYeollLIuoef9dWbiBp7nD7Fs9tvQ5-LhQC-2dS~zJ7oMX6TNYxosM3wILSoZk9ZlBM4-GFkOlNMkOqGa9kTu9ML6IFBCXR00WS8qbA4e9j4Gpu4NcWz5pH3eY8WSjrQ8OVgzp7giopFd2PI3AKRsTG0C2vSW6isL-ms0R6RtrsC~rKo0kqQ6Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
        )
    }

    override suspend fun getCategories(): Flow<List<Category>> = flow {
        val cache = menuDao.getCategories().map { it.toDomain() }
        if (cache.isNotEmpty()) emit(cache)
        val fresh = theMealDbApi.getCategories().categories
        emit(fresh.map { it.toDomain() })
        menuDao.updateCategories(fresh.map { it.toDb() })
    }

    override suspend fun getMealsByCategory(category: Category): Flow<List<Meal>> = flow {
        val cache = menuDao.getMealsByCategory(category.name).map { it.toDomain(category) }
        if (cache.isNotEmpty()) emit(cache)
        val fresh = theMealDbApi.getMealsByCategory(category.name).meals
        emit(fresh.map { it.toDomain(category) })
        menuDao.updateMeals(fresh.map { it.toDb(category) }, category.name)
    }
}
