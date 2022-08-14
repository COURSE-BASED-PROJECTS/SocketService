package com.up.socketservice.model.hailing;

import com.up.socketservice.model.Hailing;
import com.up.socketservice.model.hailing.handle.HandlePackageContext;

import java.util.ArrayList;

public class CommonPackage {
    private String idHailing;
    private String idDriver;
    private String idClient;
    private HailingPackage hailing;
    private String status;
    private ArrayList<String> scope;


    public String getIdHailing() {
        return idHailing;
    }

    public void setIdHailing(String idHailing) {
        this.idHailing = idHailing;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public HailingPackage getHailing() {
        return hailing;
    }

    public void setHailing(HailingPackage hailing) {
        this.hailing = hailing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getScope() {
        return scope;
    }

    public void setScope(ArrayList<String> scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "CommonPackage{" +
                "idHailing='" + idHailing + '\'' +
                ", idDriver='" + idDriver + '\'' +
                ", idClient='" + idClient + '\'' +
                ", hailing=" + hailing +
                ", status='" + status + '\'' +
                ", scope=" + scope +
                '}';
    }
}
