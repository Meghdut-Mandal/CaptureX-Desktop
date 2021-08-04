package discord

import ConfigProperties
import ImageUtils
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import org.slf4j.Logger
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import kotlin.random.Random


object DiscordApp : ListenerAdapter() {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    var channelName: String = ""
    var otp: String = ""
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
        if (channelName == "") {
            println("Received  Message ${msg.contentRaw}  in ${msg.channel.name}  ")
            if (msg.contentRaw == otp) {
                println("OTP verified ! Connected to Channel ${msg.channel.name}")
                channelName = msg.channel.name
                otp = ""
            }
        } else
            postVerification(msg, event)
    }

    fun postVerification(
        msg: Message,
        event: MessageReceivedEvent
    ) {
        if (msg.contentRaw.length == 1) {
            val channel = event.channel

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

    override fun onReady(event: ReadyEvent) {
        otp = Random.nextInt(1000, 9999).toString()
        println(
            """
            Welcome to CaptureXtream 
            First step is to select a unique channel,
            and send this otp $otp as text message there
            
            Waiting for Events.....
        """.trimIndent()
        )

    }


}