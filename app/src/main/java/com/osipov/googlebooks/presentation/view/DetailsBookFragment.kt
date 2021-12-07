package com.osipov.googlebooks.presentation.view

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.osipov.googlebooks.R
import com.osipov.googlebooks.databinding.DetailsBookFragmentBinding
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.base.BaseFragment
import com.osipov.googlebooks.presentation.presenter.DetailsBookPresenter
import com.osipov.googlebooks.utils.makeClickable
import moxy.ktx.moxyPresenter

class DetailsBookFragment : BaseFragment(R.layout.details_book_fragment), DetailsBookView {

    private val binding by viewBinding(DetailsBookFragmentBinding::bind)
    private val presenter by moxyPresenter { scope.getInstance(DetailsBookPresenter::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = checkNotNull(arguments?.getParcelable<BookEntity>("details_book"))
        with(binding) {
            toolbar.setNavigationOnClickListener {
                presenter.onBackPressed()
            }
            toolbar.title = book.title
            bookAuthor.text = book.author
            bookLink.apply {
                text = book.link
                makeClickable {
                    presenter.openBookLinkIntoBrowser(text.toString())
                }
            }
            bookImage.load(book.image)
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

}