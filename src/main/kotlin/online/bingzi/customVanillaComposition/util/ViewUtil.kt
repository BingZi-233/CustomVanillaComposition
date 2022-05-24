package online.bingzi.customVanillaComposition.util

import online.bingzi.customVanillaComposition.entity.Recipe
import online.bingzi.customVanillaComposition.entity.RecipeType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.library.xseries.XMaterial
import taboolib.module.chat.colored
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Basic
import taboolib.platform.util.buildItem

object ViewUtil {
    fun Player.openRegisterShapedView(argument: String) {
        this.openMenu<Basic>("注册配方 - 类型：有序") {
            init()
            button(RecipeType.Shaped, argument)
//            debug()
        }
    }

    fun Player.openRegisterShapelessView(argument: String) {
        this.openMenu<Basic>("注册配方 - 类型：无序") {
            init()
            button(RecipeType.Shapeless, argument)
//            debug()
        }
    }

    private fun Basic.init() {
        this.rows(3)
        val item: ItemStack = buildItem(XMaterial.BLACK_STAINED_GLASS_PANE) {
            this.name = "&f&l[ &7&l边界 &f&l]".colored()
            this.lore.clear()
            this.lore.add("&7&m----------------------".colored())
            this.lore.add("&8>  &8&o好像和空间牢牢的固".colored())
            this.lore.add("&8>  &8&o定在了一起,无法点击".colored())
        }
        val boundary = mutableListOf(0, 4, 5, 6, 7, 8, 9, 13, 14, 16, 18, 22, 23, 24, 25, 26)
        boundary.forEach {
            set(it, item)
        }
        onClick { event ->
            if (boundary.contains(event.rawSlot)) {
                event.isCancelled = true
            }
        }
    }

    private fun Basic.button(type: RecipeType, argument: String) {
        val item: ItemStack = buildItem(XMaterial.RED_STAINED_GLASS_PANE) {
            this.name = "&f&l[ &f&l确认 &f&l]".colored()
            this.lore.clear()
        }
        set(17, item)
        onClick { event ->
            if (event.rawSlot == 17) {
                event.isCancelled = true
                val composite = event.inventory.getItem(15) ?: return@onClick
                val materialList = mutableListOf<ItemStack>()
                mutableListOf(1, 2, 3, 10, 11, 12, 19, 20, 21).forEach {
                    val itemStackTemp = event.inventory.getItem(it) ?: ItemStack(Material.AIR)
                    materialList.add(itemStackTemp)
                }
                val recipe = Recipe(
                    node = argument,
                    recipeType = type,
                    materialList = materialList,
                    result = composite,
                )
                recipe.registerRecipe()
                recipe.writeDataFile()
                event.clicker.closeInventory()
            }
        }
    }
}