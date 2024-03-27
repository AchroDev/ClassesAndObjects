// Define a class

// In Kotlin classes are final by default, you need to add "open" to be able to extend the class.
// Updated the class to now be a "protected" constructor instead of an openly available one.
internal open class SmartDevice protected constructor (val name: String, val category: String) {

    // Defines the class property "deviceStatus" so that other devices will know if it is on or off.
    //var deviceStatus = "online"

    // Setting the "protected" visibility modifier on the "set()" property to protect it from anything outside the class or it's children.
    // We are not performing any actions or checks within the set() function, so we are omitting the "()" and body of it.
    var deviceStatus = "online"
        protected set

    // Defines the deviceType property of the SmartDevice class. Default is "unknown".
    open val deviceType = "unknown"

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

    // Sets the device volume property and function to specify the available range.
    private var speakerVolume = 2
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    // Example of the "private" visibility modifier on the setter "set()" function.
    //var speakerVolume = 2
    //        private set(value) {
    //            if (value in 0..100) {
    //                field = value
    //            }
    //        }

    // Sets the device channel number property and function to specify the available range.
    private var channelNumber = 1
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    // Defines the method to increase the speaker volume and prints the current value.
    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume")
    }

    // Defines the method to increase the channel number and prints the current value. Now using the "protected" visibility modifier.
    protected fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber")
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

    // Defining the initial brightness for the light.
    private var brightnessLevel = 0
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    // Defining the method to increase the brightness.
    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness level increased to $brightnessLevel")
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

    // Defining a method to count the amount of times a device has been power cycled.
    var deviceTurnOnCount = 0
        private set

    // Method to turn on the Smart TV. Implementing the power cycle counter.
    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    // Method to turn off the Smart TV. Implementing the power cycle counter.
    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    // Method to increase the Smart TV volume.
    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    // Method to change to the next channel on the Smart TV.
    fun changeTvChannelToNexT() {
        smartTvDevice.nextChannel()
    }

    // Method to turn on the Smart Light. Implementing the power cycle counter.
    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    // Method to turn off the Smart Light. Implementing the power cycle counter.
    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    // Method to increase the Smart Lights brightness.
    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    // Method to turn off all Smart Devices.
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}


//... C'mon... you know what the main function is....
fun main() {

    // Initializing the smartDevice variable to define a device from one of the classes and execute the code.
    var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()

    // Reassigning the smartDevice variable from a TV to a Light, providing an example of polymorphism.
    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
}