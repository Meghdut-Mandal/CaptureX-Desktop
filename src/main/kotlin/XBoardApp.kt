import com.google.protobuf.ByteString
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import proto.*
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random


object XBoardApp : ListenerAdapter(), TyperXView {
    private val config = ConfigProperties("capture.prop").apply {
        loadProps()
    }
    private val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    private val scanner = Scanner(System.`in`)


    val jda by lazy {
        JDABuilder.createLight(
            config["discord_bot_id"],
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.DIRECT_MESSAGES
        ).addEventListeners(XBoardApp).apply {
            setBulkDeleteSplittingEnabled(false)
            setActivity(Activity.watching("Taking Screenshots"))
        }.build()
    }

    val updatesChannel by lazy { jda.getTextChannelById(876910605225836614)!! }
    val requestsChannel by lazy { jda.getTextChannelById(876910144028553306)!! }
    val deviceId = Random.nextInt(10000)

    @JvmStatic
    fun main(args: Array<String>) {
        val dt = jda.toString()
        File("ss").mkdir()
        println("Meh!")
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        if(msg.attachments.isEmpty()) return
        val attachment = msg.attachments[0]
        val input = attachment.retrieveInputStream().get()
        val message = Message.parseFrom(input)
        processMessage(message)
    }

    override fun onReady(event: ReadyEvent) {
        println(
            """
          Automated Bot connected! sending Join Event
      """.trimIndent()
        )
        sendJoinInfo()
        thread {
            inputLoop()
        }

    }

    private fun sendJoinInfo() {
        val joinPayload =
            JoinPayload.newBuilder().setId(deviceId).setDeviceType(System.getProperty("os.name"))
                .setName("${System.getProperty("user.name")}'s Pc")
        newMsg()
            .setMessageType(MessageType.UPDATE_JOIN)
            .setJoinPayload(joinPayload.build())
            .sendMessage()
    }

    private fun processMessage(message: Message) {
        if(message.id==deviceId || (message.dest>0 && message.dest!= deviceId) ) {
            return
        }

        when (message.messageType) {
            MessageType.UPDATE_JOIN -> {
                val unpack = message.joinPayload
                println("User Joined ${unpack} ")
            }
            MessageType.REQUEST_COPY_SS -> {
                val bytes = ImageUtils.getScreenShot()
                newMsg()
                    .setDest(message.id)
                    .setMessageType(MessageType.RESPONSE_COPY_SS)
                    .setBytesPayload(ByteString.copyFrom(bytes))
                    .sendMessage()
            }
            MessageType.REQUEST_COPY_TEXT ->{
                println("XBoard] Copied text ${message.textPayload} into clipbard")
                val stringSelection = StringSelection(message.textPayload)
                clipboard.setContents(stringSelection, null)
            }
            MessageType.RESPONSE_COPY_SS ->{
                val bytes = message.bytesPayload.toByteArray()
                File("ss/${System.currentTimeMillis()}.jpg").writeBytes(bytes)
                println("saved ss ")
            }
            else -> println("Unidentified Message! please update bot")
        }

    }

    private fun newMsg() = Message.newBuilder().setId(deviceId).setDest(-1)

    private fun inputLoop() {

        while (true) {
            println(
                """
                Waiting for user Input ->
                p <device id> -> copy and paste text on remote machine 
                c <device id> -> copy text to remote machine clipboard
                s <device id> -> take ss from remote machine 
                cr <device id> -> copy text from the remote clipboard to current system clipboard
            """.trimIndent()
            )
            val inp = scanner.nextLine()
            fun String.deviceId() = substring(indexOf(" ")).trim().toInt()
            when {
                inp.equals("e", true) -> System.exit(0)
                inp.startsWith("cr") -> copyFromRemote(inp.deviceId())
                inp.startsWith("c") -> copyToRemote(inp.deviceId())
                inp.startsWith("s") -> takeSSFromRemote(inp.deviceId())
                else -> println("Andha hai kiya laude ? thik se likh")
            }
        }
    }


    private fun takeSSFromRemote(deviceId: Int) {
        newMsg()
            .setDest(deviceId)
            .setMessageType(MessageType.REQUEST_COPY_SS)
            .sendMessage()
    }

    private fun copyToRemote(deviceId: Int) {
        val data = inputLine("The text to copy ..")
        newMsg()
            .setMessageType(MessageType.REQUEST_COPY_TEXT)
            .setTextPayload(data)
            .sendMessage()
    }

    private fun copyFromRemote(deviceId: Int) {

    }

    fun inputLine(msg:String):String{
        println("""
            XBoard ] $msg
        """.trimIndent())
        print("User ] ")
        return  scanner.nextLine()
    }


    override fun startUI() = Unit
    override fun stopUI() = Unit
    override fun setProgress(progress: Int) = Unit
    override fun setStatus(status: String?) = Unit

    private fun Message.Builder.sendMessage() =
        updatesChannel.sendFile(build().toByteArray(), "protobuff.bin").queue()

}



