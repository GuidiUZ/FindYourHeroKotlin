package com.example.findyourhero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findyourhero.DetailsSuperHeroActivity.Companion.EXTRA_ID
import com.example.findyourhero.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retroFit: Retrofit
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retroFit = getRetrofit()
        initUi()

    }

    private fun initUi() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            //no se usa
            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperHeroAdapter{ superHeroId -> navigateToDetails(superHeroId)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {

            val myResponse: Response<SuperHeroDataResponse> =
                retroFit.create(ApiService::class.java).getSuperHeros(query)
            if (myResponse.isSuccessful) {
                Log.i("guidiuz", "Funciona!")

                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("guidiuz", response.toString())

                    runOnUiThread {
                        adapter.updateList(response.superHeroes)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("guidiuz", "No funciona!")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun navigateToDetails(id:String) {
        val intent = Intent(this, DetailsSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

}