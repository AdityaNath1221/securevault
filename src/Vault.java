import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class Vault{
    String vaultName;
    ArrayList<Entry> entries = new ArrayList<>();

    static void createVault(Scanner sc){
        System.out.print("Enter the name of vault: ");
        String vaultName = sc.nextLine().toLowerCase();
        String filePath = "vaults/" + vaultName + ".txt";
        File file = new File(filePath);
        if(file.exists()){
            System.out.println("Vault exists. Enter a different name.");
            return;
        }
        try {
            System.out.print("Enter Master Passoword: ");
            String MPass = sc.nextLine();
            FileWriter vault = new FileWriter(filePath);
            vault.write(Hashing.getHash(MPass)+"\n");
            vault.close();
            System.out.println("New vault " + vaultName + " created.");
        } catch (IOException e) {
            System.out.println("Error creating vault.");
        }
    }

    static Vault openVault(Scanner sc){
        System.out.print("Enter the name of vault: ");
        String vaultName = sc.nextLine().toLowerCase();
        String filePath = "vaults/"+vaultName+".txt";
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("Vault "+vaultName+" doesn't exist.");
            return null;
        }
        try{
            Scanner reader = new Scanner(file);
            System.out.print("Enter your Master Password: ");
            String MPass = sc.nextLine();
            String OMPass = reader.nextLine();
            if(Hashing.getHash(MPass).equals(OMPass)){
                Vault v = new Vault(vaultName);
                while(reader.hasNextLine()){
                    String line = reader.nextLine();
                    String[] parts = line.split(",");
                    if(parts.length == 4){
                        Entry e = new Entry(parts[0], parts[1], parts[2], parts[3]);
                        v.entries.add(e);
                    }
                }
                reader.close();
                System.out.println("Vault "+vaultName+" has been opened.");
                return v;
            }
            else{
                System.out.println("Wrong Master Password!");
                reader.close();
            }
        }
        catch(IOException e){
            System.out.println("Error while reading file contents.");
        }
        return null;
    }

    void addEntry(Scanner sc){
        System.out.print("Enter the name of entry (1 word only): ");
        String name = sc.nextLine();
        System.out.print("Enter the site: ");
        String site = sc.nextLine();
        System.out.print("Enter the username: ");
        String username = sc.nextLine();
        System.out.print("Enter the password: ");
        String password = sc.nextLine();
        Entry entry = new Entry(name, site, username, password);
        this.entries.add(entry);
        /*
            Encrypt the credentials and everything after adding.
        */
        String filePath = "vaults/"+this.vaultName+".txt";
        try {
            FileWriter vault = new FileWriter(filePath, true);
            vault.write(name+","+site+","+username+","+password+"\n");
            vault.close();
        } catch (IOException e) {
            System.out.println("Error adding entry.");
            e.printStackTrace();
        }
        System.out.println("Entry added to vault successfully.");
    }

    void listEntries(){
        if (this.entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }
        System.out.println("=== Entries in " + vaultName + " ===");
        int i = 1;
        for (Entry e : this.entries) {
            System.out.println("-------------------------");
            System.out.println("Entry #" + i++);
            System.out.println("Name: " + e.name);
            System.out.println("Site: " + e.site);
            System.out.println("Username: " + e.username);
            System.out.println("Password: " + e.password);
        }
        System.out.println("-------------------------");
    }

    Vault(String name){
        vaultName = name;
    }
}