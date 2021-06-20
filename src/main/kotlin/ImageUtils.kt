import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

object ImageUtils {

    private val robot = Robot()
    private val screenRect = Rectangle(Toolkit.getDefaultToolkit().screenSize)

     fun getScreenShot(): ByteArray {
        val capture: BufferedImage = robot.createScreenCapture(screenRect)
        val bios = ByteArrayOutputStream()
        ImageIO.write(capture, "JPG", bios)
        return bios.toByteArray()
    }
}
//  