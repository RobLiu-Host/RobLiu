package com.dbmovie.base.mvvm;



public class ComponentHolder {

    private static AppComponent mComponent;

    public static AppComponent getComponent() {
        return mComponent;
    }

    public static void setComponent(AppComponent component) {
        mComponent = component;
    }
}
