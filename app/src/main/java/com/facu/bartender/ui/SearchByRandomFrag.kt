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
import com.facu.bartender.data.DataSourceRandom
import com.facu.bartender.data.model.Drink
import com.facu.bartender.domain.ImplementacionRepoRandom
import com.facu.bartender.ui.viewmodel.ByRandomViewModel
import com.facu.bartender.ui.viewmodel.ViewModelFactoRandom
import com.facu.bartender.vo.Resource
import kotlinx.android.synthetic.main.fragment_search_by_random.*


class SearchByRandomFrag : Fragment(), AdapterSearchByRandom.ClickEnCoctelListenerRandom  {

    private val viewModel by viewModels<ByRandomViewModel> {
        ViewModelFactoRandom(ImplementacionRepoRandom(DataSourceRandom()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_random, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecycleViewer()
        setupSearchView()
        observer()

    }

    private fun observer(){
        viewModel.fetchRandomList.observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
            when(result){
                is Resource.Loading ->{
                    progressBarRandom.visibility= View.VISIBLE
                }
                is Resource.Success ->{
                    progressBarRandom.visibility= View.GONE
                    RecycleViewByRandom.adapter= AdapterSearchByRandom(requireContext(),result.data, this)
                }
                is Resource.Failure ->{
                    progressBarRandom.visibility= View.GONE
                    Toast.makeText(requireContext(), "Error al traer los datos ${result.exception}",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setupSearchView(){
        searchViewRandom.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTragoRandom(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun ClickTragoRandom(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.descripcionByRandom, bundle)
        //27.35
    }

    private fun configRecycleViewer(){
        RecycleViewByRandom.layoutManager= LinearLayoutManager(requireContext())
        RecycleViewByRandom.addItemDecoration(
            DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL)
        )
    }


}