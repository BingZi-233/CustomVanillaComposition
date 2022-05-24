package online.bingzi.customVanillaComposition.service

import online.bingzi.customVanillaComposition.service.impl.LegacyService
import taboolib.module.nms.MinecraftVersion

val recipeService: Service by lazy { ServiceBuilder().service }

class ServiceBuilder {
    val service: Service = if (MinecraftVersion.majorLegacy <= 11300) {
        LegacyService()
    } else {
        LegacyService()
    }

}