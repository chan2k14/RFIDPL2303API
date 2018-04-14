/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christiangarcia.rfidpl203.activities;

import com.christiangarcia.rfidpl203.activities.DeviceListener.SerialReaderEvent;
import com.christiangarcia.rfidpl203.utils.DeviceConfiguration;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.TooManyListenersException;

/**
 *
 * @author Christian Garcia | Email: chan.garcia100@gmail.com
 */
public class DeviceConnector {
    private CommPortIdentifier portIdentifier;
    private final java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
    
    public DeviceConnector() {
        //Default Constructor
    }
    
    public String findDevice() {
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
    
    public void connect() {
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(DeviceConfiguration.getPortNumber());
            //System.out.println("Device Port: " + portIdentifier.getName() + "\nType: " + Utilities.getPortTypeInfo(portIdentifier.getPortType()));
            if(portIdentifier.isCurrentlyOwned()) {
                System.out.println(DeviceStatus.BUSY.tag());
                System.exit(0);
            } else if(!findDevice().equals(DeviceStatus.INACTIVE.tag())) {
                CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
                if(commPort instanceof SerialPort) {
                    DataValues.setSerialPort((SerialPort) commPort);
                    DataValues.getSerialPort().setSerialPortParams(DeviceConfiguration.getBitrate(),DeviceConfiguration.getDataBits(),DeviceConfiguration.getStopBits(),DeviceConfiguration.getParity());
                    DataValues.setInStream(DataValues.getSerialPort().getInputStream());
                    DataValues.setOutStream(DataValues.getSerialPort().getOutputStream());
                    DataValues.getSerialPort().addEventListener(new SerialReaderEvent());
                    DataValues.getSerialPort().notifyOnDataAvailable(true);
                    //DataValues.getOutStream().write("yes".getBytes());
                    System.out.println(DeviceStatus.OK.tag());
                } else {
                    System.out.println(DeviceStatus.UNSUPPORTED.tag());
                }
            } else {
                System.out.println(DeviceStatus.INACTIVE.tag());
            }
        } catch (PortInUseException | TooManyListenersException ex) {
            System.out.println(DeviceStatus.BUSY.tag());
            System.exit(0);
        } catch (NoSuchPortException | IOException ex) {
            System.out.println(DeviceStatus.INACTIVE.tag());
            System.exit(0);
        } catch (UnsupportedCommOperationException ex) {
            System.out.println(DeviceStatus.UNSUPPORTED.tag());
            System.exit(0);
        }
    }
}
