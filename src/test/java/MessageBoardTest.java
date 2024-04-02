import ihfms.messages.IMessage;
import ihfms.observers.MessageBoard;
import ihfms.observers.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageBoardTest {
    private MessageBoard messageBoard;
    private Observer observer;
    private IMessage message;

    @BeforeEach
    public void setUp() {
        messageBoard = new MessageBoard();
        observer = Mockito.mock(Observer.class);
        message = Mockito.mock(IMessage.class);
        messageBoard.registerObserver(observer);
    }

    @Test
    public void testNotifyObservers() {
        messageBoard.postNewMessage(message);
        verify(observer, times(1)).update(message);
    }
}