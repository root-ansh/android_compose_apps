package io.github.curioustools.composeudemy1.full_notes.model

data class NoteModel(
    val id: String,
    val title: String,
    val details: String,
    val color: String,
    val createdAt: Long,
    val updatedAt: Long,
    var isChecked: Boolean,
){
    companion object{
        fun mock(): NoteModel {
            return NoteModel(
                id = "legimus",
                title = "primis",
                details = "appareat",
                color = "#FFD0BCFF",
                createdAt = 1710604688290,
                updatedAt = System.currentTimeMillis(),
                isChecked = false

            )
        }
    }
}