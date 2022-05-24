package online.bingzi.customVanillaComposition.service.impl

import online.bingzi.customVanillaComposition.entity.Recipe
import online.bingzi.customVanillaComposition.entity.RecipeType
import online.bingzi.customVanillaComposition.service.Service
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import taboolib.common.platform.function.info

class LegacyService : Service {

    override fun registerRecipe(node: String) {
        Recipe.readDataFile(node)?.let {
            register(it)
        }
    }

    override fun registerRecipe(recipe: Recipe) {
        register(recipe)
    }

    private fun register(recipe: Recipe) {
        val result = when (recipe.recipeType) {
            RecipeType.Shaped -> {
                val shapedRecipe = ShapedRecipe(NamespacedKey.minecraft("cvc_${recipe.node}"), recipe.result)
                shapedRecipe.shape("123", "456", "789")
                for ((i, itemStack) in recipe.materialList.withIndex()) {
                    if (itemStack.type != Material.AIR) {
                        itemStack.data?.let {
                            shapedRecipe.setIngredient((49 + i).toChar(), it)
                        }
                    }
                }
                Bukkit.addRecipe(shapedRecipe)
            }
            RecipeType.Shapeless -> {
                val shapelessRecipe = ShapelessRecipe(NamespacedKey.minecraft("cvc_${recipe.node}"), recipe.result)
                recipe.materialList.forEach { itemStack ->
                    if (itemStack.type != Material.AIR) {
                        itemStack.data?.let { materialData ->
                            shapelessRecipe.addIngredient(materialData)
                        }
                    }
                }
                Bukkit.addRecipe(shapelessRecipe)
            }
        }
        info("${recipe.node} 注册结果 -> ${if (result) "成功" else "失败"}")
    }
}