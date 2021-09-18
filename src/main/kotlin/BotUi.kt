import de.fuerstenau.buildconfig.BuildConfig
import org.javacord.api.DiscordApi
import org.javacord.api.entity.message.Message
import org.javacord.api.entity.permission.Permissions
import org.javacord.api.event.message.MessageCreateEvent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.swing.UIManager

object BotUi : DiscordApp() {

    private val threadPool: ExecutorService = Executors.newFixedThreadPool(5)

    private val mainFrame by lazy {
        object : MainFrame() {
        override fun github() {
            openLink(BuildConfig.GITHUB_LINK)
        }

        override fun botInvite() {
            updateStatus("Generating Bot link... please wait")
            threadPool.submit {
                val createBotInvite = api.createBotInvite()
                openLink(createBotInvite)
                updateStatus("Bot link generated!")
            }
        }
    }
    }

    override fun onReady(api: DiscordApi) {
        super.onReady(api)
        mainFrame.updateStatus("Send the opt $otp in channel")
    }

    override fun postVerification(msg: Message, event: MessageCreateEvent) {
        mainFrame.updateStatus("Bot Started!")
        super.postVerification(msg, event)
    }


    @JvmStatic
    fun main(args: Array<String>) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        mainFrame.updateStatus("Stating Bot")
        mainFrame.redirectSystemStreams()
        mainFrame.isVisible = true
        threadPool.submit {
        api.toString()
        }

    }
}