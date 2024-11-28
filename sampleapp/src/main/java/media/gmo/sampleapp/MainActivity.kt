package media.gmo.sampleapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import media.gmo.sampleapp.ui.theme.SDKSampleTheme
import media.gmo.sdksample.PlayBox

class MainActivity : ComponentActivity() {
    private val viewModel: SampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val view = View(this)
        view.setOnClickListener {

        }
        PlayBox.init(
            key = "",
            options = PlayBox.Options().setUserId(""),
            listener = object : PlayBox.PlayBoxInitializationListener() {
                override fun onSuccess() {
                    super.onSuccess()
                    Log.d("TEST", "onSuccess")
                    viewModel.setButtonState(true)
                }

                override fun onError(error: Throwable) {
                    super.onError(error)
                    Log.d("TEST", "onError")
                    viewModel.setButtonState(false)
                }
            }
        )

        enableEdgeToEdge()
        setContent {
            SDKSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Spacer(modifier = Modifier.padding(32.dp))
                        SampleScreen(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = viewModel,
                            onClick = {
                                val playBoxCatalogIntent = PlayBox.getCatalogIntent(this@MainActivity)
                                startActivity(playBoxCatalogIntent);
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SampleScreen(
    modifier: Modifier = Modifier,
    viewModel: SampleViewModel,
    onClick: () -> Unit = {}
) {
    val buttonState by viewModel.buttonState

    Button(
        onClick = {
            Log.d("TEST", "onClick")
            onClick()
        },
        enabled = buttonState
    ) {
        Text("Offerwall")
    }
}

class SampleViewModel: ViewModel() {
    private val _buttonState = mutableStateOf(false)
    val buttonState: State<Boolean> = _buttonState

    fun setButtonState(isEnabled: Boolean) {
        _buttonState.value = isEnabled
    }
}

@Preview(showBackground = true)
@Composable
fun SampleScreenPreview() {
    SDKSampleTheme {
        SampleScreen(viewModel = SampleViewModel())
    }
}