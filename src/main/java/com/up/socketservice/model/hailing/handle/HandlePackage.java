package com.up.socketservice.model.hailing.handle;

import com.up.socketservice.model.hailing.CommonPackage;

public abstract class HandlePackage {
    HandlePackageContext handlePackageContext;

    HandlePackage(HandlePackageContext handlePackageContext){
        this.handlePackageContext = handlePackageContext;
    }

    public abstract void handle(CommonPackage commonPackage);
    public abstract String getState();
}
