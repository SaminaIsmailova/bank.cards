import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack.Dimensions
import com.example.jetpack.R
import com.example.jetpack.model.Users
import com.example.jetpack.ui.Registration.Companion.DATABASENAME
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SplashScreen {

    private val auth=FirebaseAuth.getInstance()
    private val myDatabase=Firebase.database.getReference(DATABASENAME)
    @Preview(showSystemUi = true)
    @Composable
    fun Splash(onClick: () -> Unit = {}) {

        val scope = rememberCoroutineScope()

        val userState = getData(scope).collectAsState(initial = null)

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
                Text(text = userState.value?.fName.toString())
            }
        }
    }
    fun getData(scope: CoroutineScope) = flow {
        val user = auth.currentUser
        val uid = user?.uid
        if (uid != null) {
            myDatabase.child(uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue(Users::class.java)
                    if (value != null) {
                        scope.launch{
                            emit(value)
                        }
                    } else {
                        Log.e("ololo", "onDataChange: value is null")
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                }

            })

        }
    }
}
