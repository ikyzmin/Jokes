package ru.ikyzmin.jokes.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class NavigatorImpl(
    override val fragmentManager: FragmentManager,
    override val navigationContainer: Int
) : Navigator {
    override fun navigate(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(navigationContainer, fragment)
            .addToBackStack(fragment.id.toString())
            .commit()
    }

    override fun back() {
        if (fragmentManager.backStackEntryCount>1){
            fragmentManager.popBackStack()
        }
    }
}
