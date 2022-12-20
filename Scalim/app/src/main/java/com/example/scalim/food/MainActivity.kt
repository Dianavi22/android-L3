package com.example.scalim.food

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scalim.details.DetailsActivity
import com.example.scalim.Food
import com.example.scalim.FoodWrapper
import com.example.scalim.R
import com.example.scalim.api.ProductsApi
import com.example.scalim.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: FoodListViewModel by viewModels()
    private lateinit var adapter: FoodAdapter
    private var floatingButtonMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FoodAdapter(listOf())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        model.getFoodLiveData().observe(this, { foods -> updateFood(foods) })
        model.loadFood()

        binding.floatingActionButton.setOnClickListener {
            if (floatingButtonMode) {
                barcodeLauncher.launch(ScanOptions())
            } else {
                Log.i("MainActivity", "Add item ")
                model.addFood(Food("1234", "DrPapier", "Demain", "https://i.ytimg.com/vi/vsaC5nCBDZc/maxresdefault.jpg"), this@MainActivity)
                model.loadFood()
            }
        }

        binding.switchBooleanButton.setOnClickListener {
            floatingButtonMode = !floatingButtonMode
            Log.i("MainActivity", "updateBoolean: $floatingButtonMode")
        }

        adapter.onItemClick = {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }

        adapter.onItemDelete =  {
            model.deleteFood(it, this@MainActivity)
            Log.i("MainActivity", "PRODUCT: ${it.code}")
            model.loadFood()
        }
    }

    private fun updateUi(state: FoodListViewModel.AddState) {
        return when(state) {
            is FoodListViewModel.AddState.Failure -> {
                Toast.makeText(this, "Operation failed \uD83E\uDD2C", Toast.LENGTH_SHORT).show()
            }
            is FoodListViewModel.AddState.Success -> {
                Toast.makeText(this, "Operation success", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun updateFood(foods: List<Food>) {
        adapter.updateDataSet(foods)
        model.stateLiveData.observe(this) { state ->
            updateUi(state!!)
        }
    }

    // Register the launcher and result handler
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        val retrofit = Retrofit.Builder()
            .baseUrl("https://world.openfoodfacts.org/api/v2/product/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ProductsApi::class.java)

        if (result.contents == null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            Log.i("MainActivity", "onResponse: ${result.contents}")

            val call = api.getProduct(result.contents)
            Log.i("MainActivity", "call: $call")

            call.enqueue(object : Callback<FoodWrapper> {

                override fun onResponse(call: Call<FoodWrapper>, response: Response<FoodWrapper>) {


                    val products = response.body()!!

                    Log.i("MainActivity", "onResponse: $products")
                    val code = products.product.code
                    val name = products.product.genericName
                    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                    val imgUrl = products.product.imageUrl
                    if (code == null) {
                        Toast.makeText(
                            this@MainActivity,
                            "Somethings went wrong with the scan",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.i("MainActivity", "Scan failed: $code")
                    } else {
                        model.addFood(Food(code, name, date, imgUrl), this@MainActivity)
                        model.loadFood()
                    }
                }

                override fun onFailure(call: Call<FoodWrapper>, t: Throwable) {
                    Log.e("MainActivity", "onFailure: ", t)
                }
            })
        }

    }
}
