package online.bingzi.customVanillaComposition.util

import org.bukkit.NamespacedKey
import taboolib.platform.BukkitPlugin

object NamespacedKeyUtil {
    private val namespacedKey by lazy { NamespacedKey(BukkitPlugin.getInstance(), "CustomVanillaComposition") }

    fun getPluginNamespacedKey(): NamespacedKey {
        return namespacedKey
    }
}