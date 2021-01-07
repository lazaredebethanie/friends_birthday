package fr.f5rpn.dbaccessv2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface ServiceSvrDB {
    companion object {
        //const val url="http://192.168.1.93:81/"
        const val url="https://www2.seanet.info/"
    }

    /*@GET("db_json.py")
    fun result(): Call<Personnage>*/

    @GET("{complement}")
    fun resultById(@Path("complement")complement:String, @Query("id")id:String):
            Call<Person>

    @GET("{complement}")
    fun insert(@Path("complement")complement:String,
               @Query("firstname")firstName:String,
               @Query("name")name:String,
               @Query("age")age:String):
            Call<Person>

    @GET("{complement}")
    fun result(@Path("complement")complement:String):
            Call<Person>

    @GET("{complement}")
    fun resultId(@Path("complement")complement:String):Call<Id>

}