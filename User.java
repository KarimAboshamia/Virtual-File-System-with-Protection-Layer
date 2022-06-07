import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String userPassword;
    private List<String> cap;
    private List<String >paths;
    private int initSize;

    public User(String userName, String userPassword, List<String> cap) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.cap = cap;
        initSize = cap.size();
        paths = new ArrayList<String>();
    }

    public void addCapabilities(String path, String ca) {
        if(!(paths.contains(path)))
        {
            cap.add(path + " " + ca);
            paths.add(path);
        }
        else
        {
            cap.set(initSize + paths.indexOf(path), path + " " + ca);
        }
    }

    //If this user has create capability for this path
    public boolean checkCreate(String path2, int folder) {
        for (int i = 0; i < cap.size(); i++) {
            String[] splitCap = cap.get(i).split("\\s+");
            String[] tmpSplit = path2.split("\\W");
            String path = "";
            if(folder == 0)
            {
                for (int j = 0; j < tmpSplit.length - 1; j++) {
                    if (j == tmpSplit.length - 2) {

                        path += tmpSplit[j];
                    } else {
                        path += tmpSplit[j] + "/";

                    }
                }
            }
            else
            {
                for (int j = 0; j < tmpSplit.length; j++) {
                    if (j == tmpSplit.length - 1) {

                        path += tmpSplit[j];
                    } else {
                        path += tmpSplit[j] + "/";

                    }
                }
            }

            if (path.equals(splitCap[0]) && splitCap[1].charAt(0) == '1') {
                return true;
            }
        }
        return false;
    }


    public boolean checkDelete(String path2, int folder) {
        for (int i = 0; i < cap.size(); i++) {
            String[] splitCap = cap.get(i).split("\\s+");
            String[] tmpSplit = path2.split("\\W");
            String path = "";
            if(folder == 0)
            {
                for (int j = 0; j < tmpSplit.length - 1; j++) {
                    if (j == tmpSplit.length - 2) {

                        path += tmpSplit[j];
                    } else {
                        path += tmpSplit[j] + "/";

                    }
                }
            }
            else
            {
                for (int j = 0; j < tmpSplit.length; j++) {
                    if (j == tmpSplit.length - 1) {

                        path += tmpSplit[j];
                    } else {
                        path += tmpSplit[j] + "/";

                    }
                }
            }

            if (path.equals(splitCap[0]) && splitCap[1].charAt(1) == '1') {
                return true;
            }
        }
        return false;

    }


    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public List<String> getCap() {
        return cap;
    }
}
