public class User extends Profile {
    private String phoneNumber;
    private char gender;
    private String birthday;
    private int points;

    public User(int id, String name, String email, String password, String phoneNumber, char gender, String birthday) {
        super(id, name, email, password);
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
        this.points = 0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getPoints() {
        return points;
    }

    public void decreasePoints(int amount) {
        if (points >= amount) {
            points -= amount;
        }
    }

    public void order(Galon galon, int quantity, String address) {
        galon.reduceStock(quantity);
        System.out.println(quantity + " buah galon " + galon.getBrand() + " akan diantar ke " + address);
    }
}
