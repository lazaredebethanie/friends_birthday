package fr.f5rpn.dbaccessv2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class SqlLiteHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx,
    "essais",null,2) {

    companion object
    {
        private var instance:SqlLiteHelper?=null

        @Synchronized
        fun getInstance(ctx:Context):SqlLiteHelper
        {
            if(instance==null)
            {
                instance = SqlLiteHelper(ctx)
            }
            return instance as SqlLiteHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(SqlliteTablePerson.NAME,
            false,
            SqlliteTablePerson.ID to INTEGER + PRIMARY_KEY,
            SqlliteTablePerson.ID_SVR to INTEGER,
            SqlliteTablePerson.NAME_PERSON to TEXT,
            SqlliteTablePerson.FIRST_NAME to TEXT,
            SqlliteTablePerson.AGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(SqlliteTablePerson.NAME,true)
        onCreate(db)
    }

}

val Context.database : SqlLiteHelper
    get() = SqlLiteHelper.getInstance(applicationContext)