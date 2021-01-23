package fr.f5rpn.dbaccessv2

import android.database.sqlite.SQLiteDatabase
import android.provider.SyncStateContract.Helpers.update
import fr.f5rpn.dbaccessv2.SqlliteTablePerson.NAME
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import java.nio.file.Files.delete

class SqlLiteCallDB (private val dbHelper: SqlLiteHelper){

    fun requestAllPerson() = dbHelper.use {
        select (SqlliteTablePerson.NAME,
        SqlliteTablePerson.ID,
        SqlliteTablePerson.NAME_PERSON,
        SqlliteTablePerson.FIRST_NAME,
        SqlliteTablePerson.AGE,
        SqlliteTablePerson.CHANGE)
            .parseList(classParser<Person>())
    }

    fun requestAllName() = dbHelper.use {
        select (SqlliteTablePerson.NAME,
            SqlliteTablePerson.NAME_PERSON,
            SqlliteTablePerson.FIRST_NAME
        ).parseList(classParser<PersonLight>())
    }


        fun savePerson(person: Person) = dbHelper.use {
        insert(SqlliteTablePerson.NAME,
            SqlliteTablePerson.NAME_PERSON to person.name,
            SqlliteTablePerson.FIRST_NAME to person.first_name,
            SqlliteTablePerson.AGE to person.age,
            SqlliteTablePerson.CHANGE to person.change
        )
    }

    fun savePeople(personLst: List<Person>, _id:Int) {
        for (p in personLst)
            savePerson(p)
    }

    fun updatePerson (person: Person) = dbHelper.use {
        update(SqlliteTablePerson.NAME,
            SqlliteTablePerson.NAME_PERSON to person.name,
            SqlliteTablePerson.FIRST_NAME to person.first_name,
            SqlliteTablePerson.AGE to person.age)
            .whereSimple(SqlliteTablePerson.ID + " = ?",person.idPerson.toString())
            .exec()
    }


    /*fun updatePerson(person: Person) =dbHelper.use {
        update(SqlliteTablePerson.NAME,
            SqlliteTablePerson.NAME_PERSON  to person.name)
             .where(SqlliteTablePerson.ID_SVR +"=",1)
            .exec()
        )
    }*/
}