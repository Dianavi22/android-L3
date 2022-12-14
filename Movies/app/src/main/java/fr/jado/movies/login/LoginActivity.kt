package fr.jado.movies.login

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import fr.jado.movies.databinding.ActivityLoginBinding
import fr.jado.movies.movieList.MovieListActivity
import fr.jado.movies.movieList.MovieListViewModel


const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val model: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model.stateLiveData.observe(this){stateLiveData -> updateUi(stateLiveData!!)}
        binding.loginButton.setOnClickListener{
        model.login(binding.usernameEditText.text.toString(),binding.passwordEditText.text.toString())
        }

    }

    private fun updateUi(state: LoginState) {
         when (state){
            is LoginState.Failure -> {binding.errorMessageText.setVisibility(View.VISIBLE);}
            is LoginState.Success -> {
                Toast.makeText(this, "Login OK ! Navigation vers la liste des films", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MovieListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}