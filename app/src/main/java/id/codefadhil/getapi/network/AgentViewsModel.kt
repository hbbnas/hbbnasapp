package id.hbbnas.getapi.network

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hbbnas.getapi.data.Agents
import id.hbbnas.getapi.data.DataItem
import id.hbbnas.getapi.data.Weapon
import com.example.valorant.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AgentsViewModel : ViewModel() {
    private val _agentsData : MutableStateFlow<List<DataItem>> = MutableStateFlow(listOf())
    val agentsData : StateFlow<List<DataItem>> = _agentsData
    var selectedAgents by mutableStateOf<DataItem?>(value = null)
        private set

    var errorString : String = " "
    var loading: Boolean by mutableStateOf(false)

    init{
        retrieveAgentsData()
    }
    private fun retrieveAgentsData(){
        viewModelScope.launch {
            loading = true
            val call : Call<Agents> = RetrofitInstance.apiService.getAllAgents()
            call.enqueue(object : Callback<Agents> {
                override fun onResponse(
                    call: Call<Agents>,
                    response: Response<Agents>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<DataItem>? = response.body()?.data
                        if(responseData != null){
                            _agentsData.value = responseData.filter { dataItem ->  dataItem.role?.displayName != null }
                        }
                        loading = false
                    } else{
                        loading = false
                    }
                }

                override fun onFailure(call: Call<Agents>, t: Throwable) {
                    loading = false
                    Log.d("Failed Retrieve", "Network Error")
                }

            })
        }
    }

}
