/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christiangarcia.rfidpl203.utils;

import gnu.io.SerialPort;

/**
 *
 * @author Christian Garcia | Email: chan.garcia100@gmail.com
 */
public class DeviceConfiguration {
    private static String portNumber;
    private static int timeout, bitrate, dataBits, stopBits, parity;
    private Utilities utilities = new Utilities();
    
    //Default Configuration
    public DeviceConfiguration(int timeout) {
        setTimeout(timeout * 1000);
        setPortNumber(utilities.findDevicePort());
        setBitrate(9600);
        setDataBits(SerialPort.DATABITS_8);
        setStopBits(SerialPort.STOPBITS_1);
        setParity(SerialPort.PARITY_NONE);
    }
    
    //Custom Configuration -> Required to include Port Settings method (experimental)
    public DeviceConfiguration(String portNumber, int timeout) {
        setPortNumber(portNumber);
        setTimeout(timeout * 1000);
    }
    
    public void portSettings(int bitrate, int dataBits, int stopBits, int parity) {
        setBitrate(bitrate);
        setDataBits(dataBits);
        setStopBits(stopBits);
        setParity(parity);
    }
    
    /**
     * @return the portNumber
     */
    public static String getPortNumber() {
        return portNumber;
    }

    /**
     * @param aPortNumber the portNumber to set
     */
    public static void setPortNumber(String aPortNumber) {
        portNumber = aPortNumber;
    }

    /**
     * @return the timeout
     */
    public static int getTimeout() {
        return timeout;
    }

    /**
     * @param aTimeout the timeout to set
     */
    public static void setTimeout(int aTimeout) {
        timeout = aTimeout;
    }

    /**
     * @return the bitrate
     */
    public static int getBitrate() {
        return bitrate;
    }

    /**
     * @param aBitrate the bitrate to set
     */
    public static void setBitrate(int aBitrate) {
        bitrate = aBitrate;
    }

    /**
     * @return the dataBits
     */
    public static int getDataBits() {
        return dataBits;
    }

    /**
     * @param aDataBits the dataBits to set
     */
    public static void setDataBits(int aDataBits) {
        dataBits = aDataBits;
    }

    /**
     * @return the stopBits
     */
    public static int getStopBits() {
        return stopBits;
    }

    /**
     * @param aStopBits the stopBits to set
     */
    public static void setStopBits(int aStopBits) {
        stopBits = aStopBits;
    }

    /**
     * @return the parity
     */
    public static int getParity() {
        return parity;
    }

    /**
     * @param aParity the parity to set
     */
    public static void setParity(int aParity) {
        parity = aParity;
    }  
}
