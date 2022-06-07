import java.io.*;
import java.util.*;

public class main {

    public static void main(String[] args) throws IOException {
        String localDir = System.getProperty("user.dir");
        String filePath = localDir + "\\src\\test.vfs.txt";
        BufferedReader file = new BufferedReader(new FileReader(filePath));
        List<String> fileData = new ArrayList<String>();
        String ln;
        String choice;
        String allocationChoice;
        while ((ln = file.readLine()) != null) {
            fileData.add(ln);
        }
        file.close();
        Control controlProgram = new Control();
        System.out.println(fileData.get(0));
        Scanner sc = new Scanner(System.in);
        System.out.println("1- Change/Choose Allocation technique and erase all file data.");
        System.out.println("2- Interact with your virtual file system.");
        choice = sc.nextLine();
        if (choice.equals("1")) {
            PrintWriter erase = new PrintWriter(filePath);
            erase.print("");
            erase.close();
            System.out.println("1- Contiguous Allocation ");
            System.out.println("2- Indexed Allocation");
            System.out.println("3- Linked Allocation");
            allocationChoice = sc.nextLine();
            if (allocationChoice.equals("1")) {
                List<String> writeData = new ArrayList<String>();
                writeData.add("Contiguous Allocation technique is used");
                writeData.add("start");
                writeData.add("0000000000000000000000000000000000000000000000000000000000000000000000000");
                writeData.add("end");
                writeData.add("0");
                writeData.add("73");
                FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\test.vfs.txt");
                for (String str : writeData) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
                Contiguous tech = new Contiguous(localDir, filePath, file, writeData);
                while (true) {
                    String ch = sc.nextLine();
                    String[] sp = ch.split("\\s+");
                    if (sp[0].equals("CreateFile")) {
                        if (controlProgram.checkBeforeCreate(sp[1], 1)) {
                            tech.createFile(sp[1], sp[2]);
                        }
                    } else if (sp[0].equals("CreateFolder")) {
                        if (controlProgram.checkBeforeCreate(sp[1], 0)) {
                            tech.createFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFile")) {
                        if (controlProgram.checkBeforeDelete(sp[1],1)) {
                            tech.deleteFile(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFolder")) {
                        if (controlProgram.checkBeforeDelete(sp[1],0)) {
                            tech.deleteFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DisplayDiskStatus")) {
                        tech.diskStatus();
                    } else if (ch.equals("DisplayDiskStructure")) {
                        tech.diskStructure();
                    } else if (sp[0].equals("TellUser")) {
                        controlProgram.displayName();
                    } else if (sp[0].equals("CUser")) {
                        controlProgram.createUser(sp[1], sp[2]);
                    } else if (sp[0].equals("Login")) {
                        controlProgram.login(sp[1], sp[2]);
                    } else if (sp[0].equals("Grant")) {
                        controlProgram.grantCap(sp[1], sp[2], sp[3], tech.getMainDir());
                    }
                }

            } else if (allocationChoice.equals("2")) {
                List<String> writeData = new ArrayList<String>();
                writeData.add("Indexed Allocation technique is used");
                writeData.add("start");
                writeData.add("0000000000000000000000000000000000000000000000000000000000000000000000000");
                writeData.add("end");
                writeData.add("0");
                writeData.add("73");
                FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\test.vfs.txt");
                for (String str : writeData) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
                Indexed tech = new Indexed(localDir, filePath, file, writeData);
                while (true) {
                    String ch = sc.nextLine();
                    String[] sp = ch.split("\\s+");
                    if (sp[0].equals("CreateFile")) {
                        if (controlProgram.checkBeforeCreate(sp[1],1)) {
                            tech.createFile(sp[1], sp[2]);
                        }
                    } else if (sp[0].equals("CreateFolder")) {
                        if (controlProgram.checkBeforeCreate(sp[1],0)) {
                            tech.createFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFile")) {
                        if (controlProgram.checkBeforeDelete(sp[1],1)) {
                            tech.deleteFile(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFolder")) {
                        if (controlProgram.checkBeforeDelete(sp[1],0)) {
                            tech.deleteFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DisplayDiskStatus")) {
                        tech.diskStatus();
                    } else if (ch.equals("DisplayDiskStructure")) {
                        tech.diskStructure();
                    } else if (sp[0].equals("TellUser")) {
                        controlProgram.displayName();
                    } else if (sp[0].equals("CUser")) {
                        controlProgram.createUser(sp[1], sp[2]);
                    } else if (sp[0].equals("Login")) {
                        controlProgram.login(sp[1], sp[2]);
                    } else if (sp[0].equals("Grant")) {
                        controlProgram.grantCap(sp[1], sp[2], sp[3], tech.getMainDir());
                    }
                }

            } else if (allocationChoice.equals("3")) {
                List<String> writeData = new ArrayList<String>();
                writeData.add("Linked Allocation technique is used");
                writeData.add("start");
                writeData.add("0000000000000000000000000000000000000000000000000000000000000000000000000");
                writeData.add("end");
                writeData.add("0");
                writeData.add("73");
                FileWriter writer = new FileWriter("C:\\Users\\Rocker\\Desktop\\Assignment4OS\\src\\test.vfs.txt");
                for (String str : writeData) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
                Linked tech = new Linked(localDir, filePath, file, writeData);
                while (true) {
                    String ch = sc.nextLine();
                    String[] sp = ch.split("\\s+");
                    if (sp[0].equals("CreateFile")) {
                        if (controlProgram.checkBeforeCreate(sp[1],1)) {
                            tech.createFile(sp[1], sp[2]);
                        }
                    } else if (sp[0].equals("CreateFolder")) {
                        if (controlProgram.checkBeforeCreate(sp[1],0)) {
                            tech.createFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFile")) {
                        if (controlProgram.checkBeforeDelete(sp[1],1)) {
                            tech.deleteFile(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFolder")) {
                        if (controlProgram.checkBeforeDelete(sp[1],0)) {
                            tech.deleteFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DisplayDiskStatus")) {
                        tech.diskStatus();
                    } else if (ch.equals("DisplayDiskStructure")) {
                        tech.diskStructure();
                    } else if (sp[0].equals("TellUser")) {
                        controlProgram.displayName();
                    } else if (sp[0].equals("CUser")) {
                        controlProgram.createUser(sp[1], sp[2]);
                    } else if (sp[0].equals("Login")) {
                        controlProgram.login(sp[1], sp[2]);
                    } else if (sp[0].equals("Grant")) {
                        controlProgram.grantCap(sp[1], sp[2], sp[3], tech.getMainDir());
                    }
                }
            }

        } else if (choice.equals("2")) {
            String[] words = fileData.get(0).split("\\W+");
            if (words[0].equals("Contiguous")) {
                Contiguous tech = new Contiguous(localDir, filePath, file, fileData);
                while (true) {
                    String ch = sc.nextLine();
                    String[] sp = ch.split("\\s+");
                    if (sp[0].equals("CreateFile")) {
                        if (controlProgram.checkBeforeCreate(sp[1],1)) {
                            tech.createFile(sp[1], sp[2]);
                        }
                    } else if (sp[0].equals("CreateFolder")) {
                        if (controlProgram.checkBeforeCreate(sp[1],0)) {
                            tech.createFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFile")) {
                        if (controlProgram.checkBeforeDelete(sp[1],1)) {
                            tech.deleteFile(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFolder")) {
                        if (controlProgram.checkBeforeDelete(sp[1],0)) {
                            tech.deleteFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DisplayDiskStatus")) {
                        tech.diskStatus();
                    } else if (ch.equals("DisplayDiskStructure")) {
                        tech.diskStructure();
                    } else if (sp[0].equals("TellUser")) {
                        controlProgram.displayName();
                    } else if (sp[0].equals("CUser")) {
                        controlProgram.createUser(sp[1], sp[2]);
                    } else if (sp[0].equals("Login")) {
                        controlProgram.login(sp[1], sp[2]);
                    } else if (sp[0].equals("Grant")) {
                        controlProgram.grantCap(sp[1], sp[2], sp[3], tech.getMainDir());
                    }
                }
            } else if (words[0].equals("Indexed")) {
                while (true) {
                    Indexed tech = new Indexed(localDir, filePath, file, fileData);
                    String ch = sc.nextLine();
                    String[] sp = ch.split("\\s+");
                    if (sp[0].equals("CreateFile")) {
                        if (controlProgram.checkBeforeCreate(sp[1],1)) {
                            tech.createFile(sp[1], sp[2]);
                        }
                    } else if (sp[0].equals("CreateFolder")) {
                        if (controlProgram.checkBeforeCreate(sp[1],0)) {
                            tech.createFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFile")) {
                        if (controlProgram.checkBeforeDelete(sp[1],1)) {
                            tech.deleteFile(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFolder")) {
                        if (controlProgram.checkBeforeDelete(sp[1],0)) {
                            tech.deleteFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DisplayDiskStatus")) {
                        tech.diskStatus();
                    } else if (ch.equals("DisplayDiskStructure")) {
                        tech.diskStructure();
                    } else if (sp[0].equals("TellUser")) {
                        controlProgram.displayName();
                    } else if (sp[0].equals("CUser")) {
                        controlProgram.createUser(sp[1], sp[2]);
                    } else if (sp[0].equals("Login")) {
                        controlProgram.login(sp[1], sp[2]);
                    } else if (sp[0].equals("Grant")) {
                        controlProgram.grantCap(sp[1], sp[2], sp[3], tech.getMainDir());
                    }
                }

            } else if (words[0].equals("Linked")) {
                Linked tech = new Linked(localDir, filePath, file, fileData);
                while (true) {
                    String ch = sc.nextLine();
                    String[] sp = ch.split("\\s+");
                    if (sp[0].equals("CreateFile")) {
                        if (controlProgram.checkBeforeCreate(sp[1],1)) {
                            tech.createFile(sp[1], sp[2]);
                        }
                    } else if (sp[0].equals("CreateFolder")) {
                        if (controlProgram.checkBeforeCreate(sp[1],0)) {
                            tech.createFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFile")) {
                        if (controlProgram.checkBeforeDelete(sp[1],1)) {
                            tech.deleteFile(sp[1]);
                        }
                    } else if (sp[0].equals("DeleteFolder")) {
                        if (controlProgram.checkBeforeDelete(sp[1],0)) {
                            tech.deleteFolder(sp[1]);
                        }
                    } else if (sp[0].equals("DisplayDiskStatus")) {
                        tech.diskStatus();
                    } else if (ch.equals("DisplayDiskStructure")) {
                        tech.diskStructure();
                    } else if (sp[0].equals("TellUser")) {
                        controlProgram.displayName();
                    } else if (sp[0].equals("CUser")) {
                        controlProgram.createUser(sp[1], sp[2]);
                    } else if (sp[0].equals("Login")) {
                        controlProgram.login(sp[1], sp[2]);
                    } else if (sp[0].equals("Grant")) {
                        controlProgram.grantCap(sp[1], sp[2], sp[3], tech.getMainDir());
                    }
                }
            }
        }
    }
}
