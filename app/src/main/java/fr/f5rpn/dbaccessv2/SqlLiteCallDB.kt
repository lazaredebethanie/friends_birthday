package fr.f5rpn.dbaccessv2

import android.util.EventLogTags
import org.jetbrains.anko.db.*

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
            SqlliteTablePerson.AGE to person.birthday,
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
            SqlliteTablePerson.AGE to person.birthday,
            SqlliteTablePerson.CHANGE to person.change)
            .whereSimple(SqlliteTablePerson.ID + " = ?",person.idPerson.toString())
            .exec()
    }


    fun selectLastId() =dbHelper.use {
        select (SqlliteTablePerson.NAME,
            SqlliteTablePerson.ID)
            .orderBy(SqlliteTablePerson.ID, SqlOrderDirection.DESC)
            .limit(1)
            .parseSingle(IntParser)
    }
}