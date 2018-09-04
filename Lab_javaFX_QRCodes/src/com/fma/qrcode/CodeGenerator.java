package com.fma.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class CodeGenerator {
	// @formatter:off
	/**
	 * Le padding Y n'est pas pris en compte pour les codes barres 2D qui pour la
	 * grande majorit&eacute; sont trac&eacute;s sur un carr&eacute;. Seul MaxiCode est dans un rectangle
	 * mais il n'est support&eacute; qu'en lecture dans la library zXing.<br><br>
	 * 
	 * <a href="https://en.wikipedia.org/wiki/Aztec_Code">AZTEC</a><br>
	 * <a href="https://en.wikipedia.org/wiki/Codabar">CODABAR</a><br>
	 * <a href="https://fr.wikipedia.org/wiki/Code_39">CODE_39</a><br>
	 * <a href="https://fr.wikipedia.org/wiki/Code_93">CODE_93</a><br>
	 * <a href="https://fr.wikipedia.org/wiki/Code_128">CODE_128</a><br>
	 * <a href="https://fr.wikipedia.org/wiki/Datamatrix">DATA_MATRIX</a><br>
	 * <a href="https://fr.wikipedia.org/wiki/EAN_8">EAN_8</a><br>
	 * <a href="https://fr.wikipedia.org/wiki/EAN_13">EAN_13</a><br>
	 * <a href="https://en.wikipedia.org/wiki/ITF-14">ITF</a> : Nombre de chiffre doit &ecirc;tre paire<br>
	 * <a href="https://fr.wikipedia.org/wiki/MaxiCode">MAXICODE</a> : No encoder available for format MAXICODE avec MultiFormatWriter<br>
	 * <a href="https://fr.wikipedia.org/wiki/PDF-417">PDF_417</a><br>
	 * <a href="https://fr.wikipedia.org/wiki/Code_QR">QR_CODE</a><br>
	 * <a href="http://www.technoriversoft.com/RSS14Barcode.html">RSS_14</a> : No encoder available for format MAXICODE avec MultiFormatWriter<br>
	 * RSS_EXPANDED : No encoder available for format MAXICODE avec MultiFormatWriter<br>
	 * UPC_A<br>
	 * UPC_E<br>
	 * UPC_EAN_EXTENSION : No encoder available for format MAXICODE avec MultiFormatWriter<br>
	 * 
	 * @param msg
	 * @param width
	 * @param height
	 * @param bfm
	 * @param filetype
	 * @param padding
	 * @return
	 */
	// @formatter:on
	public Image multiFormatCodeGenerator(String msg, int width, int height, BarcodeFormat bfm, String filetype, int paddingX,
			int paddingY) {
		MultiFormatWriter barcodeWriter = new MultiFormatWriter();
		BufferedImage bi = null;
		try {
			int targetSizeX = width - (paddingX * 2);
			int targetSizeY = height - (paddingY * 2);
			int targetSizeSquare = (width > height) ? targetSizeY : targetSizeX;
			int targetPaddingSquare = (width > height) ? paddingY : paddingX;

			System.out.println("-> " + bfm.name() + "; targetSizeX=" + targetSizeX + ";targetSizeY=" + targetSizeY
					+ "; width=" + width + "; height=" + height + "; targetSizeSquare=" + targetSizeSquare);

			bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			bi.createGraphics();
			Graphics2D gfx = (Graphics2D) bi.getGraphics();
			gfx.setColor(Color.WHITE);
			gfx.fillRect(0, 0, width, height);
			gfx.setColor(Color.BLACK);

			BitMatrix bm = null;
			switch (bfm.name()) {
			case "AZTEC":
			case "DATA_MATRIX":
			case "PDF_417":
			case "QR_CODE":
				bm = barcodeWriter.encode(msg, bfm, targetSizeSquare, targetSizeSquare);
				for (int i = 0; i < targetSizeSquare; i++) {
					for (int j = 0; j < targetSizeSquare; j++) {
						if (bm.get(i, j)) {
							gfx.fillRect(i + targetPaddingSquare, j + targetPaddingSquare, 1, 1);
						}
					}
				}
				break;
			case "CODABAR":
			case "CODE_39":
			case "CODE_93":
			case "CODE_128":
			case "EAN_8":
			case "EAN_13":
			case "ITF":
			case "UPC_A":
			case "UPC_E":
				bm = barcodeWriter.encode(msg, bfm, targetSizeX, 1);
				for (int i = 0; i < targetSizeX; i++) {
					if (bm.get(i, 0)) {
						for (int j = 0; j < targetSizeY; j++) {
							gfx.fillRect((i + paddingX), (j + paddingY), 1, 1);
						}
					}
				}
				break;
			case "RSS_14":
			case "RSS_EXPANDED":
			case "MAXICODE":
			case "UPC_EAN_EXTENSION":
				break;

			}

		} catch (WriterException ex) {
			System.out.println(ex.getMessage() + ex.getStackTrace());
			// Logger.getLogger(qrcodeController.class.getName()).log(Level.SEVERE, null,
			// ex);
		}
		return SwingFXUtils.toFXImage(bi, null);
	}
}
