package io.github.curioustools.composeudemy1.full_notes.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import io.github.curioustools.composeudemy1.base.MyComposeColors.Green80
import io.github.curioustools.composeudemy1.base.MyComposeColors.Purple80
import io.github.curioustools.composeudemy1.base.MyComposeColors.White
import io.github.curioustools.composeudemy1.base.MyComposeColors.Yellow80
import io.github.curioustools.composeudemy1.base.asHexString
import java.util.UUID
import kotlin.random.Random
import kotlin.random.Random.Default

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
                title = "Aprimis",
                details = "appareat",
                color = "#D0BCFF",
                createdAt = 1710604688290,
                updatedAt = System.currentTimeMillis(),
                isChecked = true

            )
        }

        fun mockList():List<NoteModel>{
            return listOf(White, Purple80 ,Purple80 ,Yellow80 ,Green80,Green80, Yellow80, Yellow80,
                Green80, White,White, Purple80 ,Purple80 ,Yellow80 ,Green80,Green80, Yellow80, Yellow80,
                Green80, White  ).map {
                val createdAt = Random.nextLong(from = 1610603688290, until = System.currentTimeMillis())
                val updatedAt = Random.nextLong(from = 1610603688290, until = System.currentTimeMillis())
                val finalUpdatedAt = listOf(createdAt,updatedAt,System.currentTimeMillis()).random()
                NoteModel(
                    id = UUID.randomUUID().toString().take(10),
                    title = LoremIpsum(10).values.joinToString(),
                    details = LoremIpsum(50).values.map { s-> s.trimIndent() }.joinToString(),
                    color = it.asHexString(),
                    createdAt = createdAt,
                    updatedAt = finalUpdatedAt,
                    isChecked =  listOf(true,false,false,false,true).random()

                )
            }

        }
    }
}