package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}