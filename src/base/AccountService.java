package base;

public interface AccountService extends Abonent {
    Integer getUserId(String name);
    MessageSystem getMessageSystem();
}
