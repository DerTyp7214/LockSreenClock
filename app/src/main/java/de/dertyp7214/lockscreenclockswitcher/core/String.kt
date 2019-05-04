package de.dertyp7214.lockscreenclockswitcher.core

import android.util.Log
import com.jaredrummler.android.shell.Shell

fun String.runAsCommand(): Boolean {
    Log.d("COMMAND", this)
    val result = Shell.SU.run(this)
    Log.d("COMMAND", "${result.getStdout()} ${result.exitCode}")
    return result.isSuccessful
}

fun String.runAsCommandWithResponse(): String {
    Log.d("COMMAND", this)
    val result = Shell.SU.run(this)
    Log.d("COMMAND", "${result.getStdout()} ${result.exitCode}")
    return result.getStdout()
}