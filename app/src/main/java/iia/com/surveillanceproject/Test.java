package iia.com.surveillanceproject;

import android.util.Base64;

import org.json.JSONObject;

import javax.crypto.spec.IvParameterSpec;

import iia.com.surveillanceproject.com.Concat;
import iia.com.surveillanceproject.com.SecretKey;
import iia.com.surveillanceproject.com.GenerateIv;
import iia.com.surveillanceproject.com.asymetric.Descrypt;
import iia.com.surveillanceproject.com.asymetric.Encrypt;



/**
 * Created by Thom' on 01/05/2016.
 */
public class Test {


    public static String sendData(String login, String password) throws Exception {


        String json = "";
        JSONObject jsonObject = new JSONObject();
		
        /**
		* Build json
		*/
        jsonObject.accumulate("login", login);
        jsonObject.accumulate("password", password);
        json = jsonObject.toString();

        /**
         * Create SecretKey
         */
		
		byte kc[] = SecretKey.generateKc();

        /**
         * Create iv
         */

        IvParameterSpec ivParameterSpec = GenerateIv.generateIV();


        /**
         * Encrypt json
         */
        String jsonEncrypted = Encrypt.encryptMessage(json, kc, ivParameterSpec);

        /**
         * Encrypt SecretKey
         */
        String kcEncrypted = SecretKey.encryptKc(kc);

        /**
         * Concat kcEncrypted + jsonEncrypted
         */


        String message = Concat.ConcatEncryptedStrings(jsonEncrypted, kcEncrypted);


        /**
         * Extract SecretKey From message
         */

        String KcEncryptedFromMessage = SecretKey.ExtractKc(message);


        /**
         * Decrypt SecretKey
         */

        String KcDecrypted = SecretKey.decryptKc(kcEncrypted);
        byte[] KcDecryptedByte = Base64.decode(KcDecrypted, Base64.DEFAULT);

        /**
         * Decrypt message
         */

        String messageDecrypted = Descrypt.decryptMessage(message,KcDecryptedByte,ivParameterSpec);



		/**
		* return message encrypted ( kcEncrypted + jsonEncrypted)
		*/
        return message;
    }

}
