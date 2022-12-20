package com.baec23.hobbybank.repository

import android.graphics.Bitmap
import android.util.Log
import com.baec23.hobbybank.model.HobbyClass
import com.baec23.hobbybank.model.HobbyClassSession
import com.baec23.hobbybank.util.snapshotFlow
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

@ActivityScoped
class HobbyClassRepository {
    private val TAG = "HobbyClassRepository: DEBUG: "
    private val jobsStorageReference = Firebase.storage.reference.child("hobbyClass/")
    private val hobbyClassRef = Firebase.firestore.collection("hobbyClass")
    private val hobbyClassSessionRef = Firebase.firestore.collection("hobbyClassSession")

    fun getAllHobbyClasses(): Flow<List<HobbyClass>> {
        return hobbyClassRef.snapshotFlow().map { querySnapshot ->
            querySnapshot.documents.mapNotNull {
                it.toObject<HobbyClass>()
            }
        }
    }

    suspend fun saveHobbyClass(hobbyClass: HobbyClass, bitmaps: List<Bitmap> = listOf()): Result<HobbyClass> {
        return try {
            val result = hobbyClassRef.add(hobbyClass).await().get().await()
            val documentId = result.id
            val uploadedBitmapUrls = uploadBitmaps(documentId, bitmaps)
            val updatedHobbyClass = result.toObject(HobbyClass::class.java)!!.copy(id = documentId, bitmapUrls = uploadedBitmapUrls)
            hobbyClassRef.document(documentId).set(updatedHobbyClass).await()
            Result.success(updatedHobbyClass)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to save hobby class : $e"))
        }
    }

    suspend fun saveHobbyClassSession(hobbyClassSession: HobbyClassSession): Result<HobbyClassSession> {
        return try {
            val result = hobbyClassSessionRef.add(hobbyClassSession).await().get().await()
            val savedHobbyClassSession = result.toObject(HobbyClassSession::class.java)!!
            Result.success(savedHobbyClassSession)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to save hobby class session : $e"))
        }
    }

    private suspend fun uploadBitmaps(classId: String, toUpload: List<Bitmap>): List<String> {

        val toReturn: MutableList<String> = mutableListOf()
        toUpload.forEachIndexed { i, bitmap ->
            val fileName = "/class_${classId}/${i}.jpg"
            val fileRef = jobsStorageReference.child(fileName)
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val downloadUrl = fileRef.putBytes(data).await().storage.downloadUrl.await()
            toReturn.add(downloadUrl.toString())
        }
        return toReturn
    }
}