package rng.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import rng.frames.MainFrame;

public class Logic {

	public static int getRandomNumber(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}

	public static DefaultTableModel getRandomListModel(int min, int max,
			int numItems) {
		String[][] o = new String[numItems][2];
		for (int i = 0; i < numItems; i++) {
			o[i][0] = "" + (i + 1);
			o[i][1] = "" + getRandomNumber(min, max);
		}
		String[] s = { "Number", "Value" };
		DefaultTableModel dtm = new DefaultTableModel(o, s);
		return dtm;
	}

	public static void displayError(String msg) {
		JOptionPane.showMessageDialog(MainFrame.mFrame, msg, "Error!",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void writeToFile(String s, String fileName, boolean append){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(new File(fileName), append)); 
		} catch (Exception e) {
			displayError("Unable to write to file: "+fileName);
		}
		pw.write(s);
		pw.close();
	}
	
	public static String getTableValues(JTable t){
		String s = "";
		for (int i = 0; i < t.getRowCount(); i++){
			s += (String) t.getValueAt(i, 1);
			s += "\n";
		}
		return s;
	}
}
