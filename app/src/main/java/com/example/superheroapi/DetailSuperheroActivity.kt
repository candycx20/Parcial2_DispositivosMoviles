package com.example.superheroapi

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperheroActivity : AppCompatActivity() {

    private lateinit var tvDetails: TextView
    private lateinit var retrofit: Retrofit

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_superhero)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvDetails = findViewById(R.id.tvDetails)
        retrofit = getRetrofit()

        val receivedId = intent.getStringExtra(EXTRA_ID)
        if (receivedId != null) {
            searchById(receivedId)
        } else {
            // Handle the case where the ID is not available (e.g., log a warning)
            Log.w("DetailActivity", "No ID received from SuperHeroListActivity")
            tvDetails.text = "Error: No superhero ID found."
        }

    }

    private fun searchById(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val myResponse: Response<SuperHeroDetailResponse> =
                    retrofit.create(ApiService::class.java).getSuperheroeDetail(query)

                if (myResponse.isSuccessful) {
                    val response: SuperHeroDetailResponse? = myResponse.body()

                    if (response != null) {
                        // Extract details from the response object
                        val name = response.name
                        val biography = response.biography // assuming biography is a property within SuperHeroDetailResponse

                        val detailsText = buildString {
                            append("Name: $name\n")
                            if (biography != null) { // Check for null before accessing properties
                                append("Full Name: ${biography.full_name}\n")
                                append("Place of Birth: ${biography.place_of_birth}\n")
                                append("Publisher: ${biography.publisher}\n")
                            } else {
                                append("Biography details not available.\n")
                            }
                        }

                        runOnUiThread {
                            tvDetails.text = detailsText
                        }
                    } else {
                        runOnUiThread {
                            tvDetails.text = "Error: Unexpected response from API."
                            Log.e("Gaby", "Unexpected response from API: $response")
                        }
                    }
                } else {
                    runOnUiThread {
                        tvDetails.text = "Error: Failed to fetch superhero details."
                        Log.e("Gaby", "Failed to fetch superhero details: ${myResponse.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    tvDetails.text = "Error: ${e.message}"
                    Log.e("Gaby", "Error fetching details: ${e.printStackTrace()}")
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}