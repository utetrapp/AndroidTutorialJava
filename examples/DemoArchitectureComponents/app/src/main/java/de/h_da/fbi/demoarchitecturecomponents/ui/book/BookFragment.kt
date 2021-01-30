package de.h_da.fbi.demoarchitecturecomponents.ui.book

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import de.h_da.fbi.demoarchitecturecomponents.R
import de.h_da.fbi.demoarchitecturecomponents.databinding.BookFragmentBinding

class BookFragment : Fragment() {

    private lateinit var viewModel: BookViewModel
    private val args: BookFragmentArgs by navArgs()
    private lateinit var binding: BookFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.book = args.book
        binding.viewModel = viewModel
        // TODO: Use the ViewModel
    }

}