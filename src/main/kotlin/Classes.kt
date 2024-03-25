// Define a class

class SmartDevice {

    val name = "Android TV"
    val category = "Entertainment"
    var deviceStatus = "online"
    fun turnOn() {
        println("Smart device has been powered on.")
    }

    fun turnOff() {
        println("Smart device has been powered off.")
    }
}

fun main() {
    val smartTvDevice = SmartDevice()
    println("Device name is: ${smartTvDevice.name}")
    smartTvDevice.turnOn()
    smartTvDevice.turnOff()
}