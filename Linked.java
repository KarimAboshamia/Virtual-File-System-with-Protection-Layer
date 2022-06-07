import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.BufferedReader;
import java.util.Set;

import static java.lang.Math.abs;

public class Linked {
    private String localDir;
    private String filePath;
    private BufferedReader file;
    private List<String> fileData;
    private String allocationString;
    private int totalAllocated;
    private int freeSpace;
    static private Directory mainDir;
    private int endAllocationString;
    private File fileSearched;
    LinkedSpaceManager checkSpace;

    public Linked(String dir, String path, BufferedReader f, List<String> data) throws FileNotFoundException {
        localDir = dir;
        filePath = path;
        file = f;
        fileData = data;
        allocationString = "";
        fileSearched = new File();
        if (fileData.size() >= 1) {
            updateStructure();
        }
        checkSpace = new LinkedSpaceManager(fileData, endAllocationString, allocationString);
    }

    public Directory getMainDir() {
        return mainDir;
    }

    //Done
    public void updateStructure() {
        //filedata line by line
        int i = 2;
        allocationString = "";
        while (!(fileData.get(i).equals("end"))) {
            allocationString += fileData.get(i);
            i++;
        }
        endAllocationString = i - 1;
        i++;
        totalAllocated = Integer.parseInt(fileData.get(i));
        i++;
        freeSpace = Integer.parseInt(fileData.get(i));
        i++;
        mainDir = new Directory("root", "root/");
        for (; i < fileData.size(); i++) {
            String[] words = fileData.get(i).split("\\W+");
            ///check root here
            if (words[0].equals("root")) {
                int counter = 1;
                List<Directory> temp = mainDir.getDirs();
                Directory currentDir = mainDir;
                //Create File and add it to the right directory.
                if (words.length >= 5 && words[words.length - 3].equals("txt")) {
                    while (true) {
                        int index = -1;
                        for (int j = 0; j < temp.size(); j++) {
                            if (temp.get(j).getDirectoryName().equals(words[counter])) {
                                index = j;
                                break;
                            }
                        }

                        if (index == -1 && !(words[counter + 1].equals("txt"))) {
                            Directory tempDir = new Directory(words[counter], currentDir.getDirectoryPath() + words[counter] + "/");
                            temp.add(tempDir);
                            currentDir = tempDir;
                            temp = currentDir.getDirs();
                            counter++;

                        } else if (!(words[counter + 1].equals("txt"))) {
                            currentDir = temp.get(index);
                            temp = currentDir.getDirs();
                            counter++;
                        } else if (words[counter + 1].equals("txt")) {
                            File file = new File(words[counter], currentDir.getDirectoryPath() + words[counter] + ".txt", words[counter + 2], words[counter + 3]);
                            currentDir.getFiles().add(file);
                            break;
                        }

                        if (counter == words.length) {
                            break;
                        }
                    }
                } else if (words.length < 5) {
                    while (true) {
                        int index = -1;
                        for (int j = 0; j < temp.size(); j++) {
                            if (temp.get(j).getDirectoryName().equals(words[counter])) {
                                index = j;
                                break;
                            }
                        }

                        if (index == -1) {
                            Directory tempDir = new Directory(words[counter], currentDir.getDirectoryPath() + words[counter] + "/");
                            temp.add(tempDir);
                            currentDir = tempDir;
                            temp = currentDir.getDirs();
                            counter++;

                        } else {
                            currentDir = temp.get(index);
                            temp = currentDir.getDirs();
                            counter++;
                        }
                        if (counter == words.length) {
                            break;
                        }
                    }
                }
            }
        }
    }

    //Done
    public void createFolder(String root) throws IOException {
        if (!(checkPath(root, 0))) {
            fileData.add(root);
            updateDisk();
            updateStructure();
        } else {
            //System.out.println("This folder already exists.");
        }
    }

