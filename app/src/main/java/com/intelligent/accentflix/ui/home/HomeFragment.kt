package com.intelligent.accentflix.ui.home


import android.app.SearchManager
import android.content.Context
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.intelligent.accentflix.R
import com.intelligent.accentflix.adapters.home.MovieClickListener
import com.intelligent.accentflix.adapters.home.MoviesGridAdapter
import com.intelligent.accentflix.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * This fragment shows the movies of imdb-api.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        _binding!!.lifecycleOwner = this

        // Giving the binding access to the HomeViewModel
        _binding!!.viewModel = viewModel

        // Declare adapter for recycler view
        _binding!!.moviesGrid.adapter = MoviesGridAdapter(MovieClickListener { movie ->
            viewModel.onMovieClicked(movie)
            findNavController()
                .navigate(R.id.action_HomeFragment_to_HomeDetailFragment)
        })

        return _binding!!.root
    }

    /**
     * Add search icon menu and functionality
     */
    lateinit var  searchView: SearchView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
                val searchItem = menu.findItem(R.id.action_search)
                searchView = searchItem?.actionView as SearchView

                // restore the state of search view if user made a search
                if (viewModel.madeSearch.value!!) {
                    searchItem.expandActionView();
                    searchView.setQuery(viewModel.searchTerm.value, false);
                    searchView.clearFocus();
                }

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                    android.widget.SearchView.OnQueryTextListener {

                    override fun onQueryTextChange(qString: String): Boolean {
                        return true
                    }

                    override fun onQueryTextSubmit(title: String): Boolean {
                        if (title.trim().isNotEmpty()) {
                            viewModel.getMoviesByTitle(title)
                        } else{
                            Toast.makeText(context,resources.getString(R.string.error_search_request),Toast.LENGTH_LONG).show()
                        }
                        searchView.clearFocus()
                        return true
                    }
                })

                searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener{
                    override fun onMenuItemActionExpand(p0: MenuItem): Boolean {
                        return true
                    }

                    override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                        if(viewModel.madeSearch.value!!){
                            viewModel.resetSearch()
                        }
                        return true
                    }
                })

            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}