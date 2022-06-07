import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.floor;

public class SpaceManager {

    private List<String> fileData;
    private int allocationSize;
    private String allocation;

    public SpaceManager(List<String> data, int allocationsz, String allo) {
        fileData = data;
        allocationSize = allocationsz;
        allocation = allo;
    }

    public int allocateBlocks(int counter, int startIndex) throws IOException {
        //get row that contains the block
        String edit = fileData.get(2);
        int limit = counter + startIndex;
        int tmpIndex = startIndex;
        for (; tmpIndex < limit; tmpIndex++) {
            edit = edit.substring(0, tmpIndex) + '1' + edit.substring(tmpIndex + 1);
        }
        //update row in array
        fileData.set(2, edit);
        allocation = edit;
        //update file on disk
        FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\test.vfs.txt");
        for (String str : fileData) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        return startIndex;
    }

    public int findFreeBlocks(String sizeOfBlock) throws IOException {
        int counter = 0;
        int startIndex = 0;
        int flag = 0;
        int finalIndex = -1;
        int currentCounter = 10000;

        if (Integer.parseInt(sizeOfBlock) < Integer.parseInt(fileData.get(5))) {
            for (int i = 0; i < allocation.length(); i++) {
                if (allocation.charAt(i) == '0') {
                    counter++;
                    if (flag == 0) {
                        startIndex = i;
                        flag = 1;
                    }
                } else {
                    if (counter >= Integer.parseInt(sizeOfBlock) && counter < currentCounter) {
                        flag = 0;
                        currentCounter = counter;
                        counter = 0;

                        finalIndex = startIndex;
                    }

                }
            }

            //Corner Case
            if (counter == allocation.length()) finalIndex = startIndex;
            if(counter >= Integer.parseInt(sizeOfBlock) && counter < currentCounter)
            {
                currentCounter = counter;
                counter = 0;
                finalIndex = startIndex;
            }
            if (currentCounter >= Integer.parseInt(sizeOfBlock)) {
                return allocateBlocks(Integer.parseInt(sizeOfBlock), finalIndex);
            }
        }
        System.out.println("Not Enough space for this File");
        return -1;
    }

    public void deallocateBlocks(File deletedFile) throws IOException {
        int startIndex = Integer.parseInt(deletedFile.getStart());
        int counter = Integer.parseInt(deletedFile.getSize());
        String edit = fileData.get(2);
        int limit = counter + startIndex;
        for (; startIndex < limit; startIndex++) {
            edit = edit.substring(0, startIndex) + '0' + edit.substring(startIndex + 1);
        }
        //update row in array
        fileData.set(2, edit);
        //update file on disk
        FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment3OS\\src\\test.vfs.txt");
        for (String str : fileData) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

    }
}
