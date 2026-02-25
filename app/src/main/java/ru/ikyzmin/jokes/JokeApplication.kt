package ru.ikyzmin.jokes

import android.app.Application


class JokeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ComponentHolder.init(JokeComponent(this))
    }
}
