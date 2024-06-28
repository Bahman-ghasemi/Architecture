package com.example.architecture.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.architecture.model.entity.Animal
import com.example.architecture.model.remote.Const
import com.example.architecture.ui.theme.ArchitectureTheme
import com.example.architecture.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<MainViewModel>()
            ArchitectureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        MainScreen(viewModel) {
                            viewModel.fetchAnimals()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    viewModel: MainViewModel,
    onFetchClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        if (viewModel.isIdle.value)
            Button(onClick = onFetchClicked) {
                Text(text = "Fetch Animals")
            }

        if (viewModel.isLoading.value)
            CircularProgressIndicator()

        viewModel.hasError.value?.let {
            Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
        }

        if (viewModel.animals.value.isNotEmpty()) {
            ListScreen(viewModel.animals.value)
        }

    }
}

@Composable
fun ListScreen(animals: List<Animal>) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = animals, key = { it.id }) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = Const.BASE_URL + it.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Top)
                ) {
                    Text(text = it.name)
                    Text(text = it.location)
                    Text(text = it.lifespan)
                }
            }
            HorizontalDivider()
        }
    }
}