package com.osipov.googlebooks.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.databinding.AllBooksFragmentBinding
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.base.BaseFragment
import com.osipov.googlebooks.presentation.presenter.AllBooksPresenter
import com.osipov.googlebooks.utils.MainBooksAdapter
import moxy.ktx.moxyPresenter

class AllBooksFragment : BaseFragment(R.layout.all_books_fragment), AllBooksView {

    private val binding by viewBinding(AllBooksFragmentBinding::bind)

    private val allBooksPresenter: AllBooksPresenter by moxyPresenter {
        scope.getInstance(AllBooksPresenter::class.java)
    }

    private val adapter by lazy {
        MainBooksAdapter(
            itemClick = { link ->
                allBooksPresenter.openBookLinkIntoBrowser(link)
            },
            favoriteClick = { book ->
                allBooksPresenter.addToFavorite(book)
            },
            showDetails = { book ->
                allBooksPresenter.showDetailsBook(book)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            etSearch.addTextChangedListener {
                allBooksPresenter.searchBooks(it.toString())
            }
            rvResultSearchBooks.adapter = adapter
        }
    }

    override fun setBooks(books: List<BookEntity>) {
        adapter.items = books
    }

    override fun showState(isEmptyQuery: Boolean) {
        with(binding) {
            rvResultSearchBooks.isVisible = !isEmptyQuery
            emptyState.isVisible = isEmptyQuery
        }
    }

}