package de.sturmm.todo.infrastructure.rest

import de.sturmm.todo.domain.Todo
import de.sturmm.todo.domain.TodoService
import de.sturmm.todo.infrastructure.rest.security.Scopes
import io.micronaut.function.aws.proxy.model.factory.MicronautAwsProxyResponseFactory
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus.ACCEPTED
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import io.reactivex.Single
import javax.inject.Inject

@Controller("/todos")
@Secured(SecurityRule.IS_AUTHENTICATED)
class TodoController @Inject constructor(private val todoService: TodoService) {

    @Get("/{id}")
    @Scopes("todo.read")
    fun getTodo(@PathVariable id: String, authentication: Authentication): HttpResponse<Todo> {
        return todoService.findTodo(id)
                .map { MicronautAwsProxyResponseFactory().status(ACCEPTED, it) }
                .blockingGet()
    }

    @Put("/{id}")
    fun createTodo(@PathVariable id: String, @Body todo: Todo): Single<HttpResponse<Todo>> {
        return Single.just(HttpResponse.ok(todo))
    }

}
