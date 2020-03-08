package com.andriibohdan.kryptonote;

import static java.lang.System.out;

public class Cipher {
    private String key;
    public static final String ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Cipher(String k) {
        key = k;
    }

    public String encrypt(String note) {
        String pad = makePad(note);
        String res = "";
        for(int i = 0; i < note.length(); i++) {
            int offset = Integer.parseInt(pad.substring(i,i+1));
            int alphInd = ALPHABET.indexOf(note.charAt(i));
            int cipherInd = alphInd + offset;
            int cipherIndLooped =
                    (cipherInd >= ALPHABET.length()) ?
                            cipherInd-ALPHABET.length() :
                            cipherInd;
            res += ALPHABET.substring(cipherIndLooped, cipherIndLooped+1);
        }
        return res;
    }

    public String decrypt(String cipherText) {
        String pad = makePad(cipherText);
        String res = "";
        for(int i = 0; i < cipherText.length(); i++) {
            int offset = Integer.parseInt(pad.substring(i,i+1));
            int alphInd = ALPHABET.indexOf(cipherText.charAt(i));
            int cipherInd = alphInd - offset;
            int cipherIndLooped =
                    (cipherInd < 0) ? //if cipherInd is negative
                            ALPHABET.length() + cipherInd : // get character with index |cipherInd|
                                                            // from the back
                            cipherInd; //else get character with index cipherInd
            res += ALPHABET.substring(cipherIndLooped, cipherIndLooped+1);
        }
        return res;
    }

    private String makePad(String text) {
        String pad = this.key;
        for(;pad.length() < text.length();) {
            pad += this.key;
        }
        return pad;
    }
}
