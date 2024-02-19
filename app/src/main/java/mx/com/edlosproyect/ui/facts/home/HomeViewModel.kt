package mx.com.edlosproyect.ui.facts.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.edlosproyect.data.local.model.ResultsModel
import mx.com.edlosproyect.domain.FactsRepository
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: FactsRepository) : ViewModel() {
    val factsLiveData = MutableLiveData<MutableList<ResultsModel>>()
    var resultLiveData = MutableLiveData<ResultsModel>()
    var count = 0

    fun getPage() {
        viewModelScope.launch {
            if (count == 0) {
                factsLiveData.value = repository.getPage(10, count)
                count++
            } else {
                val list = repository.getPage(10, count)
                val result = factsLiveData.value
                for (i in list) {
                    result?.add(i)
                }
                factsLiveData.value = result!!
                count++
            }
        }
    }

    fun getFact(Id: String) {
        viewModelScope.launch {
            resultLiveData.value = repository.getFact(Id)
        }
    }
}