    public boolean checkPath(String root, int fl) {
        String[] splittedRoot = root.split("\\W+");
        Directory tmpDir = mainDir;
        int flag = 0;
        if (!(splittedRoot[0].equals("root")) && fl == 1) {
            System.out.println("Wrong Path");
            return false;
        } else if (!(splittedRoot[0].equals("root")) && fl == 0) {
            System.out.println("Wrong Path");
            return true;
        }

        int counter = 1;
        int found = 0;
        //Case path has file
        if (splittedRoot[splittedRoot.length - 1].equals("txt")) {
            //check folders after root to the folder before the file -> if folder doesn't exist then invalid path
            for (; counter < splittedRoot.length - 2; counter++) {
                for (int i = 0; i < tmpDir.getDirs().size(); i++) {
                    if (tmpDir.getDirs().get(i).getDirectoryName().equals(splittedRoot[counter])) {
                        found = 1;
                        tmpDir = tmpDir.getDirs().get(i);
                    }
                }
            }


            //New Edit
            if (splittedRoot.length == 3)
                found = 1;


            //check file -> if file with same name exist then invalid path
            for (int i = 0; i < tmpDir.getFiles().size(); i++) {
                if (tmpDir.getFiles().get(i).getName().equals(splittedRoot[counter]) && fl == 0) {
                    System.out.println("This File already exists");
                    fileSearched = tmpDir.getFiles().get(i);
                    return false;
                } else if (tmpDir.getFiles().get(i).getName().equals(splittedRoot[counter]) && fl == 1) {
                    fileSearched = tmpDir.getFiles().get(i);
                    return false;
                }
            }
            if (found == 0 && fl == 0) {
                System.out.println("Wrong Path");
                return false;
            } else if (found == 0 && fl == 1) {
                return false;
            }
        } else  //Case path for folder
        {
            for (; counter < splittedRoot.length; counter++) {
                found = 0;
                for (int i = 0; i < tmpDir.getDirs().size(); i++) {
                    if (tmpDir.getDirs().get(i).getDirectoryName().equals(splittedRoot[counter])) {
                        found = 1;
                        tmpDir = tmpDir.getDirs().get(i);
                    }
                }
            }
            if (found == 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public Set<Integer> getAllocationList(String root) {
        Set<Integer> hash_Set = new HashSet<Integer>();

        for (int i = 0; i < fileData.size(); i++) {
            String[] tmpSplit = fileData.get(i).split("\\s+");
            if (tmpSplit[0].equals(root)) {
                int j = i + 1;
                while (true) {
                    tmpSplit = fileData.get(j).split("\\W+");
                    if (tmpSplit[1].equals("null")) {
                        fileData.remove(j);
                        break;
                    }
                    hash_Set.add(Integer.parseInt(tmpSplit[0]));
                    hash_Set.add(Integer.parseInt(tmpSplit[1]));
                    fileData.remove(j);
                }
            }
        }

        return hash_Set;
    }

    public void deleteFile(String root) throws IOException {
        //Check if exist
        int deleteSize = 0;
        if (!(checkPath(root, 1))) {
            //Deallocate resources
            Set<Integer> listOfAllocation = getAllocationList(root);
            deleteSize = listOfAllocation.size();
            checkSpace.deallocateBlocks(fileSearched, listOfAllocation);
        } else {
            System.out.println("No file exists");
            return;
        }

        int newAllocatedSize = abs(Integer.parseInt(fileData.get(4)) - deleteSize);
        int newFreeSize = abs(Integer.parseInt(fileData.get(5)) + deleteSize);
        fileData.set(5, Integer.toString(newFreeSize));
        fileData.set(4, Integer.toString(newAllocatedSize));

        for (int j = 2; j < fileData.size(); j++) {
            String[] tmpSplit = fileData.get(j).split("\\s+");
            if (tmpSplit[0].equals(root)) {
                fileData.remove(j);
                break;
            }
        }
        updateStructure();
        updateDisk();
    }

    public void deleteFolder(String root) throws IOException {
        //Go to the last children -> delete all its files -> delete Dir -> GO to parent recursively
        if ((checkPath(root, 1))) {
            String[] splitRoot = root.split("\\W+");
            Directory currentDir = mainDir;

            //Find Directory to be Deleted
            for (int i = 1; i < splitRoot.length; i++) {
                for (int j = 0; j < currentDir.getDirs().size(); j++) {
                    if (splitRoot[i].equals(currentDir.getDirs().get(j).getDirectoryName())) {
                        currentDir = currentDir.getDirs().get(j);
                        break;
                    }
                }
            }

            //Delete all inside Directories
            for (int i = 0; i < currentDir.getDirs().size(); i++) {
                deleteFolder(currentDir.getDirs().get(i).getDirectoryPath());
            }

            //Delete all inside files
            for (int i = 0; i < currentDir.getFiles().size(); i++) {
                deleteFile(currentDir.getFiles().get(i).getFilePath());
            }
            for (int j = 2; j < fileData.size(); j++) {
                String[] tmpSplit = fileData.get(j).split("\\s+");
                tmpSplit[0] += "/";
                if (tmpSplit[0].equals(root) || tmpSplit[0].equals(root + "/")) {
                    fileData.remove(j);
                }
            }
            updateStructure();
            updateDisk();
            return;
        } else {
            System.out.println("No Folder exists");
            return;
        }

    }

    //Done
    public void diskStatus() {
        System.out.println("Free space: " + freeSpace);
        System.out.println("Allocated space: " + totalAllocated);
        System.out.println("Blocks state: " + allocationString);
    }

    //Done
    public void diskStructure() {
        recursiveStructure(mainDir, 0);
    }

    //Done
    public void recursiveStructure(Directory main, int lv) {
        if (lv == 0) {
            displayFiles(main);
        }
        int level = lv;
        for (int j = 0; j < main.getDirs().size(); j++) {
            for (int i = 0; i < level; i++) {
                System.out.print(" ");
            }
            System.out.println(main.getDirs().get(j).getDirectoryName());
            if (main.getDirs().get(j).getFiles().size() > 0) {
                for (int i = 0; i < level + 1; i++) {
                    System.out.print(" ");
                }
            }
            displayFiles(main.getDirs().get(j));

            recursiveStructure(main.getDirs().get(j), level + 1);

        }
        return;
    }

    //Done
    public void displayFiles(Directory parent) {
        List<File> tempFiles = parent.getFiles();
        int j = 0;
        while (true) {
            if (j < tempFiles.size()) {
                System.out.println(tempFiles.get(j).getName() + ".txt");
                j++;
            } else {
                break;
            }
        }
    }

    //Done
    void updateDisk() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\test.vfs.txt");
        for (String str : fileData) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    //Done
    public void createFile(String root, String sizeOfBlock) throws IOException {
        if (checkPath(root, 0)) {
            if (Integer.parseInt(sizeOfBlock) < Integer.parseInt(fileData.get(5))) {
                List<Integer> onFileLocation = checkSpace.allocateBlocks(sizeOfBlock);
                if (onFileLocation.size() == Integer.parseInt(sizeOfBlock) + 1) {
                    String storedOnDisk = root + " " + Integer.toString(onFileLocation.get(0)) + " " + Integer.toString(onFileLocation.get(onFileLocation.size() - 2));
                    fileData.add(storedOnDisk);
                    for (int i = 0; i < onFileLocation.size() - 1; i++) {
                        String tmp = onFileLocation.get(i) + " " + onFileLocation.get(i + 1);
                        fileData.add(tmp);
                    }
                    //New Edit
                    int newAllocatedSize = abs(Integer.parseInt(fileData.get(4)) + Integer.parseInt(sizeOfBlock));
                    int newFreeSize = abs(Integer.parseInt(fileData.get(5)) - Integer.parseInt(sizeOfBlock));

                    //New Edit
                    fileData.set(5, Integer.toString(newFreeSize));
                    fileData.set(4, Integer.toString(newAllocatedSize));
                    updateDisk();
                    updateStructure();
                }
            } else {
                System.out.println("Not Enough Space");
            }

        }
    }
}