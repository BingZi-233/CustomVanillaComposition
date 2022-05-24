package online.bingzi.customVanillaComposition

import online.bingzi.customVanillaComposition.service.recipeService
import taboolib.common.platform.Plugin
import taboolib.platform.BukkitPlugin
import java.io.File

@Suppress("unused")
object CustomVanillaComposition : Plugin() {
    override fun onEnable() {
        val file = File(BukkitPlugin.getInstance().dataFolder, "craft/")
        val fileTreeWalk = file.walk()
        fileTreeWalk.forEach {
            recipeService.registerRecipe(it.name.replace(".json",""))
        }
    }
}