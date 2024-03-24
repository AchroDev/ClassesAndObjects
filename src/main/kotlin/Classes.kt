// Define a class

class SmartDevice {
    fun turnOn() {
        println("Smart device has been powered on.")
    }

    fun turnOff() {
        println("Smart device has been powered off.")
    }
}

fun main() {
    val smartTvDevice = SmartDevice()
    smartTvDevice.turnOn()
    smartTvDevice.turnOff()
}