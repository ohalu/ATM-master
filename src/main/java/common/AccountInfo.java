package common;

/**
 * 用户登录信息
 * */
public class AccountInfo {
    private String cardNumber;
    private String password;
    private int status;
    private static AccountInfo info = new AccountInfo();
    public static void setAccountInfo(String cardNumber){
        info.cardNumber = cardNumber;
    }
    public static AccountInfo getAccountInfo(){
        return info;
    };
    public static void clear(){
        info = null;
    }

    public static String getCardNumber() {
        return info.cardNumber;
    }

    public static String getPassword() {
        return info.password;
    }

    public static void setPassword (String password) {
        info.password = password;
    }

    public static void setStatus(int status) {
        info.status = status;
    }
}
