package fr.f5rpn.dbaccessv2.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.f5rpn.dbaccessv2.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.person.*
import kotlinx.android.synthetic.main.person.view.*





class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        searchViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

}