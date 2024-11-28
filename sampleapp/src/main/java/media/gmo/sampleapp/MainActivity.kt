package media.gmo.sampleapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import media.gmo.sampleapp.ui.theme.SDKSampleTheme
import media.gmo.sdksample.PlayBox

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val view = View(this)
        view.setOnClickListener {

        }
        PlayBox.init(
            key = "",
            options = PlayBox.Options().setUserId("error"),
            listener = object : PlayBox.PlayBoxInitialisationListener() {
                override fun onInitialised() {
                    Log.d("TEST", "onInitialised")
                }

                override fun onError(error: Throwable) {
                    super.onError(error)
                    Log.d("TEST", "onError")
                }
            }
        )

        enableEdgeToEdge()
        setContent {
            SDKSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SDKSampleTheme {
        Greeting("Android")
    }
}