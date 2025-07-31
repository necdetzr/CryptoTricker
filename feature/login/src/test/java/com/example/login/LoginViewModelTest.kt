package com.example.login

import com.example.network.manager.FirebaseAuthManager
import com.necdetzr.datastore.model.DataStoreManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    private val authManager: FirebaseAuthManager = mockk()
    private val dataStoreManager: DataStoreManager = mockk(relaxed = true)

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup(){
        viewModel = LoginViewModel(authManager,dataStoreManager)
    }

    @Test
    fun successful_login_updates_state_to_Success() = runTest {
        coEvery { authManager.login("test@gmail.com","1234") }returns Result.success(Unit)
        viewModel.signIn("test@gmail.com","1234")
        advanceUntilIdle()
        assert(viewModel.uiState.value.loginStatus is LoginStatus.Success)
        assert(!viewModel.uiState.value.loading)
    }

    @Test
    fun failed_login_updates_state_to_Failure() = runTest {
        coEvery { authManager.login("fail@example.com","fail") }returns Result.failure(Exception())
        viewModel.signIn("fail@example.com","fail")
        advanceUntilIdle()
        val state = viewModel.uiState.value
        assert(state.loginStatus is LoginStatus.Error)
        assertEquals("Login Failed",(state.loginStatus as LoginStatus.Error).message)
    }
    @Test
    fun updateRememberMe_updates_state_and_calls_DataStoreManager() = runTest {
        viewModel.updateRememberMe(true)
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value.isRemember)
        coVerify { dataStoreManager.setRemember(true) }
    }
    @Test
    fun onEmailChange_updates_state()=runTest {
        viewModel.onEmailChange("testmail@example.com")
        assertEquals("testmail@example.com",viewModel.uiState.value.email)
    }
    @Test
    fun onPasswordChange_updates_state()= runTest {
        viewModel.onPasswordChange("12345")
        assertEquals("12345",viewModel.uiState.value.password)
    }
    @Test
    fun login_flow_updates_loading_and_states_correctly() = runTest {
        coEvery { authManager.login(any(),any()) }returns Result.success(Unit)

        val states = mutableListOf<LoginViewState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect { states.add(it) }
        }
        viewModel.onEvent(LoginEvent.OnSignInEvent("test@gmail.com","1234"))
        advanceUntilIdle()

        assertEquals(3,states.size)
        assertEquals(false,states[0].loading)
        assertEquals(true,states[1].loading)
        assertEquals(false,states[2].loading)
        assertTrue(states[2].loginStatus is LoginStatus.Success)
    }
    @Test
    fun login_flow_with_empty_email_and_password() = runTest {
        coEvery { authManager.login(any(),any()) } returns Result.failure(Exception())

        val states = mutableListOf<LoginViewState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)){
            viewModel.uiState.collect { states.add(it)}
        }
        viewModel.onEvent(LoginEvent.OnSignInEvent("",""))
        advanceUntilIdle()

        assertEquals(3,states.size)
        assertEquals(false,states[0].loading)
        assertEquals(true,states[1].loading)
        assertEquals(false,states[2].loading)
        assertTrue(states[2].loginStatus is LoginStatus.Error)

    }


}



@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule : TestWatcher() {
    private val dispatcher = StandardTestDispatcher()

    override fun starting(description: Description?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }

    fun getTestDispatcher() = dispatcher
}
