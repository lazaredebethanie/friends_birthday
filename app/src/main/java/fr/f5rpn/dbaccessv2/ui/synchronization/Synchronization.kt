package fr.f5rpn.dbaccessv2.ui.synchronization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import fr.f5rpn.dbaccessv2.R
import fr.f5rpn.dbaccessv2.RequestToDB
import fr.f5rpn.dbaccessv2.SqlLiteCallDB
import fr.f5rpn.dbaccessv2.SqlLiteHelper

class Synchronization : Fragment() {
    lateinit var btnCancel: Button
    lateinit var btnSync: Button
    var lastId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_synchronization, container, false)
        btnCancel = root.findViewById(R.id.btnCancel)
        btnCancel.setOnClickListener() {
            Toast.makeText(root.context,"Cancel pushed",Toast.LENGTH_LONG).show()
        }

        btnSync = root.findViewById(R.id.btnSync)
        btnSync.setOnClickListener() {
            val request = RequestToDB (requireContext())
            request.synchronize(lastId)
        }

        val ctx = requireContext()
        val sqlLiteCallDB by lazy { SqlLiteCallDB(SqlLiteHelper(ctx)) }
        lastId = sqlLiteCallDB.selectLastId()


        return root
    }

}