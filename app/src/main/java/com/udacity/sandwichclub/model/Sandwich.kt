package com.udacity.sandwichclub.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.udacity.sandwichclub.utils.JsonUtils
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class Sandwich(
    val mainName: String,
    val alsoKnownAs: List<String>,
    val placeOfOrigin: String,
    val description: String,
    val image: String,
    val ingredients: List<String>
) : Parcelable {
    companion object {
        fun fromJson(data: Array<String>): List<Sandwich> {
            val sandwiches: MutableList<Sandwich> = ArrayList()
            for (sandwich in data) {
                sandwiches.add(JsonUtils.parseSandwichJson(sandwich))
            }
            return sandwiches
        }
    }

    override fun toString(): String {
        return """Sandwich object details: 
---------------------------
${mainName},
${alsoKnownAs.size} aka,
${placeOfOrigin},
${description},
${image},
${ingredients.size} ingredients,
---------------------------"""
    }
}