/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.americas.bean;

/**
 *
 * @author omaalvca
 */
public class BeanRequerimiento {
    protected int Requerimiento;
    protected boolean AutorizaEnvioInfo;

    public int getRequerimiento() {
        return Requerimiento;
    }

    public void setRequerimiento(int Requerimiento) {
        this.Requerimiento = Requerimiento;
    }

    public boolean isAutorizaEnvioInfo() {
        return AutorizaEnvioInfo;
    }

    public void setAutorizaEnvioInfo(boolean AutorizaEnvioInfo) {
        this.AutorizaEnvioInfo = AutorizaEnvioInfo;
    }
    
}
