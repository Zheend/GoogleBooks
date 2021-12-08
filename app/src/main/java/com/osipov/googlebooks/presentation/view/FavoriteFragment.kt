package com.osipov.googlebooks.presentation.view

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.databinding.FavoriteFragmentBinding
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.base.BaseFragment
import com.osipov.googlebooks.presentation.presenter.FavoritePresenter
import com.osipov.googlebooks.utils.MainBooksAdapter
import moxy.ktx.moxyPresenter

class FavoriteFragment : BaseFragment(R.layout.favorite_fragment), FavoriteView {

    private val binding by viewBinding(FavoriteFragmentBinding::bind)
    private val presenter: FavoritePresenter by moxyPresenter { scope.getInstance(FavoritePresenter::class.java) }

    private val favoriteBooksAdapter by lazy {
        MainBooksAdapter(
            itemClick = { link ->
                presenter.openBookLinkIntoBrowser(link)
            },
            favoriteClick = { book ->
                presenter.deleteFromFavorite(book)
            },
            showDetails = { book ->
                presenter.showDetailsBook(book)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavoriteBooks.adapter = favoriteBooksAdapter
    }

    override fun setBooksIntoList(books: List<BookEntity>) {
        favoriteBooksAdapter.items = books
        favoriteBooksAdapter.notifyDataSetChanged()
    }

}