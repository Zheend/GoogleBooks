package com.osipov.googlebooks.domain

import com.osipov.googlebooks.data.repository.BooksRepository
import com.osipov.googlebooks.domain.interactor.BooksInteractor
import com.osipov.googlebooks.domain.interactor.BooksInteractorImpl
import javax.inject.Inject
import javax.inject.Provider

class BooksProvider @Inject constructor(
    private val booksRepository: BooksRepository
) : Provider<BooksInteractor> {

    override fun get(): BooksInteractor = BooksInteractorImpl(
        repository = booksRepository
    )
}