package ihfms.messages;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMSMessage implements IMessage {
    private static final Logger LOGGER = Logger.getLogger(SMSMessage.class.getName());
    private String toPhoneNumber;
    private String fromPhoneNumber;
    private String content;

    // Twilio Account SID and Auth Token should be stored in environment variables or a config file
    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public SMSMessage(String fromPhoneNumber, String toPhoneNumber, String content) {
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.content = content;
    }

    @Override
    public void send() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            Message message = Message.creator(
                    new PhoneNumber(this.toPhoneNumber),
                    new PhoneNumber(this.fromPhoneNumber),
                    this.content
            ).create();
            LOGGER.log(Level.INFO, "SMS sent with SID: {0}", message.getSid());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to send SMS: {0}", e.getMessage());
        }
    }

    // Getters and setters
    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }

    public String getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public void setFromPhoneNumber(String fromPhoneNumber) {
        this.fromPhoneNumber = fromPhoneNumber;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
//package ihfms.messages;
//
//public class SMSMessage implements Message {
//    private String content;
//    @Override
//    public void send() {
//        // SMS sending logic
//    }
//
//    @Override
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public String getContent() {
//        return this.content;
//    }
//}
