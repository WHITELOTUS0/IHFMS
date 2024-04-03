package org.example;

import ihfms.adapters.BillingAdapter;
import ihfms.adapters.BillingSystemAdapter;
import ihfms.commands.ProcessInvoiceCommand;
import ihfms.factories.SimpleMessageFactory;
import ihfms.messages.IMessage;
import ihfms.model.Invoice;
import ihfms.model.Patient;
import ihfms.observers.MessageBoard;
import ihfms.observers.Observer;
import ihfms.external.ExternalBillingSystem;
import ihfms.ui.MainApp;


public class Main {
    public static void main(String[] args) {
//        System.out.println("Integrated Health Finance Management System is starting...");
//
//        // Example external system that our adapter will communicate with
//        ExternalBillingSystem externalBillingSystem = new ExternalBillingSystem();
//
//        // Initialize the adapter with the external billing system
//        BillingSystemAdapter billingAdapter = new BillingAdapter(externalBillingSystem);
//
//        // Initialize the message factory
//        SimpleMessageFactory messageFactory = new SimpleMessageFactory();
//
//        // Initialize the message board (subject in the observer pattern)
//        MessageBoard messageBoard = new MessageBoard();
//
//        // Example observer that will listen for messages
//        Observer messageObserver = new Observer() {
//            @Override
//            public void update(IMessage message) {
//                System.out.println("New message received: " + message.getContent());
//            }
//        };
//
//        // Register the observer with the message board
//        messageBoard.registerObserver(messageObserver);
//
//        // Create a new patient and invoice for demonstration purposes
//        Patient patient = new Patient();
//        patient.setPatientID("12345");
//        patient.setFirstName("John");
//        patient.setLastName("Doe");
//
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceID("INV-12345");
//        invoice.setPatientID(patient.getPatientID());
//        invoice.setAmount(100.0);
//
//        // Process the invoice using the command pattern
//        ProcessInvoiceCommand processInvoiceCommand = new ProcessInvoiceCommand(invoice, billingAdapter);
//        processInvoiceCommand.execute();
//
//        // Create and send an email message using the factory
//        IMessage emailMessage = messageFactory.createMessage(
//                "Email",
//                "ihfms@system.com",
//                "glorrysibomana758@gmail.com",
//                "Invoice Processed",
//                "Invoice processed for patient: " + patient.getFirstName() + " " + patient.getLastName()
//        );
//        emailMessage.send(); // Send the email
//
//        // Create and send an SMS message using the factory
//        IMessage smsMessage = messageFactory.createMessage(
//                "SMS",
//                "ihfms",
//                "25675841682",
//                "", // Subject is not used for SMS
//                "Invoice processed for patient: " + patient.getFirstName() + " " + patient.getLastName()
//        );
//        smsMessage.send(); // Send the SMS
//
//        System.out.println("Integrated Health Finance Management System is running.");
        MainApp.main(args);
    }
}
//package org.example;
//
//import ihfms.adapters.BillingAdapter;
//import ihfms.adapters.BillingSystemAdapter;
//import ihfms.commands.ProcessInvoiceCommand;
//import ihfms.factories.MessageFactory;
//import ihfms.factories.SimpleMessageFactory;
//import ihfms.messages.IMessage;
//import ihfms.model.Invoice;
//import ihfms.model.Patient;
//import ihfms.observers.MessageBoard;
//import ihfms.observers.Observer;
//import ihfms.external.ExternalBillingSystem;
//
//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Integrated Health Finance Management System is starting...");
//
//        // Example external system that our adapter will communicate with
//        ExternalBillingSystem externalBillingSystem = new ExternalBillingSystem();
//
//        // Initialize the adapter with the external billing system
//        BillingSystemAdapter billingAdapter = new BillingAdapter(externalBillingSystem);
//
//        // Initialize the message factory
//        MessageFactory messageFactory = new SimpleMessageFactory();
//
//        // Initialize the message board (subject in the observer pattern)
//        MessageBoard messageBoard = new MessageBoard();
//
//        // Example observer that will listen for messages
//        Observer messageObserver = new Observer() {
//            @Override
//            public void update(IMessage message) {
//                System.out.println("New message received: " + message.getContent());
//            }
//        };
//
//        // Register the observer with the message board
//        messageBoard.registerObserver(messageObserver);
//
//        // Create a new patient and invoice for demonstration purposes
//        Patient patient = new Patient();
//        patient.setPatientID("12345");
//        patient.setFirstName("John");
//        patient.setLastName("Doe");
//
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceID("INV-12345");
//        invoice.setPatientID(patient.getPatientID());
//        invoice.setAmount(100.0);
//
//        // Process the invoice using the command pattern
//        ProcessInvoiceCommand processInvoiceCommand = new ProcessInvoiceCommand(invoice, billingAdapter);
//        processInvoiceCommand.execute();
//
//        // Create and send a message using the factory and observer patterns
//
//        IMessage message = messageFactory.createMessage(
//                "Email",
//                "test@example.com",
//                "glorrysibomana758@gmail.com",
//                "Invoice Processed",
//                "Invoice processed for patient: " + patient.getFirstName() + " " + patient.getLastName()
//        );
//        message.send(); // Send the email
//
//        System.out.println("Integrated Health Finance Management System is running.");
//    }
//}