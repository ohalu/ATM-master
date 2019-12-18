package bean;

public class User {

    private  int user_id;
    private String name;
    private  String sex;

    public User(int user_id, String name, String sex){
        this.name = name;
        this.sex = sex;
        this.user_id = user_id;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
