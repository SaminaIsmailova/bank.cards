import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack.Dimensions
import com.example.jetpack.R

class SplashScreen {
    @Preview(showSystemUi = true)
    @Composable
    fun ShowSplashScreen(onClick: () -> Unit = {}) {

        var timeFinished by remember {
            mutableStateOf(false)
        }

        DisposableEffect(Unit) {
            val timer = object : CountDownTimer(4000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                }

                override fun onFinish() {
                    timeFinished = true
                }
            }

            timer.start()

            onDispose {
                timer.cancel()
            }
        }

        if (timeFinished) {
            onClick()
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.welcome),
                    fontSize = Dimensions.TextSize.size_20,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.black)
                )
            }
        }
    }
}
