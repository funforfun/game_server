package frontend;

import base.Abonent;
import base.Address;
import base.Frontend;
import messageSystem.Message;

public abstract class MessageToFrontend extends Message {

    public MessageToFrontend(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent abonent) {
        if (abonent instanceof Frontend) {
            exec((Frontend) abonent);
        } else {
            throw new RuntimeException("Wrong type of abonent!" + abonent.getClass());
        }
    }

    public abstract void exec(Frontend frontend);
}
