package dh.wolf.wolf_imageutil_lib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WolfActivity : Activity() {
    lateinit var listener: WolfListener

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        listener.call(data)
        finish()
    }

    fun setWolfListener(wolfListener: WolfListener) {
        listener = wolfListener
    }
}