package ru.ikyzmin.jokes.presentation.navigation

import androidx.annotation.IntegerRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Navigator {
    val fragmentManager: FragmentManager

    @get:IntegerRes
    val navigationContainer: Int

    fun navigate(fragment: Fragment)

    fun back()
}
