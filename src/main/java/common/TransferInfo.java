package common;

public class TransferInfo {
    private String cardNumber;
    private String name;
    private double money;
    private int status;
    public TransferInfo(String cardNumber, String name, int status){
        this.cardNumber = cardNumber;
        this.name = name;
        this.status = status;
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
