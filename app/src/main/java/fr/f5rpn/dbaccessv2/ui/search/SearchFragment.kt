package fr.f5rpn.dbaccessv2.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import fr.f5rpn.dbaccessv2.R
import fr.f5rpn.dbaccessv2.SqlLiteCallDB
import fr.f5rpn.dbaccessv2.SqlLiteHelper
import kotlinx.android.synthetic.main.person.*
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.main_screen.*
import kotlinx.android.synthetic.main.main_screen.view.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var spinner: Spinner
    private lateinit var txtNameLoc: EditText
    private lateinit var txtFirstNameLoc: EditText
    private lateinit var txtAgeLoc: EditText
    private lateinit var txtIdHiddenLoc: TextView

    private lateinit var btnEraseLoc: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.main_screen, container, false)
        txtNameLoc = root.findViewById(R.id.txtName)
        txtFirstNameLoc = root.findViewById(R.id.txtFirstName)
        txtAgeLoc = root.findViewById(R.id.txtAge)
        txtIdHiddenLoc = root.findViewById(R.id.txtIdHidden)
        btnEraseLoc = root.findViewById(R.id.btnErase)

        btnEraseLoc.setOnClickListener {
            txtNameLoc.setText("")
            txtFirstNameLoc.setText("")
            txtAgeLoc.setText("")
            txtIdHiddenLoc.text = ""
        }


        var progressBar: ProgressBar = root.findViewById(R.id.progressBar)
        progressBar.visibility=View.VISIBLE
        root.btnCommonAdd.visibility=View.INVISIBLE
        root.btnCommonDelete.visibility=View.INVISIBLE
        root.btnCommonUpdate.visibility=View.INVISIBLE
        initSpinner(root)
        progressBar.visibility=View.GONE
        return root
    }

    private fun initSpinner(root: View) {

        val ctx = requireContext()
        spinner = root.findViewById(R.id.spinnerId)

        val SqlLiteCallDB by lazy { SqlLiteCallDB(SqlLiteHelper(ctx)) }
        val myList: MutableList<String?> = ArrayList()
        myList.add(0, "Select a name")
        //doAsync {
            var listNom = SqlLiteCallDB.requestAllPerson()
            for (person in listNom) {
                myList.add(person.first_name+" "+person.name)
            }
            val arrayAdapter: ArrayAdapter<String?> =
                ArrayAdapter<String?>(ctx,android.R.layout.simple_list_item_1, myList as MutableList<String?>)
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (parent?.getItemAtPosition(position) == "Choose a name") {
                    } else {
                        if (position!=0) {
                            val item = parent?.getItemAtPosition(position).toString()
                            txtIdHidden.setText(listNom[position-1].idPerson.toString())
                            txtName.setText(listNom[position-1].name)
                            txtFirstName.setText(listNom[position-1].first_name)
                            txtAge.setText(listNom[position-1].age)
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

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }




}