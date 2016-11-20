package frontend;

import base.DatabaseService;
import base.Address;
import messageSystem.MessageToAccountService;

public class MessageGetUserId extends MessageToAccountService {
    private String name;


    public MessageGetUserId(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    public void exec(DatabaseService databaseService) {
        long id = databaseService.getUserId(name);
        databaseService.getMessageSystem().sendMessage(new MessageUpdateUserId(getTo(),getFrom(), name, id));
    }
}
