package ru.ikyzmin.jokes.presentation.navigation

import androidx.fragment.app.Fragment

interface Router {
    val navigatorHolder: NavigationHolder
    fun open(fragment: Fragment)

    fun back()
}
