package ru.ikyzmin.jokes.data.db.extensions

import ru.ikyzmin.jokes.data.db.JokeEntity
import ru.ikyzmin.jokes.domain.models.Joke
import ru.ikyzmin.jokes.domain.models.JokeType

fun JokeEntity.toDomain() = Joke(
    joke = joke,
    setup = setup,
    delivery = delivery,
    id = id,
    date = date,
    type = when (type) {
        "single" -> JokeType.single
        "twopart" -> JokeType.twopart
        else -> JokeType.unknown
    },
)

fun Joke.toData() = JokeEntity(
    joke = joke.orEmpty(),
    setup = setup.orEmpty(),
    delivery = delivery.orEmpty(),
    id = id,
    date = date,
    type = type.name,
)
