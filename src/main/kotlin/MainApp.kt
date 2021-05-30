import com.google.gson.Gson
import com.meghdut.ui.LoginUI
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
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

    private val configFile = File("capture.prop")

    const val CHAT_ID = "chat_id"
    const val BOT_ID="bot_id"

    private var isVerified = false

    private val properties = Properties()

    private val gson = Gson()


    @JvmStatic
    fun main(args: Array<String>) {
        loadProps()
        val bot = TelegramBot(properties[BOT_ID].toString())

        bot.setUpdatesListener { updates: List<Update?>? ->
            handleUpdates(updates, bot)
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }

        if (!properties.contains(CHAT_ID)) {
            showLogin(bot)
        } else {
            startCaptureX(bot)
        }
    }

    private fun handleUpdates(
        updates: List<Update?>?,
        bot: TelegramBot
    ) {
        updates?.forEach { update ->
            println(">MainApp>main  ${gson.toJson(update)} ")

            val message = update?.message() ?: return@forEach
            val chatId = message.chat()?.id()
            bot.sendChatID(message)
        }
    }

    private fun TelegramBot.sendChatID(
        message: Message
    ) {
        if (finalChatID != 0L) return
        val chatId = message.chat()?.id() ?: return
        if (message.newChatMembers()
                ?.any { it.username() == "capture_x_bot" } == true && !chatIdList.contains(chatId)
        ) {
            execute(SendMessage(chatId, "ChatID= ${encryptChatId(chatId)} "))
            println(">MainApp>handleUpdates  Sent ID for verification ")
            chatIdList.add(chatId)
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
                bot.execute(
                    SendMessage(finalChatID, "Verification Completed!").replyMarkup(
                        InlineKeyboardMarkup(InlineKeyboardButton("Capture Screen"))
                    )
                )
                isVerified=true
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

    private fun encryptChatId(chatID: Long?): String? {
        return chatID?.absoluteValue?.toString(36)
    }

    private fun decryptChatId(chatID: String): Long {

        return chatID.toLong(36) * -1
    }


}