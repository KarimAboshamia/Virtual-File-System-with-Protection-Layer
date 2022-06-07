import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class IndexedSpaceManager {

    private List<String> fileData;
    private int allocationSize;
    private String allocation;

    public IndexedSpaceManager(List<String> data, int allocationsz, String allo) {
        fileData = data;
        allocationSize = allocationsz;
        allocation = allo;
    }

    public List<Integer> allocateBlocks(String sizeOfBlock) throws IOException {
        List<Integer> arr = new ArrayList<Integer>();
        int counter = 0;
        String edit = allocation;
        while (counter < Integer.parseInt(sizeOfBlock)) {
            for (int i = 0; i < allocation.length(); i++) {
                if (allocation.charAt(i) == '0' && counter <= Integer.parseInt(sizeOfBlock)) {
                    //11111 0 11111
                    edit = edit.substring(0, i) + '1' + edit.substring(i + 1);
                    counter++;
                    arr.add(i);
                }
                if (counter == Integer.parseInt(sizeOfBlock)) {
                    break;
                }
            }
            if (counter == Integer.parseInt(sizeOfBlock)) {
                allocation = edit;
                fileData.set(2, allocation);
                FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\test.vfs.txt");
                for (String str : fileData) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
            }
        }
        return arr;
    }


    public void deallocateBlocks(File fileSearched, Set<Integer> allocaionList) throws IOException {
        for (int i : allocaionList) {
            allocation = allocation.substring(0, i) + '0' + allocation.substring(i + 1);
        }
        fileData.set(2, allocation);
        FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\test.vfs.txt");
        for (String str : fileData) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

}