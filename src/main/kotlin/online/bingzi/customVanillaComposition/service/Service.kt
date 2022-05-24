package online.bingzi.customVanillaComposition.service

import online.bingzi.customVanillaComposition.entity.Recipe

interface Service {

    fun registerRecipe(node: String)

    fun registerRecipe(recipe: Recipe)

}