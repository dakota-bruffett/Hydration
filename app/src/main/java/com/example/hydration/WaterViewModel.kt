package com.example.hydration
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WaterViewModel(private val repository:WaterRespiratory ):ViewModel() {

        val allRecords = repository.getAllRecords().asLiveData()

        fun insertNewRecord(record:WaterRecord){
                viewModelScope.launch {
                        repository.insert(record)
                }
        }
        fun updateRecord(record: WaterRecord){
                viewModelScope.launch {
                        repository.update(record)
                }
        }
        fun deleteRecord(record: WaterRecord){
                viewModelScope.launch {
                        repository.delete(record)
                }
        }
        fun getRecordForDay(day: String): LiveData<WaterRecord>{
                return repository.getRecordForDay(day).asLiveData()
        }
}
class WaterViewModelFactory(private val repository: WaterRespiratory): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(WaterViewModel::class.java)) {
                        return WaterViewModel(repository) as T
                }
                throw IllegalStateException("$modelClass What are you doing this is not a WaterViewModel")
        }
}
