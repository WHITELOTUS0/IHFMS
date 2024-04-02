import ihfms.factories.SimpleMessageFactory;
import ihfms.messages.EmailMessage;
import ihfms.messages.IMessage;
import ihfms.messages.SMSMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMessageFactoryTest {
    private SimpleMessageFactory messageFactory = new SimpleMessageFactory();

    @Test
    public void testCreateEmailMessage() {
        // Provide the necessary parameters for creating an EmailMessage
        IMessage message = messageFactory.createMessage(
                "Email",
                "from@example.com",
                "to@example.com",
                "Subject",
                "Email content"
        );
        Assertions.assertTrue(message instanceof EmailMessage);
    }

    @Test
    public void testCreateSMSMessage() {
        // Provide the necessary parameters for creating an SMSMessage
        IMessage message = messageFactory.createMessage(
                "SMS",
                "fromPhoneNumber",
                "toPhoneNumber",
                "", // Subject is not used for SMS
                "SMS content"
        );
        Assertions.assertTrue(message instanceof SMSMessage);
    }

    @Test
    public void testCreateUnknownMessage() {
        // Attempt to create a message with an unknown type
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            IMessage message = messageFactory.createMessage(
                    "Unknown",
                    "from@example.com",
                    "to@example.com",
                    "Subject",
                    "Message content"
            );
        });

        String expectedMessage = "Unknown message type: Unknown";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
//import ihfms.factories.SimpleMessageFactory;
//import ihfms.messages.EmailMessage;
//import ihfms.messages.IMessage;
//import ihfms.messages.SMSMessage;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class SimpleMessageFactoryTest {
//    private SimpleMessageFactory messageFactory = new SimpleMessageFactory();
//
//    @Test
//    public void testCreateEmailMessage() {
//        IMessage message = messageFactory.createMessage("Email");
//        Assertions.assertTrue(message instanceof EmailMessage);
//    }
//
//    @Test
//    public void testCreateSMSMessage() {
//        IMessage message = messageFactory.createMessage("SMS");
//        Assertions.assertTrue(message instanceof SMSMessage);
//    }
//
//    @Test
//    public void testCreateUnknownMessage() {
//        IMessage message = messageFactory.createMessage("Unknown");
//        Assertions.assertNull(message);
//    }
//}