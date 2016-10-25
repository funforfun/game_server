package messageSystem;

import base.Abonent;
import base.Address;

public abstract class Message {
    private Address from;
    private Address to;

    public Message(Address from, Address to) {
        this.from = from;
        this.to = to;
    }

    public Address getFrom() {
        return from;
    }

    public Address getTo() {
        return to;
    }

    public abstract void exec(Abonent abonent) throws RuntimeException;
}
