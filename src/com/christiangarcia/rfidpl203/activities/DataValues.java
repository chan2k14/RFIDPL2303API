/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christiangarcia.rfidpl203.activities;

import gnu.io.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Christian Garcia | Email: chan.garcia100@gmail.com
 */
public class DataValues {
    private static SerialPort serialPort;
    private static InputStream inStream;
    private static OutputStream outStream;
    private static String buffer_serialData;

    /**
     * @return the serialPort
     */
    public static SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     * @param aSerialPort the serialPort to set
     */
    public static void setSerialPort(SerialPort aSerialPort) {
        serialPort = aSerialPort;
    }

    /**
     * @return the inStream
     */
    public static InputStream getInStream() {
        return inStream;
    }

    /**
     * @param aInStream the inStream to set
     */
    public static void setInStream(InputStream aInStream) {
        inStream = aInStream;
    }

    /**
     * @return the outStream
     */
    public static OutputStream getOutStream() {
        return outStream;
    }

    /**
     * @param aOutStream the outStream to set
     */
    public static void setOutStream(OutputStream aOutStream) {
        outStream = aOutStream;
    }

    /**
     * @return the buffer_string
     */
    public static String getBuffer_string() {
        return buffer_serialData;
    }

    /**
     * @param aBuffer_string the buffer_string to set
     */
    public static void setBuffer_string(String aBuffer_string) {
        buffer_serialData = aBuffer_string;
    }
}
