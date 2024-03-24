package ihfms.messages;

public class EmailMessage implements Message {
    private String content;
    @Override
    public void send() {
        // Email sending logic
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
