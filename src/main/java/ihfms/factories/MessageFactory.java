package ihfms.factories;
import ihfms.messages.Message;

public interface MessageFactory {
    Message createMessage(String type);
}