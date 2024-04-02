package ihfms.messages;

public interface IMessage {
    void send();
    String getContent();
    void setContent(String content);
}