package de.h_da.fbi.demoarchitecturecomponents.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import de.h_da.fbi.demoarchitecturecomponents.databinding.BookFragmentBinding

class BookFragment : Fragment() {

    private lateinit var viewModel: BookViewModel
    private val args: BookFragmentArgs by navArgs()
    private lateinit var binding: BookFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.book = args.book
        binding.viewModel = viewModel
    }

}