import ihfms.factories.SimpleMessageFactory;
import ihfms.messages.EmailMessage;
import ihfms.messages.Message;
import ihfms.messages.SMSMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMessageFactoryTest {
    private SimpleMessageFactory messageFactory = new SimpleMessageFactory();

    @Test
    public void testCreateEmailMessage() {
        Message message = messageFactory.createMessage("Email");
        Assertions.assertTrue(message instanceof EmailMessage);
    }

    @Test
    public void testCreateSMSMessage() {
        Message message = messageFactory.createMessage("SMS");
        Assertions.assertTrue(message instanceof SMSMessage);
    }

    @Test
    public void testCreateUnknownMessage() {
        Message message = messageFactory.createMessage("Unknown");
        Assertions.assertNull(message);
    }
}