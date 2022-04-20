package com.company.posrednik;

public class Proxy implements IProxy {
    public Officer officer;

    @Override
    public void readData() {
        if (officer == null) {
            officer = new Officer();
        }
        officer.readData();
    }
}
