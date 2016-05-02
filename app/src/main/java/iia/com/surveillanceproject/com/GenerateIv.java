package iia.com.surveillanceproject.com;

import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;

/**
 * Created by TPauchard on 02/05/2016.
 */
public class GenerateIv {

    public static IvParameterSpec generateIV() {

        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        return ivParameterSpec;
    }

}
