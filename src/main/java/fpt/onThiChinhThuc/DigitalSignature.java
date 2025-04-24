package fpt.onThiChinhThuc;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

public class DigitalSignature {

    public static void main(String[] args) throws Exception {

        String data = "this is sensitive data";

        // Tạo cặp khóa RSA

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2028);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Tạo chữ ký số

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(data.getBytes());
        byte[] signed = signature.sign();
        System.out.println("Signature created!");

        // Xác thực chữ ký
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(data.getBytes());
        boolean verified = verifier.verify(signed);
        System.out.println("Signature verified: " + verified);
    }
}
