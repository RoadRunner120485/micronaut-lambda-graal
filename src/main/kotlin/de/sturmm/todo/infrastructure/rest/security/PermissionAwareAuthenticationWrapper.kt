package de.sturmm.todo.infrastructure.rest.security

import io.micronaut.security.authentication.Authentication

data class PermissionAwareAuthenticationWrapper(val delegate: Authentication, val permissions: Set<String> = emptySet()) : Authentication {
    override fun getName(): String = delegate.name

    override fun getAttributes(): Map<String, Any> {
        return delegate.attributes.toMutableMap() + (PERMISSIONS_KEY to permissions)
    }

    companion object {
        const val PERMISSIONS_KEY = "_permissions"
    }
}
