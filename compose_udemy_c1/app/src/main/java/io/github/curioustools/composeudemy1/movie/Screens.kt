package io.github.curioustools.composeudemy1.movie

enum class Screens(val route:String) {
    HOME("home"),
    DETAILS("details"),
    ADD_NEW("add");

    companion object{
        private fun fromString(str:String): Screens?{
            return entries.firstOrNull { it.route.equals(str,true) }
        }
        fun fromRoute(route:String): Screens {
            return fromString(route.substringAfter('/')) ?: HOME
        }

    }
}