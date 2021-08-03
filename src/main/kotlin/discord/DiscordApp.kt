package discord

import ConfigProperties
import ImageUtils
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import java.awt.Button
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import javax.swing.JFrame


object DiscordApp : ListenerAdapter() {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    lateinit var channel:MessageChannel
    private val config = ConfigProperties("capture.prop").apply {
        loadProps()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val  f= JFrame("test");
        val b =Button("take ss ");
        b.addActionListener{
            channel.sendMessage("d").queue()
        }
        f.setSize(100,100);
        b.setSize(50,50);
        f.add(b)
        f.isAlwaysOnTop=true
        f.isVisible=true
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
            channel = event.channel
            channel.sendFile(ImageUtils.getScreenShot(), "ss.jpg")
                .queue {
                    println("discord>    DiscordApp>onMessageReceived   ")
                }
        } else if (msg.contentRaw.startsWith("#c", true)) {
            val myString = msg.contentRaw.substring(2)
            println("discord>DiscordApp>onMessageReceived  Copied $myString into clip board ")

            val stringSelection = StringSelection(myString)
            clipboard.setContents(stringSelection, null)
        }
    }


}