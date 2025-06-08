public class User extends Profile implements Identity {
    private int phoneNumber;
    private char gender;
    private String birthday;
    
    public User(int id, String name, String email, String password, int phoneNumber, char gender, String birthday) {
        super(id, name, email, password);
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
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
    
    public void order(Galon galon, int quantity, String address) {
        galon.reduceStock(quantity);
        System.out.println(quantity + " buah galon " + galon.getBrand() + " akan diantar ke " + address);
    }
}
