package iia.com.surveillanceproject.com.asymetric;

import android.util.Base64;

import java.security.PrivateKey;

import javax.crypto.spec.IvParameterSpec;

/**
 * Created by Thom' on 26/01/2016.
 */
public class Descrypt {


	/**
	* Decrypt message AES 128
	*/
    public static String decryptMessage(String input, byte kc[],IvParameterSpec iv) {

        try {
			
			// message to byte[]
            final byte[] message = Base64.decode(input, Base64.DEFAULT);
			// extract json from message
            final byte[] encryptedJson = new byte[message.length - 256];
            int j = 0;
            for (int i = 256; i < message.length; i++) {

                encryptedJson[j] = message[i];
                j++;
            }

			
            final byte[] cipherText = CipherData.cipherDecryptAES(encryptedJson, kc,iv);



            return new String(cipherText,"UTF-8");

        } catch (Exception e) {

            System.out.println(e);
        }
        return null;
    }

}
