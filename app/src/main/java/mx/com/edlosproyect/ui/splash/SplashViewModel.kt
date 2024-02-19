package mx.com.edlosproyect.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.com.edlosproyect.data.local.model.ResultsModel
import mx.com.edlosproyect.domain.FactsRepository
import mx.com.edlosproyect.sys.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: FactsRepository) : ViewModel() {

    val petition = MutableLiveData<Resource<ArrayList<ResultsModel>>>()
    fun getFacts() {
        viewModelScope.launch {
            petition.value = Resource.loading(null)
            delay(1000)
            petition.value = repository.getFacts()
        }
    }

    fun saveFacts(list: List<ResultsModel>) {
        viewModelScope.launch {
            repository.insertFacts(list)
        }
    }
}