package de.sturmm.todo.infrastructure.rest.security

import java.lang.annotation.Inherited
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER

@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, ANNOTATION_CLASS, CLASS)
@Retention(RUNTIME)
@Inherited
@MustBeDocumented
annotation class Scopes(
    vararg val value: String
)