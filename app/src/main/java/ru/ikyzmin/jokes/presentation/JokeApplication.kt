package ru.ikyzmin.jokes.presentation

import android.app.Application
import ru.ikyzmin.jokes.presentation.ComponentHolder
import ru.ikyzmin.jokes.presentation.JokeComponent

class JokeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ComponentHolder.init(JokeComponent(this))
    }
}
