package com.lebogang.superheros.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lebogang.superheros.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.lebogang.superheros.retrofit.UsersList as UsersList

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://api.github.com/search/"
    var tv_user: TextView? = null
    var str:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_user = findViewById(R.id.tv_name)
        getUsers()
    }
    // function to call server and update ui
    fun getUsers() {

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var api = retrofit.create(Api::class.java)

        var call = api.getUsers()

        call.enqueue(object : Callback<UsersList> {

            override fun onResponse(call: Call<UsersList>?, response: Response<UsersList>?) {
                var usres = response?.body()
                var user = usres?.users
                var length = user!!.size

                for (i in 0 until length) {
                    str = str + "\n" + user.get(i).id + " " + user.get(i).name
                }

                tv_user!!.text = str
            }

            override fun onFailure(call: Call<UsersList>?, t: Throwable?) {
                Log.v("Error", t.toString())
            }
        })
    }
}