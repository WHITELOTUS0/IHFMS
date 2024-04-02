package ihfms.messages;

import ihfms.util.ConfigLoader;

import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String sendinblueApiKey = ConfigLoader.getProperty("SENDINBLUE_API_KEY");
        ApiClient apiClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) apiClient.getAuthentication("api-key");
        apiKey.setApiKey(sendinblueApiKey);

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();

        sendSmtpEmail.setSender(new SendSmtpEmailSender().email(this.fromEmail));
        sendSmtpEmail.setTo(Collections.singletonList(new SendSmtpEmailTo().email(this.toEmail)));
        sendSmtpEmail.setSubject(this.subject);
        sendSmtpEmail.setHtmlContent(this.content);

        try {
            apiInstance.sendTransacEmail(sendSmtpEmail);
            LOGGER.log(Level.INFO, "Email sent successfully to {0}", this.toEmail);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to send email: {0}", e.getMessage());
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
