package io.github.curioustools.composeudemy1.full_notes.enums

import android.os.Bundle
import androidx.core.os.bundleOf
import io.github.curioustools.composeudemy1.full_notes.model.NoteModel

sealed class Routes(val routeID:String) {
    data object Home:Routes("home")
    data object Details:Routes("details"){
        private const val ARG_ID = "id"
        private const val ARG_TITLE = "title"
        private const val ARG_DETAILS = "details"
        private const val ARG_COLOR = "color"
        private const val ARG_CREATED_AT = "createdAt"
        private const val ARG_UPDATED_AT = "updatedAt"
        private const val ARG_CHECKED = "checked"

        fun getBundle(note: NoteModel): Bundle {
            return bundleOf(
                ARG_ID to note.id,
                ARG_TITLE to note.title,
                ARG_DETAILS to note.details,
                ARG_COLOR to note.color,
                ARG_CREATED_AT to note.createdAt,
                ARG_UPDATED_AT to note.updatedAt,
                ARG_CHECKED to note.isChecked

                )
        }
        fun getNote(bundle: Bundle?):NoteModel?{
            bundle?:return null
            return NoteModel(
                id = bundle.getString(ARG_ID).orEmpty(),
                title = bundle.getString(ARG_TITLE).orEmpty(),
                details = bundle.getString(ARG_DETAILS).orEmpty(),
                color = bundle.getString(ARG_COLOR).orEmpty(),
                createdAt = bundle.getLong(ARG_CREATED_AT)?:0,
                updatedAt = bundle.getLong(ARG_UPDATED_AT)?:0,
                isChecked = bundle.getBoolean(ARG_CHECKED)?:false

            )
        }
    }
}