package fr.f5rpn.dbaccessv2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class SqlLiteHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx,
    "essais.db",null,7) {

    init {
        instance = this
    }

    companion object {
        private var instance: SqlLiteHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): SqlLiteHelper {
            if (instance == null) {
                instance = SqlLiteHelper(ctx)
            }
            return instance as SqlLiteHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            SqlliteTablePerson.NAME,
            true,
            /*SqlliteTablePerson.ID to INTEGER + PRIMARY_KEY,*/
            SqlliteTablePerson.ID to INTEGER + PRIMARY_KEY,
            SqlliteTablePerson.NAME_PERSON to TEXT,
            SqlliteTablePerson.FIRST_NAME to TEXT,
            SqlliteTablePerson.AGE to TEXT,
            SqlliteTablePerson.CHANGE to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(SqlliteTablePerson.NAME, true)
        onCreate(db)
    }

}
val Context.database : SqlLiteHelper
    get() = SqlLiteHelper.getInstance(this)