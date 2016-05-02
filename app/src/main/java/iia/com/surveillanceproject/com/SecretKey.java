package iia.com.surveillanceproject.com;

import android.util.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

import iia.com.surveillanceproject.com.asymetric.PrivateKeyReader;
import iia.com.surveillanceproject.com.asymetric.PublicKeyReader;

/**
 * Created by Thom' on 01/05/2016.
 */
public class SecretKey {


	/**
	* Encrypt kc
	*/
    public static String encryptKc(byte[] kc) {

        final String PATH_PUBLIC_KEY = "./data/data/iia.com.surveillanceproject/public_key.der";

        PublicKey pubKey = null;
        try {
            pubKey = PublicKeyReader.get(PATH_PUBLIC_KEY);
        } catch (Exception e) {
            System.out.println(e);
        }

        byte[] cipherText = null;
        try {
			
			// Get instance
            final Cipher cipher = Cipher.getInstance("RSA");
			// Init Cipher
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			// Encrypt kc 
            cipherText = cipher.doFinal(kc);
        } catch (Exception e) {
            e.printStackTrace();
        }
		// Return SecretKey encrypted Base64
        return Base64.encodeToString(cipherText,Base64.NO_WRAP);
    }

	/**
	* Decrypt kc
	*/
    public static String decryptKc(String kc) {

        final String PATH_PRIVATE_KEY = "./data/data/iia.com.surveillanceproject/private_key.der";
        PrivateKey privKey = null;
        try {
            privKey = PrivateKeyReader.get(PATH_PRIVATE_KEY);
        } catch (Exception e) {
            System.out.println(e);
        }
        byte[] dectyptedText = null;
        try {
			// Get instance
            final Cipher cipher = Cipher.getInstance("RSA");
			// Init Cipher 
            cipher.init(Cipher.DECRYPT_MODE, privKey);
			// Decrypt SecretKey
            dectyptedText = cipher.doFinal(Base64.decode(kc,Base64.DEFAULT));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

		// Return SecretKey decrypted Base64
        return Base64.encodeToString(dectyptedText,Base64.DEFAULT);
    }

	/**
	* Extract SecretKey from message
	*/
    public static String ExtractKc(String message) {

		// Message Base64 to byte[]
        final byte[] messageBytes = Base64.decode(message,Base64.DEFAULT);
		// Get 16 first byte[] (= KcEncrypted)
        final byte[] kc = new byte[16];
        for (int i = 0; i < kc.length; i++) {
            kc[i] = messageBytes[i];
        }
		
		// return KcEncrypted base64
        return Base64.encodeToString(kc,Base64.DEFAULT);

    }
	/**
	* Generate SecretKey
	*/
	public static byte[] generateKc() {
		
		SecureRandom random = new SecureRandom();
        byte kc[] = new byte[16];
        random.nextBytes(kc);
		
		return kc;
	}
}
