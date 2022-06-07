import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String directoryName;
    private String directoryPath;
    private List<File> files;
    private List<Directory> directories;

    public Directory(String name, String path) {
        directoryName = name;
        directoryPath = path;
        files = new ArrayList<File>();
        directories = new ArrayList<Directory>();
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addDirectory(Directory dir) {
        directories.add(dir);
    }

    public List<Directory> getDirs() {
        return directories;
    }

    public List<File> getFiles() {
        return files;
    }

}
