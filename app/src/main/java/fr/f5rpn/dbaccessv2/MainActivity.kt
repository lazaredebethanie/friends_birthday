package fr.f5rpn.dbaccessv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickGo (view: View) {
        val mainScreen = Intent(this,DBAccessActivity::class.java)
        startActivity(mainScreen)
    }

    /* OLD FUNCTIONS, MAYBE NOT USED, KEEP DURING THE DEV OF NEW VERSION
    private fun loadLocalDBOLD () {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceSvrDB.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serviceDB = retrofit.create(ServiceSvrDB::class.java)

        val callService = serviceDB.resultId("dba_selectallid.py")

        Thread {
            val reponse = callService.execute()

            val idListRet = reponse.body()


            runOnUiThread {
                val idList = idListRet!!.id
                // Init spinner
                val sqlLiteCallDB by lazy { SqlLiteCallDB(SqlLiteHelper(applicationContext)) }
                var list = listOf<Person>()
                initLocalDBwithId(idList,sqlLiteCallDB)
                initSpinner(list, sqlLiteCallDB)


            }
        }.start()
    }

    private fun initLocalDBwithIdOLD (idList: MutableList<String>, sqlLiteCallDB: SqlLiteCallDB ) {
        //val SqlLiteCallDB by lazy { SqlLiteCallDB(SqlLiteHelper(applicationContext)) }
        //var list = listOf<Person>()
        for (id in idList) {
            doAsync {
                val person = Person(id.toInt(),"None","None","None", 0)
                sqlLiteCallDB.savePerson(person)
            }
        }
    }

    private fun initSpinnerOLD (list: List<Person>, sqlLiteCallDB: SqlLiteCallDB) {
        doAsync {
            var list = sqlLiteCallDB.requestIdPerson()
            print(list.toString())
        }
    }
     */

}