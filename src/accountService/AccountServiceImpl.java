package accountService;

import base.AccountService;
import base.Address;
import base.MessageSystem;
import utils.ThreadSleepHelper;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService, Runnable {
    private Address address;
    private final MessageSystem messageSystem;
    private Map<String, Integer> fakeAccounter = new HashMap<String, Integer>();

    public AccountServiceImpl(MessageSystem messageSystem) {
        System.out.println(this.getClass().toString() + ": AccountServiceImpl");
        this.messageSystem = messageSystem;
        this.address = new Address();
        messageSystem.addService(this);
        this.fakeAccounter.put("Tully", 1);
        this.fakeAccounter.put("Sully", 2);
    }

    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            ThreadSleepHelper.sleep(10);
        }

    }

    public Integer getUserId(String name) {
        System.out.println(this.getClass().toString() + ": getUserId");
        ThreadSleepHelper.sleep(5000);
        Integer user_id = fakeAccounter.get(name);
        return (user_id == null) ? -1 : user_id;
    }

    public MessageSystem getMessageSystem() {
        System.out.println(this.getClass().toString() + ": getMessageSystem");
        return messageSystem;
    }

    public Address getAddress() {
        System.out.println(this.getClass().toString() + ": getMessageSystem");
        return address;
    }
}
