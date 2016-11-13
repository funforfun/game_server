package resource;

public class ResourceFactory {
    private static ResourceFactory instance;

    private ResourceFactory() {

    }

    public static ResourceFactory getInstance() {
        if (instance == null) {
            instance = new ResourceFactory();
        }
        return instance;
    }

    public Resource getResource(String path) {

        // TODO: включить сюда VFS для проверки наличия файла и возможности его чтения

        return null;
    }
}
