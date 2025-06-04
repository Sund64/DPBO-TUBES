package halreward.tubes;

public class User {
	 private int id;
	    private String name;
	    private String email;
	    private int points;

	    public User(int id, String name, String email, int points) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.points = points;
	    }

	    public int getPoints() {
	        return points;
	    }

	    public void decreasePoints(int amount) {
	        points -= amount;
	    }

	    public void addPoints(int amount) {
	        points += amount;
	    }

	    public String getName() {
	        return name;
	    }
	    public void inisialisasiUser() {
	    	
	    }
	}

