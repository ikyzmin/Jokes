package ru.ikyzmin.jokes.presentation.navigation

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import ru.ikyzmin.jokes.R
import ru.ikyzmin.jokes.presentation.ComponentHolder
import ru.ikyzmin.jokes.presentation.mainjoke.MainFragment

class RootActivity : AppCompatActivity(R.layout.activity_root) {

    val component = ComponentHolder.getComponent()
    val navigatorHolder = component.navigatorHolder
    val router = component.router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = findViewById<View>(R.id.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.root) as NavHostFragment
        val navController = navHostFragment.navController
        navigatorHolder.attachNavigator(NavigatorImpl(supportFragmentManager, R.id.root))
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        onBackPressedDispatcher.addCallback {
            navController.popBackStack()
        }
    }
}
