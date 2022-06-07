import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> users;

    public Users() {
        users = new ArrayList<User>();
    }

    public List<User> getUsersList() {
        return users;
    }

    public void createUsersList() throws IOException {
        String localDir = System.getProperty("user.dir");
        String filePath = localDir + "\\src\\user.txt";
        BufferedReader file = new BufferedReader(new FileReader(filePath));
        List<String> fileData = new ArrayList<String>();
        String ln;
        while ((ln = file.readLine()) != null) {
            fileData.add(ln);
        }
        file.close();

        for (int i = 0; i < fileData.size(); i++) {
            String[] splitLine = fileData.get(i).split("\\W+");
            List<String> comp = addCapabilities(splitLine[0]);

            User user = new User(splitLine[0], splitLine[1], comp);


            users.add(user);
        }
    }


    public List<String> addCapabilities(String name) throws IOException {
        String localDir = System.getProperty("user.dir");
        String filePath = localDir + "\\src\\capabilities.txt";
        BufferedReader file = new BufferedReader(new FileReader(filePath));
        List<String> fileData = new ArrayList<String>();
        String ln;
        while ((ln = file.readLine()) != null) {
            fileData.add(ln);
        }
        file.close();
        List<String> cap = new ArrayList<String>();
        for (int i = 0; i < fileData.size(); i++) {
            String[] splitLine = fileData.get(i).split(",");
            for (int j = 0; j < splitLine.length; j++) {
                if (splitLine[j].equals(name)) {
                    cap.add(splitLine[0] + " " + splitLine[j + 1]);
                }
            }
        }
        return cap;
    }

    public void addUser(User user) {
        users.add(user);
    }
}

