package com.example.webc
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import java.net.URL
import kotlinx.coroutines.*
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO + job).launch {
            val WebSource = async {
                GetWebSource("https://www.ulsan.go.kr/u/rep/main.ulsan") }

            withContext(Dispatchers.Main) {
                var tv=findViewById(R.id.textView) as TextView
                tv.setMovementMethod(ScrollingMovementMethod() )
             tv.setText(WebSource.await())

            }
        }

    }

    private suspend fun GetWebSource(url:String): String {
        val response = URL(url).readText().toString()
        return response
    }
}
