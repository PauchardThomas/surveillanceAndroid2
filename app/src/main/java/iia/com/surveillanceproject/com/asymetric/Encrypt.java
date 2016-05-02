package iia.com.surveillanceproject.com.asymetric;

import android.util.Base64;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Thom' on 20/01/2016.
 */
public class Encrypt {

	
	/**
	* Encrypt message AES 128
	*/
    public static String encryptMessage(String text, byte kc[],IvParameterSpec iv) {

        try {

			// Message to byte[]
            final byte[] inputText = text.getBytes("UTF-8");
			// Encrypt message
            final byte[] cipherText = CipherData.cipherEncryptAES(inputText, kc,iv);
			// MessageEncrypted to Base64
            String message = Base64.encodeToString(cipherText,Base64.DEFAULT);
			//return MessageEncrypted Base64
            return message ;

        } catch (Exception e) {

            System.out.println(e);
        }
        return null;
    }


}
