import de.fuerstenau.buildconfig.BuildConfig
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.entity.message.Message
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random


open class DiscordApp : MessageCreateListener, TyperXView {
    private val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    var channelName: String = ""
    var otp: String = ""
    private val typerWorker = TyperXWorker(this)
    private val threadpool = Executors.newFixedThreadPool(10)

    companion object {
        private const val cameraEmoji = "\uD83D\uDCF7"

        @JvmStatic
        fun main(args: Array<String>) {
            val app = DiscordApp()
        }
    }

    val api: DiscordApi by lazy {
       val api = DiscordApiBuilder().setToken(getBotKey()).login().join()
        api.addListener(this)
        onReady(api)
        api
    }

    private fun getBotKey(): String {
        if (File("capture.prop").exists()) {
            val p = Properties()
            File("capture.prop").bufferedReader().use {
                p.load(it)
            }
            println("Reading bot key from file")
            return p.getProperty("discord_bot_id")
        }
        println("Reading bot key from Jar")
        return BuildConfig.DISCORD_KEY
    }


    open fun postVerification(
        msg: Message,
        event: MessageCreateEvent
    ) {
        val channel = event.channel
        if (msg.content.length == 1) {
            takeSS(channel)
            event.deleteMessage()
        } else if (msg.content.startsWith("#c", true)) {
            val myString = msg.content.substring(2)
            println("discord>DiscordApp>onMessageReceived  Copied $myString into clip board ")

            val stringSelection = StringSelection(myString)
            clipboard.setContents(stringSelection, null)
        } else if (msg.content.startsWith("#p", true)) {
            val text = msg.content.substring(2)
            println("discord>DiscordApp>onMessageReceived  Pasting $text into area removing spaces ")
            typerWorker.isRemoveFrontSpaces = true
            typerWorker.startRequest(text)
        } else if (msg.content.startsWith("#z", true)) {
            val text = msg.content.substring(2)
            println("discord>DiscordApp>onMessageReceived  Pasting $text into area. ")
            typerWorker.isRemoveFrontSpaces = false
            typerWorker.startRequest(text)
        }
    }

    private fun takeSS(channel: TextChannel?) = threadpool.submit {
        println("Taking ss now..")
        MessageBuilder().addAttachment(ImageUtils.getScreenShot(), "ss.jpg").send(channel).get().also {
            addSSListener(it)
        }
    }

    open fun onReady(api: DiscordApi) {
        otp = Random.nextInt(1000, 9999).toString()
        println(
            """    
            Welcome to CaptureXtream (Build date: ${BuildConfig.BUILD_DATE})
            First step is to select a unique channel,
            and send this otp $otp as text message there

            Waiting for Events.....
        """.trimIndent()
        )
    }

    override fun startUI() {
        println("discord>DiscordApp>startUI  TyperX Started Typing   ")
    }

    override fun stopUI() {
        println("discord>DiscordApp>startUI  TyperX stopped Typing   ")
    }

    override fun setProgress(progress: Int) {
        print("\rdiscord>DiscordApp>startUI Typing $progress %  ")
    }

    override fun setStatus(status: String?) {
        println("discord>DiscordApp>startUI  $status   ")
    }

    override fun onMessageCreate(event: MessageCreateEvent) {
        val msg: Message = event.message
        if (event.isServerMessage) {
            if (channelName == "") {
                println("Received  Message ${msg.content}  in ${msg.channel.id}  ")
                if (msg.content == otp) {
                    println("OTP verified ! Connected to Channel ${event.channel.id}")
                    channelName = msg.channel.id.toString()
                    otp = ""
                    msg.delete()
                    MessageBuilder()
                        .setContent("Touch the Camera emoji in this message to take a ss ")
                        .send(event.channel)
                        .get().also {
                            addSSListener(it)
                        }
                }
            } else if (channelName == msg.channel.id.toString())
                postVerification(msg, event)
        }
    }


    private fun addSSListener(msg: Message) {
        msg.addReaction(cameraEmoji)
        msg.addReactionAddListener {
            if (it.emoji.equalsEmoji(cameraEmoji) && it.count.get() > 1) {
                takeSS(msg.channel)
            }
        }.removeAfter(5, TimeUnit.MINUTES)
    }


}