package com.up.socketservice.model;

import java.util.ArrayList;

public class CommonPackage {
    public String idHailing;
    public String idDriver;
    public String idClient;
    public Hailing hailing;
    public String status;
    public ArrayList<String> scope;

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
