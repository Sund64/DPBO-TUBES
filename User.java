public class User extends Profile implements Identity {
    private int phoneNumber;
    private char gender;
    private String birthday;
    private int points; // Tambahan untuk sistem reward

    public User(int id, String name, String email, String password, int phoneNumber, char gender, String birthday) {
        super(id, name, email, password);
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
        this.points = 0;
    }

    // Konstruktor tambahan untuk Reward demo
    public User(int id, String name, String email, int points) {
        super(id, name, email, "");
        this.phoneNumber = 0;
        this.gender = 'U';
        this.birthday = "";
        this.points = points;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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

    // Reward system
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
