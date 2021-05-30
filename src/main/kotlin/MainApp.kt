import com.google.gson.Gson
import com.meghdut.ui.LoginUI
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.DeleteMessage
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.request.SendPhoto
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.imageio.ImageIO
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.system.exitProcess


object MainApp {

    private val loginUI = LoginUI()

    private val configFile = File("capture.prop")
    const val CHAT_ID = "chat_id"
    const val BOT_ID = "bot_id"
    private val screenRect = Rectangle(Toolkit.getDefaultToolkit().screenSize)
    private val properties = Properties()
    private val gson = Gson()
    private var chatIdList = mutableListOf<Long>()
    private var verifyCode = 0
    private var finalChatID = 0L
    private var isVerified = false
    private val tasks = Executors.newCachedThreadPool()

    @JvmStatic
    fun main(args: Array<String>) {
        loadProps()
        val bot = TelegramBot(properties[BOT_ID].toString())

        bot.setUpdatesListener { updates: List<Update?>? ->
            handleUpdates(updates, bot)
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }


        if (properties[CHAT_ID]==null) {
            showLogin(bot)
        } else {
            isVerified = true
            finalChatID = properties[CHAT_ID].toString().toLong()
            println(">MainApp>main  Verified   ")
        }
    }

    private fun handleUpdates(
        updates: List<Update?>?,
        bot: TelegramBot
    ) {
        updates?.forEach { update ->
            println(">MainApp>main  ${gson.toJson(update)} ")
            val message = update?.message() ?: return@forEach
            bot.sendChatID(message)
            if (isVerified && message.text()?.length == 1) {
                bot.sendSS(message.messageId())
            }
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
                isVerified = true
                println(">MainApp>showLogin  Verified!! ")
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

    fun TelegramBot.sendNudes() {
        val msg: SendPhoto = SendPhoto(finalChatID, File("test.png")).caption("ssxsxs")
        execute(msg)
    }

    private fun saveConfig() {
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


    private val robot = Robot()

    private fun TelegramBot.sendSS(messageId:Int) = tasks.execute {
        val capture: BufferedImage = robot.createScreenCapture(screenRect)
        val bios = ByteArrayOutputStream()
        ImageIO.write(capture, "png", bios)
        val photo = SendPhoto(finalChatID, bios.toByteArray()).caption("Screen Shot")
        execute(photo)
        val del=DeleteMessage(finalChatID,messageId)
        println(">MainApp>sendSS  Sent pic  ")
        execute(del)
    }
}

