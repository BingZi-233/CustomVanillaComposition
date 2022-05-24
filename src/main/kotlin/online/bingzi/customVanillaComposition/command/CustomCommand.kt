package online.bingzi.customVanillaComposition.command

import online.bingzi.customVanillaComposition.util.ViewUtil.openRegisterShapedView
import online.bingzi.customVanillaComposition.util.ViewUtil.openRegisterShapelessView
import org.bukkit.entity.Player
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.subCommand

@CommandHeader(
    name = "CustomVanillaComposition",
    aliases = ["cvc"],
    permission = "CustomVanillaComposition.Admin",
    permissionDefault = PermissionDefault.OP
)
object CustomCommand {

    @CommandBody
    val open = subCommand {
        literal("Shaped") {
            dynamic {
                execute<Player> { sender, _, argument ->
                    sender.openRegisterShapedView(argument)
                }
            }
        }
        literal("Shapeless") {
            dynamic {
                execute<Player> { sender, _, argument ->
                    sender.openRegisterShapelessView(argument)
                }
            }
        }
    }
}