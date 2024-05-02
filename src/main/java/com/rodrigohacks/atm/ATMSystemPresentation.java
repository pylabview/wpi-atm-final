package com.rodrigohacks.atm;

import com.rodrigohacks.atm.model.ATMPresentationLayer;

public class ATMSystemPresentation {
    private static int currentUserId;

    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        ATMPresentationLayer presentationLayer = new ATMPresentationLayer();
        presentationLayer.startApplication();
    }
}
