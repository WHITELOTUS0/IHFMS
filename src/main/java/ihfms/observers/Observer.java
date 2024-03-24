package ihfms.observers;

import ihfms.messages.Message;

public interface Observer {
    void update(Message message);
}
