package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pembayaran {
    protected int price;
    protected String metode;

    public Pembayaran(int price, String metode) {
        this.price = price;
        this.metode = metode;
    }

    public int getPrice() {
        return price;
    }

    public String getMetode() {
        return metode;
    }

    public void printInfo() {
        System.out.println("Metode: " + metode + ", Harga: " + price);
    }

    public static void showPage(Stage stage, String metode, int price) {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center; -fx-padding: 40;");
        Label label = new Label("Pembayaran dengan metode " + metode + " sebesar " + price + " diproses.");
        root.getChildren().add(label);

        Scene scene = new Scene(root, 350, 150);
        stage.setTitle("Halaman Pembayaran");
        stage.setScene(scene);
        stage.show();
    }
}
