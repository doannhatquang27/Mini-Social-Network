/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ASUS
 */
public class EncryptionPassword {
    public String getSHAHash(String inputString) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(inputString.getBytes());
        return convertByteToHex(messageDigest);
    }

    public String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%02x", data[i]));
        }
        return sb.toString();
    }
}
