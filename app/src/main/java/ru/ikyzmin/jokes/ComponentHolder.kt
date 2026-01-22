package ru.ikyzmin.jokes

object ComponentHolder {
    private var component: JokeComponent? = null

    fun init(component: JokeComponent) {
        this.component = component
    }

    fun getComponent(): JokeComponent {
        return component ?: throw IllegalStateException("ComponentHolder is not initialized")
    }
}
