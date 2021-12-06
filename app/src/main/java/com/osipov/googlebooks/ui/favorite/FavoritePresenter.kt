package com.osipov.googlebooks.ui.favorite

import com.osipov.googlebooks.data.local.Book
import com.osipov.googlebooks.domain.DeleteFromFavoriteUseCase
import com.osipov.googlebooks.domain.GetFavoriteBooksUseCase
import com.osipov.googlebooks.ui.Screens
import com.osipov.googlebooks.ui.base.BasePresenter
import com.osipov.googlebooks.ui.mainflow.FlowRouter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class FavoritePresenter @Inject constructor(
    private val router: FlowRouter,
    private val getFavoriteBooksUseCase: GetFavoriteBooksUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase
) : BasePresenter<FavoriteView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        presenterScope.launch {
            getFavoriteBooksUseCase.getFavoriteBooks().collect {
                viewState.setBooksIntoList(it)
            }

//            getFavoriteBooksUseCase.clearFavorite()
        }
    }

    fun deleteFromFavorite(book: Book) {
        presenterScope.launch {
            deleteFromFavoriteUseCase.delete(book)
        }
    }

    fun openBookLinkIntoBrowser(link: String) {
        router.navigate(Screens.BrowserScreen(link))
    }
}