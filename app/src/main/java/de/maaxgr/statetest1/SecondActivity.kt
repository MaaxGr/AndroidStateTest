package de.maaxgr.statetest1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        val tv = findViewById<TextView>(R.id.textView)
        tv.text = TestApplication.singleton.globalState.toString()

        val tv2 = findViewById<TextView>(R.id.textView2)
        tv2.text = TestApplication.singleton.globalState2

        val tv3 = findViewById<TextView>(R.id.textView3)
        tv3.text = TestApplication.singleton.globalState3

    }

}