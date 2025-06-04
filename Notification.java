package halreward.tubes;

public class Notification {
	private User recipient;
    private String message;

    public Notification(User recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public void send() {
        System.out.println("Notifikasi kepada " + recipient.getName() + ": " + message);
    }
}

