import com.meghdut.ui.LoginUI
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.request.SendPhoto
import java.io.File
import java.util.*
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.system.exitProcess


object MainApp {

    private var chatIdList = mutableListOf<Long>()

    private val loginUI = LoginUI()

    private var verifyCode = 0

    private var finalChatID = 0L

    private val configFile = File("capturex.conf")

    const val CHAT_ID = "chat_id"

    private val properties = Properties()



    @JvmStatic
    fun main(args: Array<String>) {
        val bot = TelegramBot("1833118457:AAF_e597d-S-2fTRB9qyRZ-svPxQvG6LpKM")

        bot.setUpdatesListener { updates: List<Update?>? ->
            println(">MainApp>main  $updates ")
            updates?.forEach { update ->
                val chatId = update?.message()?.chat()?.id()
                if (chatId != null) {
                    bot.execute(SendMessage(chatId, "ChatID= ${encryptChatId(chatId)} "))
                    chatIdList.add(chatId)
                }
            }
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }

        if (!properties.contains(CHAT_ID)) {
            showLogin(bot)
        } else {
            startCaptureX(bot)
        }
    }

    private fun startCaptureX(bot: TelegramBot) {
        loadProps()
        finalChatID = properties[CHAT_ID]?.toString()?.toLong() ?: 0
        println("ChatID $finalChatID")
        exitProcess(0)
    }

    private fun showLogin(bot: TelegramBot) {
        loginUI.setVerifyButtonListener {

            val decryptedChatId = decryptChatId(it)
            if (!chatIdList.contains(decryptedChatId)) {
                loginUI.info("Wrong chat ID!")
            } else {
                finalChatID = decryptedChatId
                val codeList = bot.sendVerificationCode()
                loginUI.setUp(codeList)
            }
        }

        loginUI.setUpCodeListener {
            val code = it.toInt()
            if (code == verifyCode) {
                loginUI.info("Channel Verification Completed Successfully!")
                saveConfig()
                loginUI.dispose()
            }
        }
        loginUI.isVisible = true
    }


    private fun TelegramBot.sendVerificationCode(): MutableList<String> {
        verifyCode = Random.nextInt(10, 99)
        execute(SendMessage(finalChatID, "Verification ID= $verifyCode "))
        val codeList = (0..2).map { Random.nextInt(10, 99).toString() }.toMutableList()
        codeList.add(verifyCode.toString())
        return codeList
    }

    fun TelegramBot.sendNudes(chatID: Long) {
        val msg: SendPhoto = SendPhoto(chatIdList, File("test.png")).caption("ssxsxs")
        execute(msg)
    }

    private fun saveConfig() {
        loadProps()
        properties[CHAT_ID] = finalChatID.toString()
        configFile.delete()
        configFile.outputStream().use {
            properties.store(it, "${System.currentTimeMillis()}")
        }
    }

    private fun loadProps() {
        configFile.inputStream().use {
            properties.load(it)
        }
    }

    private fun encryptChatId(chatID: Long): String {
        return chatID.absoluteValue.toString(36)
    }

    private fun decryptChatId(chatID: String): Long {

        return chatID.toLong(36) * -1
    }


}