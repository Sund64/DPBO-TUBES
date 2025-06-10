
import java.util.ArrayList;
import java.util.List;


class ChatChannel {
    private int id;
    private Admin admin;
    private User user;
    private List<Chat> chats;

    public ChatChannel(int id, Admin admin, User user) {
        this.id = id;
        this.admin = admin;
        this.user = user;
        this.chats = new ArrayList<>();
    }

    public void sendMessage(String message, Profile sender) {
        Chat chat = new Chat(message, sender);
        chats.add(chat);
    }

    public void printChatHistory() {
        System.out.println("\nChat History - Channel ID: " + id);
        for (Chat chat : chats) {
            chat.printInfo();
        }
    }
}
