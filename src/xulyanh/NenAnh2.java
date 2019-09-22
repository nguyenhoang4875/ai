package xulyanh;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class NenAnh2 extends JFrame {

	public static void main(String[] args) {
		new NenAnh2();
	}

	BufferedImage img;

	public NenAnh2() {
		try {
			img = ImageIO.read(new File("C:\\test3.jpg"));
		} catch (Exception e) {

		}
		
		int [][]data = new int[img.getWidth()*img.getHeight()][];
		int id=0;
		for (int y = 0; y<img.getHeight();y++)
			for (int x = 0; x<img.getWidth();x++){
				int color = img.getRGB(x, y);
				int b = color & 0xff;
				int g = (color >> 8) & 0xff;
				int r = (color >> 16) & 0xff;
				data[id] = new int[]{r,g,b};
				id++;
			}
		
		
		this.setTitle("Nen Anh");
		this.setSize(img.getWidth(), img.getHeight());
		
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}
	public void paint(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
	
}
