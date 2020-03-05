package com.lebogang.superheros.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.lebogang.superheros.database.AppDatabase
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap
import com.lebogang.superheros.database.SuperHeroEntity
import com.lebogang.superheros.R
import com.lebogang.superheros.model.MainActivityViewModel
import com.lebogang.superheros.util.ApiHelper
import kotlinx.android.synthetic.main.adapter_view_item.*


class MainActivity:AppCompatActivity() {

    companion object {
        // Constant for logging
        private val TAG = MainActivity::class.java.simpleName
        var GetPostJSONApi = "https://superheroapi.com/api/104776814471146/70/image"
    }

    lateinit var progressBar: ProgressBar
    internal var arrayList = ArrayList<HashMap<String, String>>()

    //private var mRecyclerView: RecyclerView? = null
    //private var mAdapter: SuperHeroAdapter? = null

    private var mTaskEntries: List<SuperHeroEntity>? = null

    // Member variable for the Database
    private var db: AppDatabase? = null

    lateinit var tvId: TextView
    lateinit var tvName: TextView
    lateinit var priorityView: TextView
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            Log.d(TAG, "onCreate()")
            setContentView(R.layout.activity_main)

            // Show BackButton and Set custom Title on Actionbar
            val actionBar = supportActionBar
            if (actionBar != null) {
                actionBar.title = "Superhero"
            }

            imageView = findViewById(R.id.image)
            tvId = findViewById(R.id.textViewTop)
            tvName = findViewById(R.id.textViewBottom)

            db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
                    "character_list.db").build()

            // findViewById and set view tvId
            progressBar = findViewById(R.id.Pbar)

            // Set the RecyclerView to its corresponding view
            //mRecyclerView = findViewById(R.tvId.recyclerView)

            // Set the layout for the RecyclerView to be a linear layout, which measures and
            // positions items within a RecyclerView into a linear list
            //this.mRecyclerView.layoutManager = LinearLayoutManager(this)

            // Initialize the adapter and attach it to the RecyclerView
            //mAdapter = SuperHeroAdapter(this)
            //this.mRecyclerView!!.adapter = mAdapter

            if (isNetworkConnected) {
                // Call AsyncTask for getting from server (JSON Api)
                getDeveloper().execute()
            } else {
                Log.d(TAG, "No Internet connection.")
                Toast.makeText(applicationContext, "No Internet Connection!", Toast.LENGTH_SHORT).show()
                // Read the database and display the items.
                Thread(Runnable {
                    Log.d(TAG, "Actively retrieving the tasks from the DataBase in a thread.")
                    //AppDatabase.getInstance(this@MainActivity).superHeroDAO().getAll()


                })

            }

    }

    // Checking Internet is available or not
    private val isNetworkConnected: Boolean
        get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null



    @SuppressLint("StaticFieldLeak")
    inner class getDeveloper : AsyncTask<Void, Void, String>() {

        override fun onPreExecute() {
            // Show Progressbar
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg voids: Void): String {
            // Here is post Json api example
            val sendParams = HashMap<String, String>()

            // Only Get JSON api send HashMap null see below comment example
            // return ApiHelper.SendParams(GetPostJSONApi, null);
            // Send the HttpPostRequest by HttpURLConnection and receive a Results in return string
            return ApiHelper.SendParams(GetPostJSONApi, sendParams)
        }

        override fun onPostExecute(results: String?) {
            // Hide Progressbar
            progressBar.visibility = View.GONE

            if (results != null) {
                // See Response in Logcat for understand JSON Results and make DeveloperList
                Log.i("onPostExecute: ", results)
            }

            try {
                // Create JSONObject from string response if your response start from Array [ then create JSONArray
                val rootJsonObject = JSONObject(results)
                val isSucess = rootJsonObject.getString("response")
                if (isSucess == "success") {
                    val id = rootJsonObject.getString("id")
                    val name = rootJsonObject.getString("name")
                    val url = rootJsonObject.getString("url")

                    //Add data into DB
                    //AppDatabase.getInstance(this@MainActivity).superHeroDAO().updateTodo()
                    val task = SuperHeroEntity(name, url)
                    Thread(Runnable {
                        AppDatabase.getInstance(this@MainActivity).superHeroDAO().insertAll(task)
                    })

                    tvId.text = id
                    tvName.text = name
                    Glide.with(this@MainActivity)
                            .load(url)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imageView)

                    // hash map for single jsonObject you can create model.
                    val mHash = HashMap<String, String>()
                    // adding each child node to HashMap key => value/data
                    // Now I'm adding some extra text in value
                    mHash["ID"] = "ID: $id"
                    mHash["Name"] = "Name: $name"
                    mHash["URL"] = "URL: $url"
                    // Adding HashMap pair list into developer list
                    arrayList.add(mHash)



                } else {
                    Toast.makeText(applicationContext, "No data in API found.", Toast.LENGTH_SHORT).show()
                }

            } catch (e: JSONException) {
                Toast.makeText(applicationContext, "Something wrong. Try Again.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }catch (e1: Exception){
                e1.printStackTrace()
            }
        }
    }


}

