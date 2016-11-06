package frontend;

import base.Address;
import base.Frontend;
import messageSystem.MessageToFrontend;

public class MessageUpdateUserId extends MessageToFrontend {
    private String name;
    private Integer id;

    public MessageUpdateUserId(Address from, Address to, String name, Integer id) {
        super(from, to);
        System.out.println(this.getClass().toString() + ": MessageUpdateUserId");
        this.name = name;
        this.id = id;
    }

    public void exec(Frontend frontend) {
        System.out.println(this.getClass().toString() + ": exec");
        System.out.print("Address of Frontend: " + frontend.getAddress().getAbonentId() + '\n');
        frontend.setId(name, id);
    }
}
