package ihfms.messages;

import ihfms.util.ConfigLoader;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.messages.TextMessage;
import com.vonage.client.sms.SmsSubmissionResponseMessage;
import com.vonage.client.sms.SmsSubmissionResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMSMessage implements IMessage {
    private static final Logger LOGGER = Logger.getLogger(SMSMessage.class.getName());
    private String toPhoneNumber;
    private String fromPhoneNumber;
    private String content;

    // Vonage API key and secret should be stored in environment variables or a config file
    private static final String VONAGE_API_KEY = ConfigLoader.getProperty("VONAGE_API_KEY");
    private static final String VONAGE_API_SECRET = ConfigLoader.getProperty("VONAGE_API_SECRET");

    public SMSMessage(String fromPhoneNumber, String toPhoneNumber, String content) {
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.content = content;
    }

    @Override
    public void send() {
        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        TextMessage message = new TextMessage(fromPhoneNumber, toPhoneNumber, content);

        try {
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
            for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                if (responseMessage.getStatus() == MessageStatus.OK) {
                    LOGGER.log(Level.INFO, "SMS sent successfully with ID: {0}", responseMessage);
                } else {
                    LOGGER.log(Level.SEVERE, "SMS failed with error: {0}", responseMessage.getErrorText());
                }
            }
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
