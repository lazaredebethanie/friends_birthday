package fr.f5rpn.dbaccessv2.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import fr.f5rpn.dbaccessv2.R
import fr.f5rpn.dbaccessv2.SqlLiteCallDB
import fr.f5rpn.dbaccessv2.SqlLiteHelper
import kotlinx.android.synthetic.main.person.*
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.main_screen.*
import kotlinx.android.synthetic.main.main_screen.view.*

class SearchFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var txtNameLoc: EditText
    private lateinit var txtFirstNameLoc: EditText
    private lateinit var txtBirthdayLoc: EditText
    private lateinit var txtIdHiddenLoc: TextView

    private lateinit var btnEraseLoc: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_screen, container, false)
        spinner = root.findViewById(R.id.spinnerId)
        txtNameLoc = root.findViewById(R.id.txtName)
        txtNameLoc.isEnabled=false
        txtFirstNameLoc = root.findViewById(R.id.txtFirstName)
        txtFirstNameLoc.isEnabled=false
        txtBirthdayLoc = root.findViewById(R.id.txtBirthday)
        txtBirthdayLoc.isEnabled=false
        txtIdHiddenLoc = root.findViewById(R.id.txtIdHidden)
        btnEraseLoc = root.findViewById(R.id.btnErase)

        btnEraseLoc.setOnClickListener {
            txtNameLoc.setText("")
            txtFirstNameLoc.setText("")
            txtBirthdayLoc.setText("")
            txtIdHiddenLoc.text = ""
            spinner.setSelection(0)
        }

        val progressBar: ProgressBar = root.findViewById(R.id.progressBar)
        progressBar.visibility=View.VISIBLE
        root.btnCommonAdd.visibility=View.INVISIBLE
        root.btnCommonDelete.visibility=View.INVISIBLE
        root.btnCommonUpdate.visibility=View.INVISIBLE
        initSpinner()
        progressBar.visibility=View.GONE
        return root
    }

    private fun initSpinner() {

        val ctx = requireContext()

        val sqlLiteCallDB by lazy { SqlLiteCallDB(SqlLiteHelper(ctx)) }
        val myList: MutableList<String?> = ArrayList()
        myList.add(0, "Select a name")
        val myListId: MutableList<Int> = ArrayList()
        myListId.add(0)
        //doAsync {
        val listNom = sqlLiteCallDB.requestAllPerson()
        var i=0
        for (person in listNom) {
            if (person.change < 10) {
                myList.add(person.first_name + " " + person.name)
                myListId.add(i)
            }
            i++
        }
        val arrayAdapter: ArrayAdapter<String?> =
                ArrayAdapter<String?>(ctx,android.R.layout.simple_list_item_1, myList)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (parent?.getItemAtPosition(position) == "Choose a name") {
                        TODO("Nothing to do")
                    } else {
                        if (position!=0) {
                            //val item = parent?.getItemAtPosition(position).toString()
                            txtIdHiddenLoc.text = listNom[myListId[position]].idPerson.toString()
                            txtNameLoc.setText(listNom[myListId[position]].name)
                            txtFirstNameLoc.setText(listNom[myListId[position]].first_name)
                            txtBirthdayLoc.setText(listNom[myListId[position]].birthday)
                            //layoutSearch.visibility=View.INVISIBLE
                            //Toast.makeText(parent?.context,"Selected $item", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        //}
    }
}