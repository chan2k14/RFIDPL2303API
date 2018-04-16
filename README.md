# RFIDPL2303API

A [RFID/SerialPort API](https://github.com/chan2k14/RFIDPL2303API) intended for [Profilic PL2303 Chip](http://www.prolific.com.tw/US/ShowProduct.aspx?p_id=156&pcid=41), EEPROM Writer.

This library is intended to be a cross-platform development within **Java**, **C#** and **Kotlin** that will guarantee to work between **Windows** and **Linux** Environment.

---
## Requirements

To allow the API to work flawlessly in the first run, you should comply this following software setup path in your specified IDE:

### Java

#### Java Development Kit version 1.8.0

>Supports above than the mentioned version and you can also try in lower versions. Just bump me an issue or you can pull a request if you want to contribute to the library. :smiley:

#### Driver:

* [Profilic PL2303 HXD - Windows OS](http://www.prolific.com.tw/US/ShowProduct.aspx?p_id=225&pcid=41)

#### Plugin:

###### Serial and Parallel Communication Reader/Writer

* [RXTX (Java/Kotlin) - Windows and Linux OS ](http://fizzed.com/oss/rxtx-for-java)

###### Setup:

>1. Copy **RXTXcomm.jar** in `../Java/jdk/'x.x.x_xx'/jre/lib/ext/`
>
>2. Copy **rxtxParallel.dll** and **rxtxSerial.dll** in `../Java/jdk/'x.x.x_xx'/jre/bin/`
>
>**Note: The hypened text may depend on the version of jdk setup in your IDE.**

### C#.NET

### [RFIDPL2303API](https://github.com/chan2k14/RFIDPL2303API/releases)

>Download the library that is compatible with the certain programming language that you used.

---
## Implementation

### Java

1. Call **Device Listener** and **Device Connector** class:

Code:
```Java
DeviceListener devList = new DeviceListener();
DeviceConnector devConn = new DeviceConnector();
```

2. Call **Device Configuration** with these two different approaches:

#### Default Device Configuration

Code:
```Java
//Timeout is derived by SECONDS
DeviceConfiguration devConfig = new DeviceConfiguration(int timeout);

//Sample Code
DeviceConfiguration devConfig = new DeviceConfiguration(2);    
```

#### Custom Device Configuration (Experimental)

Code:
```Java
//Timeout is derived by SECONDS
DeviceConfiguration devConfig = new DeviceConfiguration(String portNumber, int timeout);
devConfig.portSettings(int bitrate, int databits, int stopbits, int parity);

//Sample Code
DeviceConfiguration devConfig = new DeviceConfiguration("COM10", 2);
devConfig.portSettings(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
```

**Note:**

* You can refer [SerialPort Java Documentation](https://docs.oracle.com/cd/E17802_01/products/products/javacomm/reference/api/javax/comm/SerialPort.html) for more details.

* In Windows Environment, you can modify or see more details about the serial port device configuration from `Device Manager -> Ports`. (i.e. Port Number and Port Settings)

![alt text](https://docs.google.com/uc?id=1gHQLHRIeOPdTOFex_QaR5m6GWcwE8gD5)
*An image displaying the device manager (red outline rectangle box) with the specified device port settings (green outline rectangle box)*

3. Use **connect()** method from the *Device Connector* class to start the device reader event:

Code:
```Java
devConn.connect();
```

**Note:**

* Be sure the device is connected to the computer before calling this method or else it will receive an error log labeled `Status: Device Not Found`.

* You can call this method again if **Step 1 & 2** already exists, even if you use the method **terminateDevice()**.

4. Use **readSensorData()** method from the *Device Listener* class to read rfid chip or tag data from the device with these two different approaches:

#### Read RFID Data Once

Code:
``` Java
while(devList.readSensorData().equals("Serial Reader Timeout")) {
    devList.readSensorData();
}
devList.readSensorData();
devList.terminateDevice();
```


#### Read RFID Data Continuously

Code: 
``` Java
while(devList.readSensorData().equals("Serial Reader Timeout")) {
    devList.readSensorData();
}
devList.readSensorData();
...
...
...
devList.terminateDevice();
```

**Note:**

* Be sure to use **terminateDevice()** to properly close the device reader event or else it will receive an error log labeled `Status: Device is Busy` by the time when using the **Step 3** again.

* The two methods mentioned earlier have return value.

---
## Troubleshooting

- You can use **Utilities** class to see the port type and the device port whenever your computer have multiple ports installed.

1. Call Utilities class:

### Java

Code:
```Java
Utilities utilities = new Utilities();
```

2. You can use this two methods with its different operations. Both are return methods:

#### Verifying the Port Type Info

Code:
```Java
utilities.getPortTypeInfo();
```

#### Finding the Device Port

Code:
```Java
utilities.findDevicePort();
```

---
## Source

[RXTX for Java](http://fizzed.com/oss/rxtx-for-java)

[Profilic PL2303HXD Documentation](http://www.prolific.com.tw/US/ShowProduct.aspx?p_id=156&pcid=41)
