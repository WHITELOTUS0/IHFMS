package ihfms.factories;

import ihfms.messages.EmailMessage;
import ihfms.messages.SMSMessage;
import ihfms.messages.Message;

public class SimpleMessageFactory implements MessageFactory {
    @Override
    public Message createMessage(String type) {
        switch (type) {
            case "SMS":
                return new SMSMessage();
            case "Email":
                return new EmailMessage();
            default:
                throw new IllegalArgumentException("Unknown message type: " + type);
        }
    }
}