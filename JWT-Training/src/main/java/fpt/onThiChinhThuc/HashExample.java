package fpt.onThiChinhThuc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashExample {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "hello123";

    // Tạo hash MD5

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Hash = md5.digest(input.getBytes());
        System.out.println("MD5: " + Base64.getEncoder().encodeToString(md5Hash));


        // Tạo hash với SHA-256
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] shaHash = sha256.digest(input.getBytes());
        System.out.println("SHA-256: " + Base64.getEncoder().encodeToString(shaHash));


    }

}
