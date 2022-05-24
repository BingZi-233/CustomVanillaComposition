package online.bingzi.customVanillaComposition.service

import online.bingzi.customVanillaComposition.service.impl.LegacyService

val recipeService: Service by lazy { ServiceBuilder().service }

class ServiceBuilder {
    val service: Service

    init {
        service = LegacyService()
    }
}