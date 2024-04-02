package ihfms.factories;

import ihfms.messages.IMessage;

public interface MessageFactory {
    IMessage createMessage(String type, String from, String to, String subject, String content);
}