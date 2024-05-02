package com.rodrigohacks.atm;

import com.rodrigohacks.atm.model.ATMPresentationLayer;

/**
 * The main entry point of the ATM System Presentation.
 * This class initializes the presentation layer and starts the application.
 *
 * @author Rodrigo Hacks
 * @version 1.0
 */
public class ATMSystemPresentation {

    /**
     * The current user ID being used in the system.
     */
    private static int currentUserId;

    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        ATMPresentationLayer presentationLayer = new ATMPresentationLayer();
        presentationLayer.startApplication();
    }
}
