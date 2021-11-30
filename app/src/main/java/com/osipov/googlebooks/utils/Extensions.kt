package com.osipov.googlebooks.utils

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"