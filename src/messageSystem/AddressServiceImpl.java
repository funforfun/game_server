package messageSystem;


import base.Abonent;
import base.Address;
import base.AddressService;

import java.util.HashMap;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    private Map<Class<?>, Address> addresses = new HashMap<Class<?>, Address>();

    public Address getAddress(Class<?> abonentClass) {
        return addresses.get(abonentClass);
    }

    public void setAddress(Abonent abonent) {
        addresses.put(abonent.getClass(), abonent.getAddress());
    }
}
