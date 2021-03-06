package com.facu.bartender.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.facu.bartender.R
import com.facu.bartender.data.DataSource
import com.facu.bartender.data.model.Drink
import com.facu.bartender.domain.ImplemetacionRepo
import com.facu.bartender.ui.viewmodel.ByNameViewModel
import com.facu.bartender.ui.viewmodel.ViewModelFacto
import com.facu.bartender.vo.Resource
import kotlinx.android.synthetic.main.fragment_search_by_name.*



class SearchByNameFrag : Fragment(), AdapterSearchByName.ClickEnCoctelListener {

    private val viewModel by viewModels<ByNameViewModel> {
        ViewModelFacto(ImplemetacionRepo(DataSource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecycleViewer()
        setupSearchView()
        observer()

    }

    private fun observer(){
        viewModel.fetchTragosList.observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
            when(result){
                is Resource.Loading ->{
                    progressBar.visibility= View.VISIBLE
                }
                is Resource.Success ->{
                    progressBar.visibility= View.GONE
                    RecycleViewByname.adapter= AdapterSearchByName(requireContext(),result.data, this)
                }
                is Resource.Failure ->{
                    progressBar.visibility= View.GONE
                    Toast.makeText(requireContext(), "Error al traer los datos ${result.exception}",Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTrago(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun ClickTrago(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.descripcionBebidasFrag, bundle)
        //27.35
    }

    private fun configRecycleViewer(){
        RecycleViewByname.layoutManager=LinearLayoutManager(requireContext())
        RecycleViewByname.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }
}