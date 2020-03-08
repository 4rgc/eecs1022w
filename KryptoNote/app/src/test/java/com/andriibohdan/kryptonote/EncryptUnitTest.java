package com.andriibohdan.kryptonote;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncryptUnitTest {
    @Test
    public void encryption0_isCorrect() {
        Cipher c = new Cipher("0");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals(" ABCDEFGHIJKLMNOPQRSTUVWXYZ", cipherString);
    }

    @Test
    public void encryption1_isCorrect() {
        Cipher c = new Cipher("1");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ ", cipherString);
    }

    @Test
    public void encryption2_isCorrect() {
        Cipher c = new Cipher("2");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("BCDEFGHIJKLMNOPQRSTUVWXYZ A", cipherString);
    }

    @Test
    public void encryption3_isCorrect() {
        Cipher c = new Cipher("3");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("CDEFGHIJKLMNOPQRSTUVWXYZ AB", cipherString);
    }

    @Test
    public void encryption4_isCorrect() {
        Cipher c = new Cipher("4");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("DEFGHIJKLMNOPQRSTUVWXYZ ABC", cipherString);
    }

    @Test
    public void encryption5_isCorrect() {
        Cipher c = new Cipher("5");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("EFGHIJKLMNOPQRSTUVWXYZ ABCD", cipherString);
    }

    @Test
    public void encryption6_isCorrect() {
        Cipher c = new Cipher("6");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("FGHIJKLMNOPQRSTUVWXYZ ABCDE", cipherString);
    }

    @Test
    public void encryption7_isCorrect() {
        Cipher c = new Cipher("7");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("GHIJKLMNOPQRSTUVWXYZ ABCDEF", cipherString);
    }

    @Test
    public void encryption8_isCorrect() {
        Cipher c = new Cipher("8");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("HIJKLMNOPQRSTUVWXYZ ABCDEFG", cipherString);
    }

    @Test
    public void encryption9_isCorrect() {
        Cipher c = new Cipher("9");
        String cipherString = c.encrypt(Cipher.ALPHABET);
        assertEquals("IJKLMNOPQRSTUVWXYZ ABCDEFGH", cipherString);
    }
}
