package com.markyhzhang.moreofless;

public class AppAction {

    private String nameString, packageString;

    public AppAction(String nameString, String packageString){
        this.nameString = nameString;
        this.packageString = packageString;
    }

    public String getPackageString() {
        return packageString;
    }

    public String getNameString() {
        return nameString;
    }


}
