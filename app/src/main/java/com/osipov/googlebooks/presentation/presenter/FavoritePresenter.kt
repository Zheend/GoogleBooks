package com.osipov.googlebooks.presentation.presenter

import com.osipov.googlebooks.domain.interactor.BooksInteractor
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.base.BasePresenter
import com.osipov.googlebooks.presentation.view.FavoriteView
import com.osipov.googlebooks.screens.Screens
import com.osipov.googlebooks.utils.FlowRouter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class FavoritePresenter @Inject constructor(
    private val router: FlowRouter,
    private val booksInteractor: BooksInteractor
) : BasePresenter<FavoriteView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        presenterScope.launch {
            booksInteractor.getFavoriteBooks().collect {
                viewState.setBooksIntoList(it)
            }

//            booksInteractor.clearFavorite()
        }
    }

    fun deleteFromFavorite(book: BookEntity) {
        presenterScope.launch {
            booksInteractor.deleteFavoriteBook(book)
        }
    }

    fun showDetailsBook(book: BookEntity) {
        router.navigate(Screens.DetailsBook(book))
    }

    fun openBookLinkIntoBrowser(link: String) {
        router.navigate(Screens.BrowserScreen(link))
    }

}
