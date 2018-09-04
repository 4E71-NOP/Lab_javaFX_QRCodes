package com.fma.qrcode;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import com.google.zxing.BarcodeFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

public class qrcodeController {
	private ObservableList<String> listeMenu = FXCollections.observableArrayList("AZTEC", "CODABAR", "CODE_39",
			"CODE_93", "CODE_128", "DATA_MATRIX", "EAN_8", "EAN_13", "ITF", "MAXICODE", "PDF_417", "QR_CODE", "RSS_14",
			"RSS_EXPANDED", "UPC_A", "UPC_E", "UPC_EAN_EXTENSION");

	// @formatter:off
	@FXML 	private ChoiceBox<String> ChoiceBoxType;
	@FXML	private TextField TextFieldPaddingX;
	@FXML	private TextField TextFieldPaddingY;
	@FXML	private TextField TextFieldMsg;
	@FXML	private Button ButtonUpdate;
	@FXML	private ImageView ImageQRCode1;
	// @formatter:on

	@FXML
	public void ButtonUpdateAction(ActionEvent event) {
		updateQRCodeImage();
	}

	private void ChoiceBoxType() {

		String texte = "Faust MDA le plus beau des meilleurs de la creme de la creme de le fonkey en diable total. Sans aucun doute!!!§";
		// System.out.println(ChoiceBoxType.getSelectionModel().getSelectedItem());

		// @formatter:off
		switch (ChoiceBoxType.getSelectionModel().getSelectedItem()) {
		case "AZTEC":				TextFieldMsg.setText(texte);					break;
		case "CODABAR":				TextFieldMsg.setText("13216541");				break;
		case "CODE_39":				TextFieldMsg.setText("ABEYZ0129 -$%./+");		break;
		case "CODE_93":				TextFieldMsg.setText("A9-. *$/+%");				break;
		case "CODE_128":			TextFieldMsg.setText("123456789ABCDEFGHIJ");	break;
		case "DATA_MATRIX":			TextFieldMsg.setText(texte);					break;
		case "EAN_8":				TextFieldMsg.setText("12345670");				break;
		case "EAN_13":				TextFieldMsg.setText("1234567891019");			break;
		case "ITF":					TextFieldMsg.setText("1234567891");				break;
		case "MAXICODE":			TextFieldMsg.setText(texte);					break;
		case "PDF_417":				TextFieldMsg.setText(texte);					break;
		case "QR_CODE":				TextFieldMsg.setText(texte);					break;
		case "RSS_14":				TextFieldMsg.setText(texte);					break;
		case "RSS_EXPANDED":		TextFieldMsg.setText(texte);					break;
		case "UPC_A":				TextFieldMsg.setText("123456789104");			break;
		case "UPC_E":				TextFieldMsg.setText("12345670");				break;
		case "UPC_EAN_EXTENSION":	TextFieldMsg.setText("1234567891019");			break;
		}
		// @formatter:on
	}

	private void updateQRCodeImage() {
		CodeGenerator cg1 = new CodeGenerator();
		BarcodeFormat bfm = null;

		// @formatter:off
		switch (ChoiceBoxType.getSelectionModel().getSelectedItem()) {
		case "AZTEC":				bfm = BarcodeFormat.AZTEC;				break;
		case "CODABAR":				bfm = BarcodeFormat.CODABAR;			break;
		case "CODE_39":				bfm	= BarcodeFormat.CODE_39;			break;
		case "CODE_93":				bfm = BarcodeFormat.CODE_93;			break;
		case "CODE_128":			bfm = BarcodeFormat.CODE_128;			break;
		case "DATA_MATRIX":			bfm = BarcodeFormat.DATA_MATRIX;		break;
		case "EAN_8":				bfm = BarcodeFormat.EAN_8;				break;
		case "EAN_13":				bfm = BarcodeFormat.EAN_13;				break;
		case "ITF":					bfm = BarcodeFormat.ITF;				break;
		case "MAXICODE":			bfm = BarcodeFormat.MAXICODE;			break;
		case "PDF_417":				bfm = BarcodeFormat.PDF_417;			break;
		case "QR_CODE":				bfm = BarcodeFormat.QR_CODE;			break;
		case "RSS_14":				bfm = BarcodeFormat.RSS_14;				break;
		case "RSS_EXPANDED":		bfm = BarcodeFormat.RSS_EXPANDED;		break;
		case "UPC_A":				bfm = BarcodeFormat.UPC_A;				break;
		case "UPC_E":				bfm = BarcodeFormat.UPC_E;				break;
		case "UPC_EAN_EXTENSION":	bfm = BarcodeFormat.UPC_EAN_EXTENSION;	break;
		}
		// @formatter:on
		ImageQRCode1.setImage(cg1.multiFormatCodeGenerator(TextFieldMsg.getText(), (int) ImageQRCode1.getFitWidth(),
				(int) ImageQRCode1.getFitHeight(), bfm, "png", Integer.parseInt(TextFieldPaddingX.getText()),
				Integer.parseInt(TextFieldPaddingY.getText())));

	}

	@FXML
	public void initialize() {
		ChoiceBoxType.setItems(listeMenu);
		ChoiceBoxType.getSelectionModel().select(0);
		TextFieldPaddingX.setText("16");
		TextFieldPaddingY.setText("64");

		ChoiceBoxType.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println(
					getClass().getName() + "FormulaireMenuSelection passe de : " + oldValue + " à " + newValue);
			ChoiceBoxType.getSelectionModel().select(listeMenu.get((int) newValue));
			ChoiceBoxType();
		});

	}

}
