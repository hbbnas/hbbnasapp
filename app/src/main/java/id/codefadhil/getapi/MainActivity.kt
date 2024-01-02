package id.hbbnas.getapi

import android.os.Bundle
import android.os.Debug
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import id.codefadhil.getapi.ui.theme.GetAPITheme
import id.codefadhil.getapi.network.ApiService

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState

import id.codefadhil.getapi.data.Agents
import id.codefadhil.getapi.data.DataItem
import id.codefadhil.getapi.data.Weapon
import androidx.compose.foundation.layout.Box

import id.codefadhil.getapi.network.AgentsViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetAPITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    AgentsList(AgentsViewModel())
                }
            }
        }
    }
}


@Composable
fun AgentsList( viewModel: AgentsViewModel) {
    val agentsData = viewModel.agentsData.collectAsState()
    Text(text =agentsData.value.size.toString())
    LazyColumn(

    ) {
        items(count = agentsData.value.size) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = agentsData.value[it].description.toString())
            }
        }

    }
}
