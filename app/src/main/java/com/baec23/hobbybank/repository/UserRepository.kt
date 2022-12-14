package com.baec23.hobbybank.repository

import com.baec23.hobbybank.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.tasks.await

@ActivityScoped
class UserRepository {
    var currUser: User? = null
        private set
    private val collectionRef = Firebase.firestore.collection("users")
    suspend fun trySignup(user: User): Result<User> {
        val queryResult =
            collectionRef.whereEqualTo("username", user.username).get().await().documents
        return if (queryResult.isEmpty()) {
            saveUser(user)
        } else {
            Result.failure(Exception("username이 이미 등록되어 있습니다."))
        }
    }

    suspend fun tryLogin(username: String, password: String): Result<User> {
        val queryResult =
            collectionRef
                .whereEqualTo("username", username)
                .whereEqualTo("password", password)
                .get().await().documents
        if (queryResult.isNotEmpty()) {
            val doc = queryResult.first()
            val user = doc.toObject<User>()?.copy(id = doc.id)
            return if (user != null) {
                currUser = user
                Result.success(user)
            } else {
                Result.failure(Exception("response에서 온 데이터를 User로 바꾸는데 에러가 났다."))
            }
        }
        return Result.failure(Exception("username 혹은 password가 제대로 입력이 안됬다."))
    }

    private suspend fun saveUser(user: User): Result<User> {
        return try {
            val savedUser = collectionRef.add(user).await()
            Result.success(savedUser.get().await().toObject(User::class.java)!!)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to save user info"))
        }
    }
}