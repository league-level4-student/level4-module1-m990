package _05_Enum_Stuff;

import javax.swing.JOptionPane;

public class _00_Horoscope {
	// 1. Create an enum in a separate file called Zodiac that contains a category for
	//    all 12 zodiac signs.
	
	// 2. Write a method that takes in a Zodiac enum object and uses a JOPtionPane to display
	//    a different horoscope based on the Zodiac's state.
	
	// 3. Make a main method to test your method
	public static void main(String[] args) {
		horoscope(Zodiac.ARIES);
	}
	
	static void horoscope(Zodiac e) {
		switch (e) {
		case ARIES:
			JOptionPane.showMessageDialog(null, "A good season or something like that");
			break;
		
		case TAURUS:
			JOptionPane.showMessageDialog(null, "Peacefull and methodical");
			break;
		case GEMINI:
			JOptionPane.showMessageDialog(null, "Something about ideas and concepts");
			break;
		case CANCER:
			JOptionPane.showMessageDialog(null, "Good reason");
			break;
		case LEO:
			JOptionPane.showMessageDialog(null, "Proud, regal, relaxed, incharge");
			break;
		case VIRGO:
			JOptionPane.showMessageDialog(null, "Cool and calm");
			break;
		case LIBRA:
			JOptionPane.showMessageDialog(null, "Balance between work and lives");
			break;
		case SCORPIO:
			JOptionPane.showMessageDialog(null, "Emotional, great secret keepers");
			break;
		case SAGITTARIUS:
			JOptionPane.showMessageDialog(null, "Traveling, meeting new people, learning things, etc");
			break;
		case CAPRICORN:
			JOptionPane.showMessageDialog(null, "Goal oriented and driven to succeed");
			break;
		case AQUARIUS:
			JOptionPane.showMessageDialog(null, "Generious with time and resources");
			break;
		case PISCES:
			JOptionPane.showMessageDialog(null, "Day dreamers");
		}
	}
	
}
