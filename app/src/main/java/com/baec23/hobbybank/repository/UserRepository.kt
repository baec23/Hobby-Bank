package com.baec23.hobbybank.repository

import androidx.compose.animation.core.snap
import com.baec23.hobbybank.ui.auth.signup.SignupFormState
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.tasks.await

@ActivityScoped
class UserRepository {
    private val collectionRef = Firebase.firestore.collection("users")

    fun getId(){
        val toReturn: MutableList<String> = arrayListOf()
        return try {
            val listUserName = collectionRef
                .get()
                .addOnCompleteListener{ task->
                if(task.isSuccessful){
                    val snaps: List<DocumentSnapshot> = task.result.documents
                    for (i: Int in snaps.indices){
                        val toAdd: String = snaps[i].getString("username").toString()
                        toReturn.add(toAdd)
                    }
                    Result.success(toReturn)
                }else{
                    Result.failure(Exception("Failed to get userName"))
                }
            }
        }catch (e: Exception){

        }
    }

    suspend fun saveUser(userInfo: SignupFormState): Result<SignupFormState>{
        return try {
            val a = collectionRef.add(userInfo).await()
            Result.success(a.get().result.toObject(SignupFormState::class.java)!!)
        } catch (e: Exception){
            Result.failure(Exception("Failed to save user info"))
        }
    }
}