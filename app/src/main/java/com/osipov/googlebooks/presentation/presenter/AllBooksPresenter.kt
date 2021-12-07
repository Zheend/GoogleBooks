package com.osipov.googlebooks.presentation.presenter

import com.osipov.googlebooks.domain.interactor.BooksInteractor
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.base.BasePresenter
import com.osipov.googlebooks.presentation.view.AllBooksView
import com.osipov.googlebooks.screens.Screens
import com.osipov.googlebooks.utils.FlowRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class AllBooksPresenter @Inject constructor(
    private val booksInteractor: BooksInteractor,
    private val flowRouter: FlowRouter
) : BasePresenter<AllBooksView>() {

    private val query = MutableStateFlow("")

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        presenterScope.launch {
            query.sample(TEXT_ENTERED_DEBOUNCE_MILLIS)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isBlank()) {
                        viewState.showState(true)
                    } else {
                        val result = booksInteractor.getBooksByQuery(query)
                        viewState.setBooks(result)
                        viewState.showState(false)
                    }
                }
        }
    }

    fun searchBooks(message: String) {
        query.value = message
    }

    fun openBookLinkIntoBrowser(link: String) {
        flowRouter.navigate(Screens.BrowserScreen(link))
    }

    fun addToFavorite(book: BookEntity) {
        presenterScope.launch {
            booksInteractor.addToFavorite(book)
        }
    }

    fun showDetailsBook(book: BookEntity) {
        flowRouter.navigate(Screens.DetailsBook(book))
    }

}

private const val TEXT_ENTERED_DEBOUNCE_MILLIS = 500L
