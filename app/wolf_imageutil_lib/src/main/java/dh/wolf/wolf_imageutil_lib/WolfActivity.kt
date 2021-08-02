package dh.wolf.wolf_imageutil_lib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WolfActivity : AppCompatActivity() {
    lateinit var listener: WolfListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        listener.call(data)
        finish()
    }

    fun setWolfListener(wolfListener: WolfListener) {
        listener = wolfListener
    }
}