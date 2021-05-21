package com.certified.zuritask49contacts.screens.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.certified.zuritask49contacts.Category
import com.certified.zuritask49contacts.R
import com.certified.zuritask49contacts.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var binding: FragmentCategoryBinding? = null
    private lateinit var adapter: CategoryAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_category, container, false
        )

        val categories = mutableListOf<Category>()

        val viewModelFactory = CategoryViewModelFactory(categories)

        val categoryViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(CategoryViewModel::class.java)

        categories.add(Category(0, "Family", R.drawable.ic_family))
        categories.add(Category(1, "Friends", R.drawable.ic_friends))
        categories.add(Category(2, "Colleagues", R.drawable.ic_colleagues))
        categories.add(Category(3, "Tutors", R.drawable.ic_tutors))

        adapter = CategoryAdapter()

        binding?.viewModel = categoryViewModel
        binding?.lifecycleOwner = this
        binding?.recyclerViewContacts?.layoutManager = GridLayoutManager(activity, 2)
        binding?.recyclerViewContacts?.adapter = adapter

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        adapter.setOnCategoryClickedListener(object : CategoryAdapter.OnCategoryClickedListener {
            override fun onClick(category: Category) {
                when (category.id) {
                    0 -> navController.navigate(R.id.familyFragment)
                    1 -> navController.navigate(R.id.friendsFragment)
                    2 -> navController.navigate(R.id.colleaguesFragment)
                    3 -> navController.navigate(R.id.tutorsFragment)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}