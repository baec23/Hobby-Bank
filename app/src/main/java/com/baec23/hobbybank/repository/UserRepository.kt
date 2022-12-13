package com.baec23.hobbybank.repository

import com.baec23.hobbybank.model.User
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class UserRepository {
    val currUser = User(displayName = "test", username = "test", id = "testId")
}