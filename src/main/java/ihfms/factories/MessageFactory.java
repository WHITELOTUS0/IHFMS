package ihfms.factories;

public interface MessageFactory {
    Message createMessage(String type);
}