package fr.jado.movies.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class LoginState{
    object Success : LoginState()
    data class Failure(val errorMessage: String): LoginState()
}

class LoginViewModel: ViewModel() {
val stateLiveData = MutableLiveData<LoginState>()
    fun login(username: String, password: String){
        if(validLogin(username, password)){
            stateLiveData.value = LoginState.Success

          //  Log.i(TAG, "Success")
        }else{
            stateLiveData.value = LoginState.Failure("Nope")
      //      Log.i(TAG, "Failure")
         //   Log.i(TAG, "$username $password")

        }
    }
    private fun validLogin(username: String, password: String): Boolean = username == "Login" && password == "rock"

//private fun navigateToMovieList(){
//    val intent = Intent(this, MovieListActivity::class.java)
//    startActivity(intent)
//    finish()
//}

}