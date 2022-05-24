package online.bingzi.customVanillaComposition.entity

import online.bingzi.customVanillaComposition.service.recipeService
import org.bukkit.inventory.ItemStack
import taboolib.library.xseries.parseToItemStack
import taboolib.module.configuration.Type
import taboolib.module.configuration.createLocal
import taboolib.platform.util.serializeToByteArray

data class Recipe(
    val node: String,
    val recipeType: RecipeType,
    val materialList: MutableList<ItemStack>,
    val result: ItemStack,
) {
    fun writeDataFile() {
        val createLocal = createLocal(path = "craft/$node.json", saveTime = 200, type = Type.JSON)
        createLocal["Node"] = node
        createLocal["RecipeType"] = recipeType
        createLocal["Material"] = materialList.map { it.serializeToByteArray() }
        createLocal["Result"] = result.serializeToByteArray()
    }

    fun registerRecipe(){
        recipeService.registerRecipe(this)
    }

    companion object {
        fun readDataFile(node: String): Recipe? {
            val createLocal = createLocal(path = "craft/$node.json", saveTime = 200, type = Type.JSON)
            return Recipe(
                node = createLocal.getString("Node") ?: return null,
                recipeType = createLocal.getEnum("RecipeType", RecipeType::class.java) ?: return null,
                materialList = createLocal.getStringList("Material").map { it.parseToItemStack() }.toMutableList(),
                result = (createLocal.getString("Result") ?: return null).parseToItemStack()
            )
        }
    }
}
