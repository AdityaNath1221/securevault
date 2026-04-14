import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class Encryption {

    static final String SHA_CRYPT = "SHA-256";
    static final String AES_ALGORITHM = "AES";
    static final String AES_ALGORITHM_GCM = "AES/GCM/NoPadding";

    static final Integer IV_LENGTH_ENCRYPT = 12;
    static final Integer TAG_LENGTH_ENCRYPT = 16;

    static String encrypt(String plainText, String passphrase) throws Exception {
        byte[] iv = new byte[IV_LENGTH_ENCRYPT];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        SecretKeySpec aesKey = generateAesKeyFromPassphrase(passphrase);

        Cipher cipher = Cipher.getInstance(AES_ALGORITHM_GCM);
        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_ENCRYPT * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, gcmSpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        byte[] combinedIvAndCipherText = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, combinedIvAndCipherText, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combinedIvAndCipherText, iv.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(combinedIvAndCipherText);
    }

    static String decrypt(String cipherText, String passphrase) throws Exception {
        byte[] decodedCipherText = Base64.getDecoder().decode(cipherText);

        SecretKeySpec aesKey = generateAesKeyFromPassphrase(passphrase);

        byte[] iv = new byte[IV_LENGTH_ENCRYPT];
        System.arraycopy(decodedCipherText, 0, iv, 0, iv.length);
        byte[] encryptedText = new byte[decodedCipherText.length - IV_LENGTH_ENCRYPT];
        System.arraycopy(decodedCipherText, IV_LENGTH_ENCRYPT, encryptedText, 0, encryptedText.length);

        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_ENCRYPT * 8, iv);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM_GCM);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, gcmSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedText);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKeySpec generateAesKeyFromPassphrase(String passphrase) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance(SHA_CRYPT);
        byte[] keyBytes = sha256.digest(passphrase.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(keyBytes, AES_ALGORITHM);
    }
}