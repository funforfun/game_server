package frontend;

import base.AccountService;
import base.Address;
import frontend.MessageUpdateUserId;
import messageSystem.MessageToAccountService;

public class MessageGetUserId extends MessageToAccountService {
    private String name;


    public MessageGetUserId(Address from, Address to, String name) {
        super(from, to);
        System.out.println(this.getClass().toString() + ": MessageGetUserId");
        this.name = name;
    }

    public void exec(AccountService accountService) {
        System.out.println(this.getClass().toString() + ": exec");
        Integer id = accountService.getUserId(name);
        accountService.getMessageSystem().sendMessage(new MessageUpdateUserId(getTo(),getFrom(), name, id));
    }
}
