package iia.com.surveillanceproject.com;

import android.util.Base64;

/**
 * Created by Thom' on 01/05/2016.
 */
public class Concat {

    public static String ConcatEncryptedStrings(String json, String kc) {

        byte[] concat = new byte[Base64.decode(kc, Base64.NO_WRAP).length + Base64.decode(json, Base64.NO_WRAP).length];

        for (int i = 0; i < Base64.decode(kc, Base64.NO_WRAP).length; i++) {
            concat[i] = Base64.decode(kc, Base64.NO_WRAP)[i];
        }
        int j = 0;
        try {
            for (int i = Base64.decode(kc, Base64.NO_WRAP).length; i < concat.length; i++) {
                concat[i] = Base64.decode(json, Base64.NO_WRAP)[j];
                j++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        String message = Base64.encodeToString(concat, Base64.DEFAULT);

        return message;
    }
}
