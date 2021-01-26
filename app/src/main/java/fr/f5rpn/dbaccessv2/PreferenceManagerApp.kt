package fr.f5rpn.dbaccessv2

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import org.jetbrains.anko.defaultSharedPreferences

class PreferenceManagerApp constructor(context: Context) {
        //private val PREFS_NAME = "fr.f5rpn.dbaccessv2_preferences.xml"
        //var preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        private var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getServer(): String? {
            return preferences.getString("server","def.www2.seanet.info")
            //return preferences[SERVER] ?: ""
        }
    fun getSynchro(): String? {
            return preferences.getString("synchro","false")
            //return preferences[SYNCHRO] ?: ""
        }
    fun clearPrefs() {
            preferences.edit().clear().apply()
        }
    }