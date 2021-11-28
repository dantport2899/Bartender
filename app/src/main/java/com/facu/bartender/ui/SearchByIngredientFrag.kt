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
import com.facu.bartender.data.DataSourceIngredient
import com.facu.bartender.data.model.Drink
import com.facu.bartender.domain.ImplementacionRepoIngredient
import com.facu.bartender.ui.viewmodel.ByIngredientViewModel
import com.facu.bartender.ui.viewmodel.ViewModelFacto
import com.facu.bartender.ui.viewmodel.ViewModelFactoIngredient
import com.facu.bartender.vo.Resource
import kotlinx.android.synthetic.main.fragment_search_by_ingredient.*
import kotlinx.android.synthetic.main.fragment_search_by_name.*

class SearchByIngredientFrag : Fragment(), AdapterSearchByIngredient.ClickEnCoctelListenerIngredient {

    private val viewModel by viewModels<ByIngredientViewModel> {
        ViewModelFactoIngredient(ImplementacionRepoIngredient(DataSourceIngredient()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecycleViewer()
        setupSearchView()
        observer()

    }

    private fun observer(){
        viewModel.fetchIngredientList.observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
            when(result){
                is Resource.Loading ->{
                    progressBarIngredient.visibility= View.VISIBLE
                }
                is Resource.Success ->{
                    progressBarIngredient.visibility= View.GONE
                    RecycleViewByIngredient.adapter= AdapterSearchByIngredient(requireContext(),result.data, this)
                }
                is Resource.Failure ->{
                    progressBarIngredient.visibility= View.GONE
                    Toast.makeText(requireContext(), "Error al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setupSearchView(){
        searchViewIngredient.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTragoIngredient(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun ClickTragoIngredient(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.descripcionByIngredientFrag, bundle)
        //27.35
    }

    private fun configRecycleViewer(){
        RecycleViewByIngredient.layoutManager= LinearLayoutManager(requireContext())
        RecycleViewByIngredient.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }

}