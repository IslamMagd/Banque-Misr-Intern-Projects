package com.example.infinitydogs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.infinitydogs.api.Dog
import com.example.infinitydogs.api.DogAPICallable
import com.example.infinitydogs.ui.theme.InfinityDogsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfinityDogsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DogImage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DogImage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var isLoading by rememberSaveable { mutableStateOf(true) }
    var dog by remember { mutableStateOf<Dog?>(null) }

    LaunchedEffect(Unit) {
        getDogImage {
            if(it != null)
                dog = it
            else
                Toast.makeText(context, "Failure happened", Toast.LENGTH_SHORT).show()

            isLoading = false
        }
    }
    if (!isLoading){
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(dog?.imageLink)
                .placeholder(R.drawable.ic_downloading)
                .crossfade(700)
                .build(),
            contentDescription = "Dog image", modifier = modifier.fillMaxSize())
    }
}

fun getDogImage(onRecieved: (Dog?) -> Unit){
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val c = retrofit.create(DogAPICallable::class.java)
    c.getDogImage().enqueue(object: Callback<Dog>{
        override fun onResponse(p0: Call<Dog>, p1: Response<Dog>) {
            val dog = p1.body()
            onRecieved(dog)
        }

        override fun onFailure(p0: Call<Dog>, p1: Throwable) {
           onRecieved(null)
        }

    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}