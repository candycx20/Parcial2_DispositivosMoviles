package com.example.superheroapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.DetailSuperheroActivity.Companion.EXTRA_ID
import com.example.superheroapi.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // View Binding
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        // Iniciamos el SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        // Inicializamos el adaptador
        adapter = SuperHeroAdapter { pokemonName -> navigateToDetail(pokemonName) }
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            // Realiza la solicitud a la PokeAPI
            val myResponse: Response<PokemonDataResponse> =
                retrofit.create(ApiService::class.java).getPokemon(query)

            if (myResponse.isSuccessful) {
                Log.i("Gaby", "Funciona :)")
                val response: PokemonDataResponse? = myResponse.body()

                if (response != null) {
                    Log.i("Gaby", response.toString())
                    runOnUiThread {
                        // Actualizamos la lista del adaptador con los resultados
                        adapter.updateList(listOf(PokemonResults(response.name, response.sprites.frontDefault)))
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("Gaby", "No funciona :(")
                runOnUiThread {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")  // Cambiado a la URL base de PokeAPI
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(pokemonName: String) {
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, pokemonName)
        startActivity(intent)
    }
}