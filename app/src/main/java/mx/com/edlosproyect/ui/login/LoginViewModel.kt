package mx.com.edlosproyect.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.edlosproyect.domain.LoginRepository
import mx.com.edlosproyect.sys.utils.Resource
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {
    val loginLiveData = MutableLiveData<Resource<Boolean>>()
    fun getLogin(email: String, password: String) {
        loginLiveData.value=Resource.loading(null)
        viewModelScope.launch {
            loginLiveData.value = loginRepository.getAuthUser(email, password)
        }
    }

}