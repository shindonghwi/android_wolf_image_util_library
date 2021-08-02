package dh.wolf.wolf_imageutil_lib

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WolfActivity(private val listener: WolfListener) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        listener.call(data)
        finish()
    }
}