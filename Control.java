import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Control {

    User user;
    Users users;
    Directory mainDir;
    List<String> userFile = new ArrayList<String>();
    List<String> capFile = new ArrayList<String>();

    public Control() throws IOException {
        users = new Users();
        users.createUsersList();
        this.user = login("admin", "admin");
        userFile = readFile("user.txt");
        capFile = readFile("capabilities.txt");
    }

    public List<String> readFile(String fl) throws IOException {
        String localDir = System.getProperty("user.dir");
        String filePath = localDir + "\\src\\" + fl;
        BufferedReader file = new BufferedReader(new FileReader(filePath));
        List<String> fileData = new ArrayList<String>();
        String ln;
        while ((ln = file.readLine()) != null) {
            fileData.add(ln);
        }
        file.close();
        return fileData;
    }

    public void createUser(String name, String password) throws IOException {
        //check that admin is logged in
        if (user.getUserName().equals("admin")) {
            List<String> emptyCap = new ArrayList<String>();
            for (int i = 0; i < users.getUsersList().size(); i++) {
                if (users.getUsersList().get(i).getUserName().equals(name)) {
                    System.out.println("This user already exists");
                    return;
                }
            }
            users.addUser(new User(name, password, emptyCap));
            //add user to users.txt
            userFile.add(name + "," + password);
            updateDisk1();
        } else {
            System.out.println("You don't have creating user permission");
        }
    }

    public void grantCap(String name, String path, String cap, Directory dir) throws IOException {
        mainDir = dir;
        boolean flag = false;
        String[] tmpSplitting = path.split("\\W+");
        if(tmpSplitting[tmpSplitting.length-1].equals("txt"))
        {
            System.out.println("You can't grant permission on a file");
        }
        if (checkPath(path, 1)) {
            int f = 0;
            for (int i = 0; i < users.getUsersList().size(); i++) {
                if (users.getUsersList().get(i).getUserName().equals(name)) {
                    users.getUsersList().get(i).addCapabilities(path, cap);
                    //Add this cap to cap.txt
                    for (int j = 0; j < capFile.size(); j++) {
                        String[] splitCap = capFile.get(j).split(",");
                        if (path.equals(splitCap[0])) {
                            String edit = "";
                            for (int k = 0; k < splitCap.length; k++) {
                                if (splitCap[k].equals(name)) {
                                    edit += splitCap[k] + "," + cap + ',';
                                    k += 1;
                                    f = 1;
                                } else {
                                    edit += splitCap[k] + ",";
                                }
                            }
                            if(f == 0)
                            {
                                capFile.set(j, edit + name + "," + cap);
                            }
                            else
                            {
                                capFile.set(j, edit);
                            }
                            flag = true;
                        }
                    }
                    if (flag == false)
                        capFile.add(path + "," + name + "," + cap);
                    updateDisk2();
                    return;
                }
            }
            System.out.print("This user doesn't exist");
        } else {
            System.out.println("Invalid Path");
        }

    }

    public boolean checkBeforeCreate(String path, int check) {
        String[] splittedRoot = path.split("\\W+");
        String finalPath = "";
        if (splittedRoot[splittedRoot.length - 1].equals("txt")) {
            for (int i = 0; i < splittedRoot.length; i++) {
                if (i == splittedRoot.length - 3) {
                    finalPath += splittedRoot[i];
                    break;
                } else {
                    finalPath += splittedRoot[i] + "/";
                }

            }
        } else {
            finalPath = path;
        }

        boolean res = user.checkCreate(finalPath,check);
        if (res == true || user.getUserName().equals("admin")) {
            return true;
        } else {
            System.out.println("User Doesn't have the permission of Creating in this folder");
            return false;
        }
    }

    public boolean checkBeforeDelete(String path, int check) {

        String[] splittedRoot = path.split("\\W+");
        String finalPath = "";
        if (splittedRoot[splittedRoot.length - 1].equals("txt")) {
            for (int i = 0; i < splittedRoot.length; i++) {
                if (i == splittedRoot.length - 3) {
                    finalPath += splittedRoot[i];
                    break;
                } else {
                    finalPath += splittedRoot[i] + "/";
                }

            }
        } else {
            finalPath = path;
        }

        boolean res = user.checkDelete(finalPath, check);
        if (res == true || user.getUserName().equals("admin")) {
            return true;
        } else {
            System.out.println("User Doesn't have the permission of Deleting in this folder");
            return false;
        }
    }

    public boolean checkPath(String root, int fl) {
        String[] splittedRoot = root.split("\\W+");
        Directory tmpDir = mainDir;
        int flag = 0;
        if (!((splittedRoot[0].equals("root")) && fl == 1) || (splittedRoot[splittedRoot.length - 1].equals("text"))) {
            System.out.println("Wrong Path");
            return false;
        }
        int counter = 1;
        int found = 0;
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


    public User login(String name, String password) {
        //search users for user with matching name and password
        List<User> systemUsers = users.getUsersList();
        for (int i = 0; i < systemUsers.size(); i++) {
            if (systemUsers.get(i).getUserName().equals(name) && systemUsers.get(i).getUserPassword().equals(password)) {
                this.user = systemUsers.get(i);
                displayName();
                return systemUsers.get(i);
            }
        }

        //user = user if found else return wrong email or password
        System.out.println("Wrong E-mail or Password");
        return null;
    }

    public void displayName() {
        System.out.println(user.getUserName() + " is currently logged in.");
    }

    void updateDisk1() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\user.txt");
        for (String str : userFile) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    void updateDisk2() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\capabilities.txt");
        for (String str : capFile) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

}
