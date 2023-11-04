package work.curioustools.composerecipes.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import work.curioustools.composerecipes.presentation.ui.Screens.*
import work.curioustools.composerecipes.presentation.ui.user.UserDetailScreen
import work.curioustools.composerecipes.presentation.ui.user.UserListScreen

sealed class Screens(val route: String) {
    object UserListScreen: Screens(route = "user_list")
    object UserDetail: Screens(route = "dishes")
    object DishDetail: Screens(route = "dish_detail")
}

@Composable
fun AppNavigation(start: Screens) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =start.route) {
        composable(
            route = UserListScreen.route
        ) {

            UserListScreen(router = navController)
        }

        composable(
            route = "${UserDetail.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            UserDetailScreen(id = it.arguments?.getString("id"))
        }

        composable(route = DishDetail.route) {

        }
    }
}

