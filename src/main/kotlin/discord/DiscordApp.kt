package discord

import ConfigProperties
import ImageUtils
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent


object DiscordApp : ListenerAdapter() {
    private val config = ConfigProperties("capture.prop").apply {
        loadProps()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val builder: JDABuilder = JDABuilder.createLight(
            config["discord_bot_id"],
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.DIRECT_MESSAGES
        ).addEventListeners(DiscordApp)
        builder.setBulkDeleteSplittingEnabled(false)
        builder.setActivity(Activity.watching("Taking Screenshots"))

        builder.build()


    }


    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg: Message = event.message
        if (msg.contentRaw.length == 1) {
            val channel = event.channel
            channel.sendFile(ImageUtils.getScreenShot(), "ss.jpg")
                .queue {
                    println("discord>DiscordApp>onMessageReceived   ")
                }
        }
    }


}