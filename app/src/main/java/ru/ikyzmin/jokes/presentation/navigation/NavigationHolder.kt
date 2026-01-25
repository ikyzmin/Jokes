package ru.ikyzmin.jokes.presentation.navigation

import androidx.fragment.app.Fragment

interface NavigationHolder {
    fun attachNavigator(navigator: Navigator)
    fun detachNavigator()

    fun openFragment(fragment: Fragment)

    fun back()
}
