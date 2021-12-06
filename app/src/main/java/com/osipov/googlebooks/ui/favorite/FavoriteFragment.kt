package com.osipov.googlebooks.ui.favorite

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.data.local.Book
import com.osipov.googlebooks.databinding.FavoriteFragmentBinding
import com.osipov.googlebooks.ui.base.BaseFragment
import moxy.ktx.moxyPresenter

class FavoriteFragment : BaseFragment(R.layout.favorite_fragment), FavoriteView {

    private val binding by viewBinding(FavoriteFragmentBinding::bind)

    private val favoriteBooksAdapter by lazy {
        FavoriteBooksAdapter(
            itemClick = { link ->
                presenter.openBookLinkIntoBrowser(link)
            },
            favoriteClick = { book ->
                presenter.deleteFromFavorite(book)
            }
        )
    }

    private val presenter: FavoritePresenter by moxyPresenter { scope.getInstance(FavoritePresenter::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavoriteBooks.adapter = favoriteBooksAdapter
    }

    override fun setBooksIntoList(books: List<Book>) {
        favoriteBooksAdapter.items = books
        favoriteBooksAdapter.notifyDataSetChanged()
    }

}