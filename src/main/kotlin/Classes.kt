import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// Define a class

// In Kotlin classes are final by default, you need to add "open" to be able to extend the class.
// Updated the class to now be a "protected" constructor instead of an openly available one.
open class SmartDevice (val name: String, val category: String) {

    // Defines the class property "deviceStatus" so that other devices will know if it is on or off.
    //var deviceStatus = "online"

    // Setting the "protected" visibility modifier on the "set()" property to protect it from anything outside the class or it's children.
    // We are not performing any actions or checks within the set() function, so we are omitting the "()" and body of it.
    open var deviceStatus = "online"
        protected set

    // Defines the deviceType property of the SmartDevice class. Default is "unknown".
    open val deviceType = "unknown"

    // CHALLENGE: Define a "printDeviceInfo" method to print the following string.
    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }

    // Defines class method to turn on the device.
    open fun turnOn() {
        deviceStatus = "on"
    }

    // Defines class method to turn off the device.
    open fun turnOff() {
        deviceStatus = "off"
    }
}

/*
deviceName and deviceCategory are specific to the subclasses and need to be defined as so within the -
- constructor definitions/parameters. These are not considered class properties.
*/
// This subclass creates an IS-A inheritance relationship to the primary class SmartDevice
class SmartTvDevice(deviceName: String, deviceCategory: String) : SmartDevice(name = deviceName, category = deviceCategory) {

    // Defines the device type as "Smart TV" overriding the method from the superclass.
    override val deviceType = "Smart TV"

    // Sets the device volume property, now using the delegate "RangeRegulator" class to define the properties.
    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    // Example of the "private" visibility modifier on the setter "set()" function.
    //var speakerVolume = 2
    //        private set(value) {
    //            if (value in 0..100) {
    //                field = value
    //            }
    //        }

    // Sets the device channel number property, now using the delegate "RangeRegulator" class to define the properties.
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    // Defines the method to increase the speaker volume and prints the current value.
    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume")
    }

    //CHALLENGE: define "decreaseVolume" method that decreases the volume
    fun decreaseSpeakerVolume() {
        speakerVolume--
        print("Speaker volume decreased to $speakerVolume")
    }

    // Defines the method to increase the channel number and prints the current value.
    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber")
    }

    //CHALLENGE: define "previousChannel" method that navigates to the previous channel.
    fun previousChannel() {
        channelNumber--
        println("Channel number decreased to $channelNumber")
    }

    // Method to turn off the TV and assign properties. now uses the "super" keyword to pull from the superclass
    override fun turnOn() {
        super.turnOn()
        println("$name is turned on. Speaker volume is set to $speakerVolume and channel number is " + "set to $channelNumber.")
    }

    // Method to turn on the TV and assign properties. now uses the "super" keyword to pull from the superclass
    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

// Defining a subclass to create a smart light off of the SmartDevice primary class.
class SmartLightDevice(deviceName: String, deviceCategory: String) : SmartDevice(name = deviceName, category = deviceCategory) {

    // Defines the device type as "Smart Light" overriding the method from the superclass.
    override val deviceType = "Smart Light"

    // Defining the initial brightness for the light. Now using the delegate "RangeRegulator" class to define the properties.
    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    // Defining the method to increase the brightness.
    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness level increased to $brightnessLevel")
    }

    //CHALLENGE: Define a method to decrease the brightness.
    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness level decreased to $brightnessLevel")
    }

    // Method to turn on the lights and assign properties. now uses the "super" keyword to pull from the superclass
    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }
    
    // Method to turn off the lights and assign properties. now uses the "super" keyword to pull from the superclass.
    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("$name turned off.")
    }
}

// Defining a new "SmartHome" subclass that provides an example of the HAS-A relationship to the SmartTvDevice and light.
class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    // CHALLENGE: ensure that all actions can only be performed when each device's "deviceStatus" is set to "on".
    // CHALLENGE: define decreaseTvVolume(), changeTvChannelToPrevious(), printSmartTvInfo(), printSmartLightInfo(), and decreaseLightBrightness() method.

    // Defining a method to count the amount of times a device has been power cycled.
    var deviceTurnOnCount = 0
        private set

    // Method to turn on the Smart TV. Implementing the power cycle counter.
    fun turnOnTv() {
        if (smartTvDevice.deviceStatus == "on") {
            deviceTurnOnCount++
            smartTvDevice.turnOn()
        } else {
            println("The Smart TV is already on.")
        }
    }

    // Method to turn off the Smart TV. Implementing the power cycle counter.
    fun turnOffTv() {
        if (smartTvDevice.deviceStatus == "on") {
            deviceTurnOnCount--
            smartTvDevice.turnOff()
        } else {
            println("The Smart TV is already off.")
        }
    }

    // Method to increase the Smart TV volume.
    fun increaseTvVolume() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.increaseSpeakerVolume()
        } else {
            println("Please turn on the TV to adjust the volume.")
        }
    }


    // Method to decrease the Smart TV volume.
    fun decreaseTvVolume() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.decreaseSpeakerVolume()
        } else {
            println("Please turn on the TV to adjust the volume.")
        }
    }

    // Method to change to the next channel on the Smart TV.
    fun changeTvChannelToNexT() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.nextChannel()
        } else {
            println("Please turn on the TV to adjust the channel.")
        }
    }

    // Method to change to the previous channel on the Smart TV.
    fun changeTvChannelToPrevious() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.previousChannel()
        } else {
            println("Please turn on the TV to adjust the channel.")
        }
    }

    // Method to print the information about the TV
    fun printSmartTvInfo(){
        smartTvDevice.printDeviceInfo()
    }

    // Method to turn on the Smart Light. Implementing the power cycle counter.
    fun turnOnLight() {
        if (smartLightDevice.deviceStatus == "on") {
            deviceTurnOnCount++
            smartLightDevice.turnOn()
        } else {
            println("The Smart Light is already on.")
        }
    }

    // Method to print the information about the Light
    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }

    // Method to turn off the Smart Light. Implementing the power cycle counter.
    fun turnOffLight() {
        if (smartLightDevice.deviceStatus == "on") {
            deviceTurnOnCount--
            smartLightDevice.turnOff()
        } else {
            println("The Smart Light is already off.")
        }
    }

    // Method to increase the Smart Lights brightness.
    fun increaseLightBrightness() {
        if (smartLightDevice.deviceStatus == "on") {
            smartLightDevice.increaseBrightness()
        } else {
            println("Please turn on the Smart Light before adjusting the brightness.")
        }
    }

    // Method to decrease the Smart Lights brightness.
    fun decreaseLightBrightness() {
        if (smartLightDevice.deviceStatus == "on") {
            smartLightDevice.decreaseBrightness()
        } else {
            println("Please turn on the Smart Light before adjusting the brightness.")
        }
    }

    // Method to turn off all Smart Devices.
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

// Creating a "ReadWriteProperty" delegate interface under the "RangeRegulator" class.
class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    // Setting the backing field variable
    var fieldData = initialValue

    // Method to get the value
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    // Method to set the value
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

//... C'mon... you know what the main function is....
fun main() {

    // Initializing the smartDevice variable to define a device from one of the classes and execute the code.
    var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()
    smartDevice.printDeviceInfo()

    // Reassigning the smartDevice variable from a TV to a Light, providing an example of polymorphism.
    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
    smartDevice.printDeviceInfo()

}