package de.sturmm.todo.infrastructure.rest.security

import com.nimbusds.jwt.JWT
import io.micronaut.context.annotation.Replaces
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.jwt.validator.DefaultJwtAuthenticationFactory
import java.util.Optional
import javax.inject.Singleton

@Singleton
@Replaces(DefaultJwtAuthenticationFactory::class)
class PermissionAwareJwtAuthenticationFactory : DefaultJwtAuthenticationFactory() {
    override fun createAuthentication(token: JWT?): Optional<Authentication> {
        return super.createAuthentication(token).map { PermissionAwareAuthenticationWrapper(it, setOf("test")) }
    }
}