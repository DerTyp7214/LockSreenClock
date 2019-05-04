package de.dertyp7214.lockscreenclockswitcher.screens

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.dertyp7214.lockscreenclockswitcher.R
import de.dertyp7214.lockscreenclockswitcher.core.getTextViewTitle
import de.dertyp7214.lockscreenclockswitcher.core.runAsCommandWithResponse
import de.dertyp7214.lockscreenclockswitcher.fragments.Settings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)

        if ("getprop ro.build.version.release".runAsCommandWithResponse() != "Q") throw TooLow()

        window.navigationBarDividerColor = Color.LTGRAY

        toolbar.getTextViewTitle()?.setTextColor(Color.BLACK)
        toolbar.title = getString(R.string.app_name)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, Settings())
            commit()
        }
    }
}

class TooLow : Exception("Android Version is to low! Minimum is android Q")