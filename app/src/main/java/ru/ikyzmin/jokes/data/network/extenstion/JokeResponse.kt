package ru.ikyzmin.jokes.data.network.extenstion

import ru.ikyzmin.jokes.data.network.JokeResponse
import ru.ikyzmin.jokes.domain.models.Joke
import java.time.LocalDateTime

fun JokeResponse.toDomain(): Joke {
    return Joke(
        joke = joke,
        setup = setup,
        delivery = delivery,
        id = id,
        date = LocalDateTime.now(),
        type = type,
    )
}
