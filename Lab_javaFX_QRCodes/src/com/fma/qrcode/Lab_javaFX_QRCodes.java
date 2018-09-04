package com.fma.qrcode;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lab_javaFX_QRCodes extends Application {

	// https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
	private static String[] listeCle = { "file.separator", "java.class.path", "java.home", "java.vendor",
			"java.vendor.url", "java.version", "line.separator", "os.arch", "os.name", "os.version", "path.separator",
			"user.dir", "user.home", "user.name" };

	@Override
	public void start(Stage primaryStage) {

		Parent vueLancementAutonome;
		try {
			vueLancementAutonome = FXMLLoader.load(getClass().getResource("qrcode.fxml"));
			Scene sceneLancementAutonome = new Scene(vueLancementAutonome);
			primaryStage.setScene(sceneLancementAutonome);
			primaryStage.setTitle("QRCode");
			primaryStage.setMinWidth(288);
			primaryStage.setMinHeight(480);
			

			primaryStage.show();
		} catch (IOException e) { e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (String l : listeCle) {
			System.out.println(l + " : " + System.getProperties().get(l));
		}
		launch(args);
	}
}
