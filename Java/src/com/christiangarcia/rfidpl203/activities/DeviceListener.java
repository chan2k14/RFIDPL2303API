package com.christiangarcia.rfidpl203.activities;

import com.christiangarcia.rfidpl203.utils.DeviceConfiguration;
import com.christiangarcia.rfidpl203.utils.Utilities;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Christian Garcia | Email: chan.garcia100@gmail.com
 */
public class DeviceListener {
    
    public DeviceConnector devPortLoc = new DeviceConnector();
    OutputStream out;
    public Utilities utils;
    
    public DeviceListener() {
        //Default Constructor
    }
    
    public String readSensorData(){
        try {
            Thread.sleep(DeviceConfiguration.getTimeout());
        } catch (InterruptedException ex) {
            return DeviceStatus.BUSY.tag();
        }
        
        if(DataValues.getBuffer_string() == null) {
            return "Serial Reader Timeout";
        } else {
            return (DataValues.getBuffer_string()).trim();
        }
    }
    
    public static class SerialReaderEvent implements SerialPortEventListener {
        private final byte[] buffer = new byte[1024];
        
        @Override
        public void serialEvent(SerialPortEvent event) {
            int data;
            switch (event.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    try {
                        int len = 0;
                        while((data = DataValues.getInStream().read()) > -1) {
                            if(data == '\n') {
                                break;
                            }
                            buffer[len++] = (byte) data;
                        }
                        //System.out.println(new String(buffer,0,len).replaceAll("[]", ""));
                        DataValues.setBuffer_string(new String(buffer, 0, len).replaceAll("[]", ""));
                    } catch(IOException ex) {
                        System.out.println("Data not successfully read");
                    }
                    break;
                //In case if the rfid chip has no data
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                    DataValues.setBuffer_string(null);
                    break;
            }
        }
    }
    
    public String terminateDevice() {
        try {
            if(DataValues.getInStream() != null && DataValues.getSerialPort() != null) {
                DataValues.getInStream().close();
                DataValues.getSerialPort().close();
                return "Device Successfully Terminated";
            } else {
                return "Illegal Command Execution";
            }   
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
}
