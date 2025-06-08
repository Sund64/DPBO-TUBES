package com.example;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Pilihan metode pembayaran
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pilih Metode Pembayaran");
        alert.setHeaderText("Metode Pembayaran");
        alert.setContentText("Pilih metode pembayaran yang diinginkan:");

        ButtonType cashBtn = new ButtonType("Cash");
        ButtonType qrisBtn = new ButtonType("QRIS");
        alert.getButtonTypes().setAll(cashBtn, qrisBtn);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == qrisBtn) {
            // Tampilkan halaman QRIS dengan tombol konfirmasi
            QRIS qris = new QRIS(150000, 12345);
            VBox root = qris.getQRVBox();
            Button konfirmasiBtn = new Button("Konfirmasi Pembayaran");
            root.getChildren().add(konfirmasiBtn);

            Scene scene = new Scene(root, 350, 300);
            Stage qrStage = new Stage();
            qrStage.setTitle("Pembayaran QRIS");
            qrStage.setScene(scene);
            qrStage.show();

            konfirmasiBtn.setOnAction(e -> {
                qrStage.close();
                Notification.showInfo(
                    "Pembayaran Berhasil",
                    "Sukses",
                    "Pembayaran QRIS berhasil dikonfirmasi!"
                );
            });
        } else if (result.isPresent() && result.get() == cashBtn) {
            Notification.showInfo(
                "Pembayaran Tunai",
                "Cash",
                "Silakan bayar secara tunai kepada kasir."
            );
        }
    }

    public static void main(String[] args) {
        launch();
    }

}