package com.osipov.googlebooks.ui.allbooks

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.data.remote.Item
import com.osipov.googlebooks.databinding.AllBooksFragmentBinding
import com.osipov.googlebooks.ui.base.BaseFragment
import moxy.ktx.moxyPresenter

class AllBooksFragment : BaseFragment(R.layout.all_books_fragment), AllBooksView {

    private val binding by viewBinding(AllBooksFragmentBinding::bind)

    private val allBooksPresenter: AllBooksPresenter by moxyPresenter {
        scope.getInstance(AllBooksPresenter::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            etSearch.addTextChangedListener {
                allBooksPresenter.searchBooks(it.toString())
            }
            rvResultSearchBooks.adapter = AllBooksAdapter()
            (binding.rvResultSearchBooks.adapter as AllBooksAdapter).bookLinkClicked = {
                allBooksPresenter.openBookLinkIntoBrowser(it)
            }
        }
    }

    override fun setBooks(books: List<Item>) {
        (binding.rvResultSearchBooks.adapter as AllBooksAdapter).submitList(books)
    }

    override fun showState(isEmptyQuery: Boolean) {
        with(binding) {
            rvResultSearchBooks.isVisible = !isEmptyQuery
            emptyState.isVisible = isEmptyQuery
        }
    }

}