package com.sains;

import java.io.Writer;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.security.*;

import java.util.Base64;

public class Main {

    public static Base64.Encoder encoder = Base64.getEncoder();

    public static void main(String[] args) throws Exception {
//        if ( args.length == 0 ) {
//            System.out.println("Example usage:");
//            System.out.println("  rsa_keygen [filename]");
//            System.exit(1);
//        }

        String algo = "rsa"; // default to RSA key generation algorithm
        int keySize = 4096; // initializing RSA with keySize of 4096

        String outFile = "C:\\Users\\Alshafiq\\Desktop\\merchant";

        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo);
        kpg.initialize(keySize);
        KeyPair kp = kpg.generateKeyPair();

        System.out.println("Generating private key, format: " + kp.getPrivate().getFormat());
        System.out.println("Generating private key, content: " + encoder.encodeToString(kp.getPrivate().getEncoded()));

        // Begin writing to file
        Writer private_key = new FileWriter(outFile + ".key");
        private_key.write("-----BEGIN RSA PRIVATE KEY-----\n");
        writeBase64(private_key, kp.getPrivate());
        private_key.write("-----END RSA PRIVATE KEY-----\n");
        private_key.close();
        // end writing
        System.out.println("Your private key has been saved in " + outFile + ".key");



        System.out.println("Generating public key, format: " + kp.getPublic().getFormat());
        System.out.println("Generating public key, content: " + encoder.encodeToString(kp.getPublic().getEncoded()));

        // Begin writing to file
        Writer public_key = new FileWriter(outFile + ".pub");
        public_key.write("-----BEGIN RSA PUBLIC KEY-----\n");
        writeBase64(public_key, kp.getPublic());
        public_key.write("-----END RSA PUBLIC KEY-----\n");
        public_key.close();
        // End writing
        System.out.println("Your private key has been saved in " + outFile + ".pub");
    }

    public static void writeBase64(Writer out,Key key) throws java.io.IOException
    {
        byte[] buf = key.getEncoded();
        out.write(encoder.encodeToString(buf));
        out.write("\n");
    }

    public static byte[] hexStringToByteArray(String hex) {
        int l = hex.length();
        byte[] data = new byte[l / 2];
        for (int i = 0; i < l; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
