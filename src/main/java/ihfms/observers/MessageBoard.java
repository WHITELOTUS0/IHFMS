package ihfms.observers;

import ihfms.messages.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageBoard implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private Message message;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void postNewMessage(Message message) {
        this.message = message;
        notifyObservers();
    }
}
