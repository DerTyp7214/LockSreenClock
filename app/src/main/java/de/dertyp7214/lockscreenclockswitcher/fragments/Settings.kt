package de.dertyp7214.lockscreenclockswitcher.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.dertyp7214.preferencesplus.preferences.BottomSheetPreference
import de.dertyp7214.lockscreenclockswitcher.R
import de.dertyp7214.lockscreenclockswitcher.core.runAsCommand

class Settings : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)

        val pref = findPreference<BottomSheetPreference>("list")
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)

        val arrayE = resources.getStringArray(R.array.entries).toList()
        val arrayV = resources.getStringArray(R.array.values).toList()

        val item = prefs.getString("list", "null")

        pref?.value = try { arrayV[arrayE.indexOf(item)] } catch (e: Exception) { item }
        pref?.summary = pref?.value
        pref?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue == "null") "settings delete secure lock_screen_custom_clock_face".runAsCommand()
            else "settings put secure lock_screen_custom_clock_face \"$newValue\"".runAsCommand()
            pref.summary = arrayV[arrayE.indexOf(newValue.toString())]
            pref.value = try { arrayV[arrayE.indexOf(newValue)] } catch (e: Exception) { newValue.toString() }
            true
        }

        return v
    }
}