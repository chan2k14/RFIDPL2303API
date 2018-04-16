/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christiangarcia.rfidpl203.activities;

/**
 *
 * @author Christian Garcia | Email: chan.garcia100@gmail.com
 */
public enum DeviceStatus {
    ACTIVE("Status:  Device is Ready"),
    INACTIVE("Status:  Device Not Found"),
    BUSY("Status:  Device is Busy"),
    UNSUPPORTED("Status:  Unsupported Device"),
    OK("Status:  OK");
    
    private String result;
    
    DeviceStatus(String result) {
        this.result = result;
    }
    
    public String tag() {
        return result;
    }
}
