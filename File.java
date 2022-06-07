public class File {
    private String fileName;
    private String allocationStart;
    private String allocationSize;
    private String filePath;

    public File() {

    }

    public File(String name, String path, String start, String size) {
        fileName = name;
        filePath = path;
        allocationStart = start;
        allocationSize = size;
    }

    public String getName() {
        return fileName;
    }

    public String getStart() {
        return allocationStart;
    }

    public String getSize() {
        return allocationSize;
    }

    public String getFilePath() {
        return filePath;
    }

    {

    }
}
