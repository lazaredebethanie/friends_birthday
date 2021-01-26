package fr.f5rpn.dbaccessv2.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import fr.f5rpn.dbaccessv2.Person
import fr.f5rpn.dbaccessv2.R
import fr.f5rpn.dbaccessv2.SqlLiteCallDB
import fr.f5rpn.dbaccessv2.SqlLiteHelper
import kotlinx.android.synthetic.main.main_screen.view.*
import org.jetbrains.anko.*

class AddFragment : Fragment(), AnkoLogger {

    private lateinit var txtNameLoc: EditText
    private lateinit var txtFirstNameLoc: EditText
    private lateinit var txtBirthdayLoc: EditText
    private lateinit var btnEraseLoc: Button
    private lateinit var btnAddLoc: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_screen, container, false)
        txtNameLoc = root.findViewById(R.id.txtName)

        txtFirstNameLoc = root.findViewById(R.id.txtFirstName)
        txtBirthdayLoc = root.findViewById(R.id.txtBirthday)
        btnEraseLoc = root.findViewById(R.id.btnErase)
        btnAddLoc = root.findViewById(R.id.btnCommonAdd)

        btnEraseLoc.setOnClickListener {
            erase()
        }

        btnAddLoc.setOnClickListener {
            addPerson() //txtNameLoc,txtFirstNameLoc,txtAgeLoc
        }

        root.btnCommonDelete.visibility=View.INVISIBLE
        root.btnCommonUpdate.visibility=View.INVISIBLE
        root.layoutSearch.visibility=View.INVISIBLE

        return root
    }

    private fun addPerson () { //txtName: EditText, txtFirstName: EditText, txtAge: EditText
        val name=txtNameLoc.text.toString()
        val firstName=txtFirstNameLoc.text.toString()
        val birthday=txtBirthdayLoc.text.toString()
        if (birthday.equals("") || (name.equals("") && firstName.equals(""))) {
            Toast.makeText(activity, "Give Name and/or First Name and Age", Toast.LENGTH_LONG).show()
        } else {
            val ctx = requireContext()
            val sqlLiteCallDB by lazy { SqlLiteCallDB(SqlLiteHelper(ctx)) }
            var person = Person(0, name, firstName, birthday, 1)
            var rc = sqlLiteCallDB.savePerson(person)
            info("Return code after insert in database : " + rc.toString())
            if (rc > 0) {
                //longToast("Friend added !")
                Toast.makeText(activity, "Friend added !", Toast.LENGTH_LONG).show()
                erase()

            }
        }
    }

    private fun erase() {
        txtNameLoc.setText("")
        txtFirstNameLoc.setText("")
        txtBirthdayLoc.setText("")

    }

}