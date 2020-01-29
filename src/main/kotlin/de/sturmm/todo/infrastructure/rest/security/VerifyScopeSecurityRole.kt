package de.sturmm.todo.infrastructure.rest.security

import io.micronaut.http.HttpRequest
import io.micronaut.security.rules.SecuredAnnotationRule
import io.micronaut.security.rules.SecurityRule
import io.micronaut.security.rules.SecurityRuleResult
import io.micronaut.security.rules.SecurityRuleResult.ALLOWED
import io.micronaut.security.rules.SecurityRuleResult.REJECTED
import io.micronaut.security.rules.SecurityRuleResult.UNKNOWN
import io.micronaut.web.router.MethodBasedRouteMatch
import io.micronaut.web.router.RouteMatch
import org.slf4j.LoggerFactory
import javax.inject.Singleton

private typealias Claims = MutableMap<String, Any>?

private fun Claims.scopes(): List<String> = this?.get("scope")?.let { it as List<String> } ?: emptyList()


@Singleton
class VerifyScopeSecurityRole : SecurityRule {

    private val ruleOrder = SecuredAnnotationRule.ORDER - 100

    override fun getOrder(): Int {
        return ruleOrder
    }

    override fun check(request: HttpRequest<*>?, routeMatch: RouteMatch<*>?, claims: Claims): SecurityRuleResult {
        if (routeMatch is MethodBasedRouteMatch<*, *>) {
            if (routeMatch.hasAnnotation(Scopes::class.java)) {
                val optionalValue = routeMatch.getValue(Scopes::class.java, Array<String>::class.java)
                if (optionalValue.isPresent) {
                    val values = listOf(*optionalValue.get())
                    val scopes = claims.scopes()
                    LOG.debug("Matching required scopes ($values) with provided ($scopes)")
                    return if (scopes.containsAll(values)) {
                        ALLOWED
                    } else REJECTED
                }
            }
        }
        return UNKNOWN
    }

    companion object {
        @JvmStatic
        private val LOG = LoggerFactory.getLogger(VerifyScopeSecurityRole::class.java)
    }

}