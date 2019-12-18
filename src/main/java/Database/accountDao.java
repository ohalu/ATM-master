package Database;

import common.AccountInfo;
import common.TransferInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class accountDao {

    //检查账号是否存在
    public int cardIsExist(String card_number) {
        //把账号信息拿出来
        String sql = "select * from account where card_number=" + card_number;
        DBUtil db = new DBUtil();
        ResultSet rs = db.rsExecute(sql);
        int status = -1;
        try {
            if (rs!= null && rs.next())
                status = rs.getInt(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return status;
    }

    public boolean checkPwd(String password) {
        //把账号信息拿出来
        String sql = "select * from account where card_number=" + AccountInfo.getAccountInfo().getCardNumber() + " and password = " + password;
        DBUtil db = new DBUtil();
        ResultSet rs = db.rsExecute(sql);
        boolean find = false;
        try {
            if (rs != null && rs.next())
                find = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return find;
    }

    public double queryBalance() {
        String sql = "select * from account where card_number=" + AccountInfo.getAccountInfo().getCardNumber();
        DBUtil db = new DBUtil();
        ResultSet rs = db.rsExecute(sql);
        try {
            if (rs != null && rs.next())
                return rs.getDouble(3);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        throw new IllegalArgumentException();
    }

    /**
     * 取钱操作，返回当前余额
     */
    public double withdrawMoney(int money) {
        if (money == 0 || money % 100 != 0) {

            return -1.0;
        }
        double current_money = queryBalance();
        if (money <= current_money) {
            double now_money = current_money - money;
            String sql = "update account set balance = " + now_money + " where card_number=" + AccountInfo.getAccountInfo().getCardNumber();
            DBUtil db = new DBUtil();
            int changeRows = db.intExecute(sql);
            db.close();
            if (changeRows != 0)
                return now_money;

        } else {
            return -2;
        }

        return current_money;

    }

    /**
     * 存钱操作，返回当前余额
     */
    public double saveMoney(int money) {
        if (money == 0 || money % 100 != 0) {

            return -1.0;
        }
        double current_money = queryBalance();
        double now_money = current_money + money;
        String sql = "update account set balance = " + now_money + " where card_number=" + AccountInfo.getAccountInfo().getCardNumber();
        DBUtil db = new DBUtil();
        int changeRows = db.intExecute(sql);
        db.close();
        return now_money;

    }

    public TransferInfo queryAccount(String cardNumber) {
        //把账号信息拿出来
        String sql = "select  name,status from account,person_info where person_info.user_id = account.user_id and card_number= " + cardNumber;
        DBUtil db = new DBUtil();
        ResultSet rs = db.rsExecute(sql);
        TransferInfo account = null;
        try {
            if (rs != null && rs.next())
                account = new TransferInfo(cardNumber, rs.getString(1),rs.getInt(2));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return account;
    }

    public double transferMoney(TransferInfo info) {
        double transferMoney = info.getMoney();
        if (transferMoney <= 0) {
            return -1.0;
        }
        double current_money = queryBalance();
        double now_money;
        if (transferMoney <= current_money) {
            now_money = current_money - transferMoney;
            String sql1 = "update account set balance = " + now_money + " where card_number=" + AccountInfo.getAccountInfo().getCardNumber();
            String sql2 = "update account set balance = balance+" + transferMoney + " where card_number=" + info.getCardNumber();
            DBUtil db = new DBUtil();
            db.intExecute(sql1);
            db.intExecute(sql2);
            db.close();
        } else {
            return -2.0;
        }

        return now_money;
    }

    public void modifyPwd(String new_pwd) {
        String sql = "update account set password = " + new_pwd + " where card_number=" + AccountInfo.getAccountInfo().getCardNumber();
        DBUtil db = new DBUtil();
        db.intExecute(sql);
        db.close();
    }

    public void lockCard() {
        String sql = "update account set status = 1 where card_number=" + AccountInfo.getAccountInfo().getCardNumber();
        DBUtil db = new DBUtil();
        db.intExecute(sql);
        db.close();
    }
}
