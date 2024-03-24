package ihfms.messages;

public class SMSMessage implements Message {
    private String content;
    @Override
    public void send() {
        // SMS sending logic
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
