package com.up.socketservice.model.hailing.handle;

import com.up.socketservice.model.hailing.CommonPackage;

public class HandlePackageContext {
    private HandlePackage handlePackage;

    public HandlePackageContext(){
        this.handlePackage = new InitPackage(this);
    }

    public void changeState(HandlePackage newState) {
        this.handlePackage = newState;
    }

    public void handle(CommonPackage commonPackage){
        handlePackage.handle(commonPackage);
    }

    public String getState(){
        return handlePackage.getState();
    }
}
