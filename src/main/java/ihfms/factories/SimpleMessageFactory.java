package ihfms.factories;

import ihfms.messages.EmailMessage;
import ihfms.messages.SMSMessage;
import ihfms.messages.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleMessageFactory implements MessageFactory {
    private static final Logger LOGGER = Logger.getLogger(SimpleMessageFactory.class.getName());

    @Override
    public Message createMessage(String type) {
        switch (type) {
            case "SMS":
                return new SMSMessage();
            case "Email":
                return new EmailMessage();
            default:
                LOGGER.log(Level.WARNING, "Unknown message type: {0}", type);
                return null; // Consider returning a default implementation or null object pattern
        }
    }
}
//package ihfms.factories;
//
//import ihfms.messages.EmailMessage;
//import ihfms.messages.SMSMessage;
//import ihfms.messages.Message;
//
//public class SimpleMessageFactory implements MessageFactory {
//    @Override
//    public Message createMessage(String type) {
//        switch (type) {
//            case "SMS":
//                return new SMSMessage();
//            case "Email":
//                return new EmailMessage();
//            default:
//                throw new IllegalArgumentException("Unknown message type: " + type);
//        }
//    }
//}