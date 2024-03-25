// Define a class

class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"

    constructor(name: String, category: String, statusCode: Int) : this("Android TV", "Entertainment") {
        deviceStatus = when (statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }

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