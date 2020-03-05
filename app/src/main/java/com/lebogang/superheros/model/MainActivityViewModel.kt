package com.lebogang.superheros.model

import androidx.lifecycle.LiveData
import com.lebogang.superheros.database.AppDatabase
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.lebogang.superheros.database.SuperHeroEntity


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        // Constant for logging
        private val TAG = MainActivityViewModel::class.java.simpleName
    }
    //val tasks: LiveData<List<SuperHeroEntity>>

    init {
        val database = AppDatabase.getInstance(this.getApplication())
        Log.d(TAG, "Actively retrieving the tasks from the DataBase")
        //tasks = database.superHeroDAO().loadAllTasks()
    }


}
