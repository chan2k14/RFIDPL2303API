/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christiangarcia.rfidpl203.utils;

import com.christiangarcia.rfidpl203.activities.DeviceStatus;
import gnu.io.CommPortIdentifier;

/**
 *
 * @author Christian Garcia | Email: chan.garcia100@gmail.com
 */
public class Utilities {
    private CommPortIdentifier portIdentifier;
    private final java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
    
    public Utilities() {
        //Default Constructor
    }
    
    public static String getPortTypeInfo(int portType) {
        switch(portType) {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "Unknown Port Type";
        }
    }
    
    public String findDevicePort() {
        while(portEnum.hasMoreElements()) {
            portIdentifier = portEnum.nextElement();
        }
        if(portIdentifier == null) {
            return DeviceStatus.INACTIVE.tag();
        }
        else if(!portIdentifier.getName().isEmpty()) {
            return portIdentifier.getName();
        } else {
            return DeviceStatus.INACTIVE.tag();
        }
    }
}