package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QRIS extends Pembayaran {
    private int qrisId;

    public QRIS(int price, int qrisId) {
        super(price, "QRIS");
        this.qrisId = qrisId;
    }

    public int getQrisId() {
        return qrisId;
    }

    @Override
    public void printInfo() {
        System.out.println("Pembayaran QRIS sebesar " + price + ", QRIS ID: " + qrisId);
    }

    public VBox getQRVBox() {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center; -fx-padding: 40;");
        Label label = new Label("Scan QR Code untuk membayar " + price + "\nQRIS ID: " + qrisId);

        // QR code dengan data unik (misal: qrisId dan price)
        String qrData = "QRISID:" + qrisId + "|PRICE:" + price;
        Image qrImage = new Image("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + qrData);
        ImageView qrView = new ImageView(qrImage);

        root.getChildren().addAll(label, qrView);
        return root;
    }

    public void tampilkanQR(Stage stage) {
        VBox root = getQRVBox();
        Scene scene = new Scene(root, 350, 250);
        stage.setTitle("Pembayaran QRIS");
        stage.setScene(scene);
        stage.show();
    }

    public boolean pengecekanPembayaran() {
        // Simulasi pengecekan pembayaran (misal: selalu sukses)
        System.out.println("Pembayaran QRIS dengan ID " + qrisId + " telah diterima.");
        return true;
    }
}
