package base;

public interface DatabaseService extends Abonent {
    long getUserId(String name);
    MessageSystem getMessageSystem();
}
