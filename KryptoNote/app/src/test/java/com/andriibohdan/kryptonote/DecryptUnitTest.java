package com.andriibohdan.kryptonote;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecryptUnitTest {
    @Test
    public void decryption0_isCorrect() {
        Cipher c = new Cipher("0");
        String data = c.decrypt(" ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption1_isCorrect() {
        Cipher c = new Cipher("1");
        String data = c.decrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ ");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption2_isCorrect() {
        Cipher c = new Cipher("2");
        String data = c.decrypt("BCDEFGHIJKLMNOPQRSTUVWXYZ A");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption3_isCorrect() {
        Cipher c = new Cipher("3");
        String data = c.decrypt("CDEFGHIJKLMNOPQRSTUVWXYZ AB");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption4_isCorrect() {
        Cipher c = new Cipher("4");
        String data = c.decrypt("DEFGHIJKLMNOPQRSTUVWXYZ ABC");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption5_isCorrect() {
        Cipher c = new Cipher("5");
        String data = c.decrypt("EFGHIJKLMNOPQRSTUVWXYZ ABCD");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption6_isCorrect() {
        Cipher c = new Cipher("6");
        String data = c.decrypt("FGHIJKLMNOPQRSTUVWXYZ ABCDE");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption7_isCorrect() {
        Cipher c = new Cipher("7");
        String data = c.decrypt("GHIJKLMNOPQRSTUVWXYZ ABCDEF");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption8_isCorrect() {
        Cipher c = new Cipher("8");
        String data = c.decrypt("HIJKLMNOPQRSTUVWXYZ ABCDEFG");
        assertEquals(Cipher.ALPHABET, data);
    }

    @Test
    public void decryption9_isCorrect() {
        Cipher c = new Cipher("9");
        String data = c.decrypt("IJKLMNOPQRSTUVWXYZ ABCDEFGH");
        assertEquals(Cipher.ALPHABET, data);
    }
}
