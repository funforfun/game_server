package base;

public interface DatabaseService extends Abonent {
    Integer getUserId(String name);
    MessageSystem getMessageSystem();
}
