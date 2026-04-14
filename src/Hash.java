import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Hashing{
    public static String getHash(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexCode = new StringBuilder();
            for(byte b: hash){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length()==1){
                    hex = "0" + hex;
                }
                hexCode.append(hex);
            }
            return hexCode.toString();
        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}