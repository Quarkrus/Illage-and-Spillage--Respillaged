package com.yellowbrossproductions.illageandspillage.capability;

public class WebbedCapability implements IWebbed {
    private boolean webbed;

    @Override
    public boolean isWebbed() {
        return webbed;
    }

    @Override
    public void setWebbed(boolean webbed) {
        this.webbed = webbed;
    }
}
