package com.example.apidisney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.apidisney.databinding.ActivityMainBinding
import com.example.apidisney.json.DisneyResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<DisneyResponse> = getRetrofit().create(APIService::class.java).getDisney("/characters?name="+binding.editTextTextPersonName.text.toString())
                val disney = call.body()
                if (call.isSuccessful){
                    //Mostramos la imagen
                    val resultado = disney?.data ?: emptyList()
                    Glide.with(binding.imageView.context).load(resultado[0].imageUrl).into(binding.imageView)
                }
                else{
                    Toast.makeText(this@MainActivity, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.disneyapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}