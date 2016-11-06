package messageSystem;

import base.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageSystemImpl implements MessageSystem {

    private Map<Address, ConcurrentLinkedQueue<Message>> messages = new HashMap<Address, ConcurrentLinkedQueue<Message>>();
    private AddressServiceImpl addressService = new AddressServiceImpl();

    public void addService(Abonent abonent) {
        System.out.println(this.getClass().toString() + ": addService");
        addressService.setAddress(abonent);
        messages.put(abonent.getAddress(), new ConcurrentLinkedQueue<Message>());
    }

    public void sendMessage(Message message) {
        System.out.println(this.getClass().toString() + ": sendMessage");
        Queue<Message> messageQueue = messages.get(message.getTo());
        messageQueue.add(message);
    }

    public void execForAbonent(Abonent abonent) {
        System.out.println(this.getClass().toString() + ": execForAbonent");
        Queue<Message> messageQueue = messages.get(abonent.getAddress());

        if (messageQueue == null) {
            return;
        }

        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.poll();
            try {
                message.exec(abonent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public AddressService getAddressService(){
        System.out.println(this.getClass().toString() + ": getAddressService");
        return addressService;
    }
}
