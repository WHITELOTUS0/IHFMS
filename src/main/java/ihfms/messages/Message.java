package ihfms.messages;

public interface Message {
    void send();
    String getContent();
    void setContent(String content);
}