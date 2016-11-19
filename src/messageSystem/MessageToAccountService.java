package messageSystem;

import base.Abonent;
import base.DatabaseService;
import base.Address;
import base.Message;

public abstract class MessageToAccountService extends Message {

    public MessageToAccountService(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) {
        if (abonent instanceof DatabaseService) {
            exec((DatabaseService) abonent);
        } else {
            throw new RuntimeException("Wrong type of abonent!" + abonent.getClass());
        }
    }

    public abstract void exec(DatabaseService databaseService);
}
