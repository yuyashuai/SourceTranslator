import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {
    public static String getBaiduMD5(String p, String randomString) {
        String flexString = Config.BD_APP_ID + p + randomString + Config.BD_APP_SEC;
        String mdString = getMD5(flexString);
        return mdString;
    }
    public static String getYouDaoMD5(String p, String randomString) {
        String flexString = Config.YD_APP_ID + p + randomString + Config.YD_APP_KEY;
        String mdString = md5(flexString);
        return mdString;
    }

    //默认不需要samples
    public static String getBingUrl(String word) {
        return getBingUrl(word, false);
    }

    public static String getBingUrl(String word, boolean needSample) {
        if (needSample) {
            return Config.BING_URL + "?Word=" + word;
        } else {
            return Config.BING_URL + "?Word=" + word + "&Samples=false";
        }
    }

    /**
     * 生成32位MD5摘要
     * @param string
     * @return
     */
    public static String md5(String string) {
        if(string == null){
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};

        try{
            byte[] btInput = string.getBytes("utf-8");
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            return null;
        }
    }


    public static String getMD5(String inStr) {

        MessageDigest md5 = null;

        try {

            md5 = MessageDigest.getInstance("MD5");

        } catch (Exception e) {

            System.out.println(e.toString());
            e.printStackTrace();

            return "";
        }

        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {

            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();

    }

}
