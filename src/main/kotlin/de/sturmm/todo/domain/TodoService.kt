package de.sturmm.todo.domain

import io.reactivex.Single
import javax.inject.Singleton

interface TodoService {

    fun findTodo(id: String): Single<Todo>

}

@Singleton
class TodoServiceImpl : TodoService {

    override fun findTodo(id: String): Single<Todo> = Single.just(Todo(description = "Hello World", id = id))

}