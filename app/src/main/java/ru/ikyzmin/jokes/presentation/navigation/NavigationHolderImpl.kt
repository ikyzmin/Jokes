package ru.ikyzmin.jokes.presentation.navigation

import androidx.fragment.app.Fragment

class NavigationHolderImpl : NavigationHolder {
    private var navigator: Navigator? = null
    override fun attachNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun detachNavigator() {
        this.navigator = null
    }

    override fun openFragment(fragment: Fragment) {
        if (navigator == null) return
        navigator!!.navigate(fragment)
    }

    override fun back() {
        if (navigator == null) return
        navigator!!.back()
    }
}
