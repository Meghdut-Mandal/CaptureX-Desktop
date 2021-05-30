import com.meghdut.ui.LoginUI
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.request.SendPhoto
import java.io.File
import kotlin.random.Random


object MainApp {

    private var chatIdList = mutableListOf<Long>()

    private val loginUI = LoginUI()

    private var verifyCode = 0

    var finalChatID = 0L


    @JvmStatic
    fun main(args: Array<String>) {
        val bot = TelegramBot("1833118457:AAF_e597d-S-2fTRB9qyRZ-svPxQvG6LpKM")

        bot.setUpdatesListener { updates: List<Update?>? ->
            println(">MainApp>main  $updates ")
            updates?.forEach { update ->
                val chatId = update?.message()?.chat()?.id()
                if (chatId != null) {
                    bot.execute(SendMessage(chatId, "ChatID= $chatId "))
                    chatIdList.add(chatId)
                }
            }
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }

        showLogin(bot)
    }

    private fun showLogin(bot: TelegramBot) {
        loginUI.setVerifyButtonListener {
            if (chatIdList.contains(it.toLong())) {
                loginUI.info("Wrong chat ID!")
            } else {
                finalChatID = it.toLong()
                val codeList = bot.sendVerificationCode()
                loginUI.setUp(codeList)
            }
        }

        loginUI.setUpCodeListener {
            val code = it.toInt()
            if (code == verifyCode) {
                loginUI.info("Channel Verification Completed Successfully!")
                loginUI.dispose()
            }
        }
    }


    private fun TelegramBot.sendVerificationCode(): MutableList<String> {
        verifyCode = Random.nextInt(10, 99)
        execute(SendMessage(finalChatID, "Verification ID= $verifyCode "))
        val codeList = (0..3).map { Random.nextInt(10, 99).toString() }.toMutableList()
        codeList.add(verifyCode.toString())
        return codeList
    }

    fun TelegramBot.sendNudes(chatID: Long) {
        val msg: SendPhoto = SendPhoto(chatIdList, File("test.png")).caption("ssxsxs")
        execute(msg)
    }


}