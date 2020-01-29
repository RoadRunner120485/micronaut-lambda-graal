package de.sturmm.todo.domain

import io.micronaut.core.annotation.Introspected
import java.util.UUID

@Introspected
data class Todo(val id: String = UUID.randomUUID().toString(), val description: String, val done: Boolean = false)
