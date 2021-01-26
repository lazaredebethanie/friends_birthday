package fr.f5rpn.dbaccessv2

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferencesName
import android.widget.Toast
import androidx.preference.PreferenceManager
import org.jetbrains.anko.defaultSharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RequestToDB constructor(context: Context) {

    val ctx=context


    fun synchronize (id: Int) {
        val prefs = PreferenceManagerApp(ctx)
        val url= prefs.getServer()

        Toast.makeText(ctx, url,Toast.LENGTH_LONG).show()

        val retrofit = Retrofit.Builder()
            .baseUrl(url) //.baseUrl(ServiceSvrDB.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serviceDB = retrofit.create(ServiceSvrDB::class.java)


    }

}