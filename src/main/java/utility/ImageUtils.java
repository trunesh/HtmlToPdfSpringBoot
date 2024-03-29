package utility;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

public class ImageUtils {

	public static String BASE64_IMAGE_JPEG = "jpeg";
	public static String BASE64_IMAGE_PNG = "png";
	public static String BASE64_IMAGE_GIF = "gif";

	public static String convertImageToBase64(String imagePath) {
		String imageFormat = BASE64_IMAGE_JPEG;

		 if (imagePath.toLowerCase().endsWith(".gif")) {
			imageFormat = BASE64_IMAGE_GIF;
		}else if (imagePath.toLowerCase().endsWith(".png")) {
			imageFormat = BASE64_IMAGE_PNG;
		}
		
		return convertImageToBase64(imagePath,imageFormat);
	}

	private static String convertImageToBase64(String imagePath, String imageFormat) {
		// TODO Auto-generated method stub
		System.out.print("imagePath: "+imagePath + "\n imageFormat: " + imageFormat);
		URL fr = ImageUtils.class.getResource("/templates/" +imagePath);
		
		byte[] b = new byte[0];
		try {
			b = IOUtils.toByteArray(fr.openStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
		String imgBase64=Base64.getEncoder().encodeToString(b);
		return "data:image/" + imageFormat + ";base64," + imgBase64;
	}

	
	
	public static String convertImageToBase64(byte[] byteArray, String imageFormat) {
//		// TODO Auto-generated method stub
//		System.out.print("imagePath: "+imagePath + "\n imageFormat: " + imageFormat);
//		URL fr = ImageUtils.class.getResource("/templates/" +imagePath);
//		
//		byte[] byteArray = new byte[0];
//		try {
//			byteArray = IOUtils.toByteArray(fr.openStream());
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		String imgBase64=Base64.getEncoder().encodeToString(byteArray);
		return "data:image/" + imageFormat + ";base64," + imgBase64;
	}
}
