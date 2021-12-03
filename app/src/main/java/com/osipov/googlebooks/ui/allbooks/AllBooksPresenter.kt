package com.osipov.googlebooks.ui.allbooks

import com.osipov.googlebooks.domain.SearchBooksUseCase
import com.osipov.googlebooks.ui.Screens
import com.osipov.googlebooks.ui.base.BasePresenter
import com.osipov.googlebooks.ui.mainflow.FlowRouter
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
    private val searchBooksUseCase: SearchBooksUseCase,
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
                        val result = searchBooksUseCase.searchBooks(query)
                        viewState.setBooks(result.items)
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

}

private const val TEXT_ENTERED_DEBOUNCE_MILLIS = 500L
