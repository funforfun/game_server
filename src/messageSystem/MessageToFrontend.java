package messageSystem;

import base.Abonent;
import base.Address;
import base.Frontend;
import base.Message;

public abstract class MessageToFrontend extends Message {

    public MessageToFrontend(Address from, Address to) {
        super(from, to);
        System.out.println(this.getClass().toString() + ": MessageToFrontend");
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
