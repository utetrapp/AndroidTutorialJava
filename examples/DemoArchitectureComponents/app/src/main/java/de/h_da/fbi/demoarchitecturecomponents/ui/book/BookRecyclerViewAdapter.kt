package de.h_da.fbi.demoarchitecturecomponents.ui.book

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import de.h_da.fbi.demoarchitecturecomponents.data.Book

import de.h_da.fbi.demoarchitecturecomponents.databinding.ViewholderBookBinding
import de.h_da.fbi.demoarchitecturecomponents.ui.book.BookListFragment
import de.h_da.fbi.demoarchitecturecomponents.ui.book.BookFragment
/**
 * [RecyclerView.Adapter] that can display a [Book].
 * TODO: Replace the implementation with code for your data type.
 */
class BookRecyclerViewAdapter(
    private val values: List<Book>
) : RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ViewholderBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content

        holder.editButton.setOnClickListener{ it.findNavController().navigate(BookListFragmentDirections.actionBookListFragmentToBookFragment(item)) }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ViewholderBookBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val editButton: Button = binding.buttonEdit

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}