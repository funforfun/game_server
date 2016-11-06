package base;

import java.util.concurrent.atomic.AtomicInteger;

public class Address {
    static private AtomicInteger abonentIdCreator = new AtomicInteger();
    final private int abonentId;

    public Address() {
        System.out.println(this.getClass().toString() + ": Address");
        this.abonentId = abonentIdCreator.incrementAndGet();
    }

    public int hashCode() {
        System.out.println(this.getClass().toString() + ": hashCode");
        return abonentId;
    }

    public int getAbonentId() {
        System.out.println(this.getClass().toString() + ": getAbonentId");
        return abonentId;
    }
}
