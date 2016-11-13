package vfs;

import java.io.File;
import java.util.Iterator;

public class VFSImpl implements VFS {

    private String root;

    public VFSImpl(String root) {
        this.root = root;
    }

    public boolean isExist(String path) {
        return false;
    }

    public boolean isDirectory(String path) {
        return new File(root + path).isDirectory();
    }

    public String getAbsolutePath(String file) {
        return new File(root + file).getAbsolutePath();
    }

    public Iterator<String> getIterator(String startDir) {
        return new FileIterator(root, startDir);
    }

    public byte[] getBytes(String file) {
        return new byte[0];
    }

    public String getUTF8Text(String file) {
        return null;
    }
}
