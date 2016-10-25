package accountService;

import base.Abonent;
import base.AccountService;
import base.Address;
import messageSystem.Message;

public abstract class MessageToAccountService extends Message {

    public MessageToAccountService(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) throws RuntimeException {
        if (abonent instanceof AccountService) {
            exec((AccountService) abonent);
        } else {
            throw new RuntimeException("Wrong type of abonent!" + abonent.getClass());
        }
    }

    public abstract void exec(AccountService accountService);
}
