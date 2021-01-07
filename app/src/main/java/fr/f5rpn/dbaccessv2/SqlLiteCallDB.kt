package fr.f5rpn.dbaccessv2

import android.database.sqlite.SQLiteDatabase
import fr.f5rpn.dbaccessv2.SqlliteTablePerson.NAME
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class SqlLiteCallDB (private val dbHelper: SqlLiteHelper){
    fun requestIdPerson() = dbHelper.use {
        select (SqlliteTablePerson.NAME,
        SqlliteTablePerson.ID,
        SqlliteTablePerson.NAME_PERSON,
        SqlliteTablePerson.FIRST_NAME,
        SqlliteTablePerson.AGE)
            .parseList(classParser<Person>())
    }

    fun savePerson(person: Person) = dbHelper.use {
        insert(SqlliteTablePerson.NAME,
            SqlliteTablePerson.NAME_PERSON to person.name,
            SqlliteTablePerson.FIRST_NAME to person.firstName,
            SqlliteTablePerson.AGE to person.age,
            SqlliteTablePerson.ID_SVR to person.id
        )
    }

    fun savePeople(personLst: List<Person>) {
        for (p in personLst)
            savePerson(p)
    }
}