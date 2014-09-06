package tk.bartbart333.citybuilder;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 * This class renders text in 2D.
 * @author Barthold
 */
public class FontRenderer {
	
	private static TrueTypeFont font;
	
	public static void init(){
		Font awt = new Font("Times", Font.PLAIN, 14);
		font = new TrueTypeFont(awt, true);
	}
	
	/**
	 * Returns the width of the given string.
	 * @author Barthold
	 * @param text String that you want the width of.
	 * @return int The width of the entered text.
	 */
	public static int getWidth(String text){
		return font.getWidth(text);
	}
	
	/**
	 * Returns the height of the font
	 * @author Barthold
	 * @return int The height of the font.
	 */
	public static int getHeight(){
		return font.getHeight();
	}
	
	/**
	 * Draws text on the screen at the given position in the given color.
	 * @author Barthold
	 * @param x The x position of the text.
	 * @param y The y position of the text.
	 * @param text The text to draw.
	 * @param color The color to draw the text in.
	 */
	public static void drawString(int x, int y, String text, Color color){
		font.drawString(x, y, text, color);
	}
}