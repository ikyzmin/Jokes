package ru.ikyzmin.jokes.presentation.navigation

import androidx.fragment.app.Fragment

class RouterImpl(
    override val navigatorHolder: NavigationHolder
) : Router {
    override fun open(fragment: Fragment) {
        navigatorHolder.openFragment(fragment)
    }

    override fun back() {
        navigatorHolder.back()
    }
}
