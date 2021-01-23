package fr.f5rpn.dbaccessv2.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fr.f5rpn.dbaccessv2.Person
import fr.f5rpn.dbaccessv2.R
import fr.f5rpn.dbaccessv2.SqlLiteCallDB
import fr.f5rpn.dbaccessv2.SqlLiteHelper
import kotlinx.android.synthetic.main.main_screen.view.*
import kotlinx.android.synthetic.main.person.*
import org.jetbrains.anko.*

class AddFragment : Fragment(), AnkoLogger {

    private lateinit var addViewModel: AddViewModel
    private lateinit var txtNameLoc: EditText
    private lateinit var txtFirstNameLoc: EditText
    private lateinit var txtAgeLoc: EditText
    private lateinit var btnEraseLoc: Button
    private lateinit var btnAddLoc: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addViewModel =
            ViewModelProviders.of(this).get(AddViewModel::class.java)
        val root = inflater.inflate(R.layout.main_screen, container, false)
        txtNameLoc = root.findViewById(R.id.txtName)

        txtFirstNameLoc = root.findViewById(R.id.txtFirstName)
        txtAgeLoc = root.findViewById(R.id.txtAge)
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
        val age=txtAgeLoc.text.toString()
        if (age.equals("") || (name.equals("") && firstName.equals(""))) {
            Toast.makeText(activity, "Give Name and/or First Name and Age", Toast.LENGTH_LONG).show()
        } else {
            val ctx = requireContext()
            val SqlLiteCallDB by lazy { SqlLiteCallDB(SqlLiteHelper(ctx)) }
            var person = Person(0, name, firstName, age, 1)
            var rc = SqlLiteCallDB.savePerson(person)
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
        txtAgeLoc.setText("")

    }

}