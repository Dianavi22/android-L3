package fr.jado.movies
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.jado.movies.login.LoginState
import fr.jado.movies.login.LoginViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class LoginListViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Test
    fun `load login success state`() {
        val viewModel = LoginViewModel()
        viewModel.login( "Login",  "rock")
        Assert.assertEquals(LoginState.Success,
            viewModel.stateLiveData.value)
    }
    @Test
    fun `load login failure state`() {
        val viewModel = LoginViewModel()
        viewModel.login( "errors",  "rock")
        Assert.assertEquals(LoginState.Failure("Nope"),
            viewModel.stateLiveData.value)
    }
}