package ihfms.messages;

import com.sendgrid.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;

public class EmailMessage implements IMessage {
    private static final Logger LOGGER = Logger.getLogger(EmailMessage.class.getName());
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String content;

    public EmailMessage(String fromEmail, String toEmail, String subject, String content) {
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public void send() {
        Email from = new Email(this.fromEmail);
        Email to = new Email(this.toEmail);
        Content content = new Content("text/plain", this.content);
        Mail mail = new Mail(from, this.subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY")); // Retrieve API key from environment
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            LOGGER.log(Level.INFO, "Email sent with status code: {0}", response.getStatusCode());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to send email: {0}", ex.getMessage());
        }
    }

    // Getters and setters
    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
//public class EmailMessage implements Message {
//    private String content;
//    @Override
//    public void send() {
//        // Email sending logic
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
