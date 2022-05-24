package online.bingzi.customVanillaComposition.util

import online.bingzi.customVanillaComposition.service.recipeService
import org.bukkit.Bukkit
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.platform.BukkitPlugin
import java.io.File

@Suppress("unused")
object Util {
    private val file = File(BukkitPlugin.getInstance().dataFolder, "craft/")

    @Awake(LifeCycle.ACTIVE)
    fun loadRecipe() {
        file.walk().forEach {
            if (it.name.contains(".json")) {
                recipeService.registerRecipe(it.name.replace(".json", ""))
            }
        }
    }

    @Awake(LifeCycle.DISABLE)
    fun unloadRecipe() {
        Bukkit.clearRecipes()
    }
}