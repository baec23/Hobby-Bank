package com.baec23.hobbybank.repository

import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.util.snapshotFlow
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

@ActivityScoped
class HobbyClassRepository {
    private val TAG = "HobbyClassRepository: DEBUG: "
    private val collectionRef = Firebase.firestore.collection("hobbyClass")

    fun getAllHobbyClasses(): Flow<List<HobbyClass>> {
        return collectionRef.snapshotFlow().map { querySnapshot ->
            querySnapshot.documents.mapNotNull {
                it.toObject<HobbyClass>()
            }
        }
    }

    suspend fun saveHobbyClass(hobbyClass: HobbyClass): Result<HobbyClass> {
        return try {
            val a = collectionRef.add(hobbyClass).await()
            Result.success(a.get().result.toObject(HobbyClass::class.java)!!)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to save hobby class"))
        }
    }
}