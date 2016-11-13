package resource;

import java.util.HashMap;
import java.util.Map;

public class ResourcesMap {
    private static Map<Class, Resource> resourcesMap = new HashMap<Class, Resource>();

    public static void put(Resource resource) {
        resourcesMap.put(resource.getClass(), resource);
    }

    public static Resource get(Class clazz) {
        return resourcesMap.get(clazz);
    }

    public static boolean has(Class clazz) {
        return resourcesMap.containsKey(clazz);
    }

    public static Map<Class, Resource> getResourcesMap() {
        return resourcesMap;
    }

}
