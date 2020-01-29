package de.sturmm.todo;

import io.micronaut.runtime.Micronaut


class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Micronaut.build(*args)
                    .packages(Application::class.java.`package`.name)
                    .mainClass(Application::class.java)
                    .start()
        }
    }

}
