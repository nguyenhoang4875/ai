package xulyanh;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NenAnhR extends JFrame {

	public static void main(String[] args) {
		new NenAnhR();
	}

	BufferedImage img;

	public NenAnhR() {
		try {
			img = ImageIO.read(new File("/home/hoang/Pictures/lamp_outlet_idea_electricity_120422_1920x1080.jpg"));
		} catch (Exception e) {

		}
		List<int []> ld = new ArrayList<>();
		int id=0;
		for (int y = 0; y<img.getHeight();y+=5)
			for (int x = 0; x<img.getWidth();x+=5){
				int color = img.getRGB(x, y);
				System.out.println(color);
				int b = color & 0xff;
				int g = (color >> 8) & 0xff;
				int r = (color >> 16) & 0xff;
				System.out.println("b g r: "+ b+"  "+g+"  "+r);
				ld.add(new int[]{r,g,b});
				id++;
			}
		int [][]data = new int[id][];
		for (int i=0;i<id;i++)
			data[i]=ld.get(i);
		
		Kmeans km = new Kmeans(data,10);
		
		int i = 0;
		for (int y = 0; y<img.getHeight();y++)
			for (int x = 0; x<img.getWidth();x++){
				int color = img.getRGB(x, y);
				int b = color & 0xff;
				int g = (color >> 8) & 0xff;
				int r = (color >> 16) & 0xff;
				
				int index = km.Chia(new int[]{r,g,b});
				int nr = km.c[index][0];
				int ng = km.c[index][1];
				int nb = km.c[index][2];
				int ncolor = nb + (ng<<8) + (nr<<16);
				img.setRGB(x, y, ncolor);
				i++;
			}
		
		this.setTitle("Nen Anh Repetition");
		this.setSize(img.getWidth(), img.getHeight());
		
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}
	public void paint(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
	
}
