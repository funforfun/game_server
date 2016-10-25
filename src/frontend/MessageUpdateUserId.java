package frontend;

import base.Address;
import base.Frontend;

public class MessageUpdateUserId extends MessageToFrontend {
    private String name;
    private Integer id;

    public MessageUpdateUserId(Address from, Address to, String name, Integer id) {
        super(from, to);
        this.name = name;
        this.id = id;
    }

    public void exec(Frontend frontend) {
        frontend.setId(name, id);
    }
}
