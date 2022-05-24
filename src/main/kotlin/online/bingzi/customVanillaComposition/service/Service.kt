package online.bingzi.customVanillaComposition.service

import online.bingzi.customVanillaComposition.entity.Recipe

interface Service {
    /**
     * Register shaped recipe
     * 注册有序配方
     *
     */
    fun registerRecipe(node: String)

    /**
     * Register shaped recipe
     * 注册有序配方
     *
     */
    fun registerRecipe(recipe: Recipe)
}