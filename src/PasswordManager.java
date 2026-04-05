import java.io.File;
import java.util.Scanner;
class PasswordManager{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        File dir = new File("vaults");
        if (!dir.exists()) {
            dir.mkdir();
        }
        int ch;
        Vault v = null;
        System.out.println("===PASSWORD MANAGER===");
        while(true){
            if(v!=null){
                System.out.println();
                System.out.println("Current Vault: "+v.vaultName);
                System.out.println();
            }
            System.out.println("Enter 1 to create a new vault.");
            System.out.println("Enter 2 to open an existing vault.");
            if(v!=null){
                System.out.println("Enter 3 to add a new entry.");
                System.out.println("Enter 4 to list all entries.");
                System.out.println("Enter 5 to close current vault.");
            }
            System.out.println("Enter -1 to exit");
            ch = sc.nextInt();
            sc.nextLine();
            switch(ch){
                case 1:
                    Vault.createVault(sc);
                    break;
                case 2:
                    v = Vault.openVault(sc);
                    break;
                case 3:
                    if(v!=null){
                        v.addEntry(sc);
                    }
                    else{
                        System.out.println("Open a vault first.");
                    }
                    break;
                case 4:
                    if(v!=null){
                        v.listEntries();
                    }
                    else{
                        System.out.println("Open a vault first.");
                    }
                    break;
                case 5:
                    if(v!=null){
                        v = null;
                        System.out.println("Vault closed.");
                    }
                    else{
                        System.out.println("Open a vault first.");
                    }
                    break;
                case -1:
                    System.out.println("Exiting the program...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}