package ihfms.observers;

import ihfms.messages.IMessage;

public interface Observer {
    void update(IMessage message);
}
