package io.github.curioustools.composeudemy1.full_notes.enums

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import io.github.curioustools.composeudemy1.full_notes.model.NoteModel

sealed class Routes(val routeID: String) {
    open fun schemaRoute(): String = routeID
    open fun schemaArgs() = listOf<NamedNavArgument>()


    data object Home : Routes("home")
    data object Details : Routes("details") {
        override fun schemaRoute(): String {
            return buildString {
                append(routeID)
                append("/{$ARG_ID}")
                append("/{$ARG_TITLE}")
                append("/{$ARG_DETAILS}")
                append("/{$ARG_COLOR}")
                append("/{$ARG_CREATED_AT}")
                append("/{$ARG_UPDATED_AT}")
                append("&{$ARG_CHECKED}")
            }
        }
        override fun schemaArgs(): List<NamedNavArgument> {
            return listOf(
                navArgument(ARG_ID) { type = NavType.StringType },
                navArgument(ARG_TITLE) { type = NavType.StringType },
                navArgument(ARG_DETAILS) { type = NavType.StringType },
                navArgument(ARG_COLOR) { type = NavType.StringType },
                navArgument(ARG_CREATED_AT) { type = NavType.LongType },
                navArgument(ARG_UPDATED_AT) { type = NavType.LongType },
                navArgument(ARG_CHECKED) { type = NavType.BoolType },

                )
        }


        fun filledRoute(note: NoteModel): String {
            return buildString {
                append(routeID)
                append("/${note.id}")
                append("/${note.title}")
                append("/${note.details}")
                append("/${note.color}")
                append("/${note.createdAt}")
                append("/${note.updatedAt}")
                append("&${note.isChecked}")

            }
        }

        fun getNote(bundle: Bundle?): NoteModel? {
            bundle ?: return null
            return NoteModel(
                id = bundle.getString(ARG_ID).orEmpty(),
                title = bundle.getString(ARG_TITLE).orEmpty(),
                details = bundle.getString(ARG_DETAILS).orEmpty(),
                color = bundle.getString(ARG_COLOR).orEmpty(),
                createdAt = bundle.getLong(ARG_CREATED_AT) ?: 0,
                updatedAt = bundle.getLong(ARG_UPDATED_AT) ?: 0,
                isChecked = bundle.getBoolean(ARG_CHECKED) ?: false

            )
        }

    }

    companion object{
        private const val ARG_ID = "id"
        private const val ARG_TITLE = "title"
        private const val ARG_DETAILS = "details"
        private const val ARG_COLOR = "color"
        private const val ARG_CREATED_AT = "createdAt"
        private const val ARG_UPDATED_AT = "updatedAt"
        private const val ARG_CHECKED = "checked"
    }
}