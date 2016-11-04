package frontend;

import base.AccountService;
import base.Address;
import frontend.MessageUpdateUserId;
import messageSystem.MessageToAccountService;

public class MessageGetUserId extends MessageToAccountService {
    private String name;


    public MessageGetUserId(Address from, Address to, String name) {
        super(from, to);
        this.name = name;
    }

    public void exec(AccountService accountService) {
        Integer id = accountService.getUserId(name);
        accountService.getMessageSystem().sendMessage(new MessageUpdateUserId(getTo(),getFrom(), name, id));
    }
}
