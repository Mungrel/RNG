package rng.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.Arrays;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import rng.logic.Logic;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtMin;
	private JTextField txtMax;
	public static MainFrame mFrame = new MainFrame();
	private JLabel label;
	private JTable table;
	private JTextField txtMin_1;
	private JTextField txtMax_1;
	private JTextField txtNumitems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("myRNG");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel RNGPanel = new JPanel();
		tabbedPane.addTab("RNG", null, RNGPanel, null);
		RNGPanel.setLayout(null);
		RNGPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mFrame.dispose();
			}
		});
		btnQuit.setBackground(Color.WHITE);
		btnQuit.setBounds(255, 270, 89, 23);
		RNGPanel.add(btnQuit);

		label = new JLabel("-1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 70));
		label.setBounds(10, 11, 334, 250);
		RNGPanel.add(label);

		JButton btnGen = new JButton("Generate");
		btnGen.setBackground(Color.WHITE);
		btnGen.setBounds(156, 270, 89, 23);
		btnGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Generate button clicked
				int min;
				int max;
				try {
					min = Integer.parseInt(txtMin.getText());
					max = Integer.parseInt(txtMax.getText());
				} catch (Exception e1) {
					Logic.displayError("Invalid min or max");
					return;
				}
				label.setText("" + Logic.getRandomNumber(min, max));
			}
		});
		RNGPanel.add(btnGen);

		txtMin = new JTextField();
		txtMin.setText("Min");
		txtMin.setColumns(10);
		txtMin.setBounds(20, 273, 32, 20);
		txtMin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMin.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMin.getText().trim().isEmpty()) {
					txtMin.setText("Min");
				}
			}
		});
		RNGPanel.add(txtMin);

		txtMax = new JTextField();
		txtMax.setText("Max");
		txtMax.setColumns(10);
		txtMax.setBounds(62, 273, 32, 20);
		txtMax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMax.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMax.getText().trim().isEmpty()) {
					txtMax.setText("Max");
				}
			}
		});
		RNGPanel.add(txtMax);

		JPanel ListPanel = new JPanel();
		tabbedPane.addTab("List", null, ListPanel, null);
		ListPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 130, 284);
		ListPanel.add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		String[] titles = { "Number", "Values" };
		table.setModel(new DefaultTableModel(null, titles));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		JButton btnQuit_1 = new JButton("Quit");
		btnQuit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.mFrame.dispose();
			}
		});
		btnQuit_1.setBackground(Color.WHITE);
		btnQuit_1.setBounds(255, 272, 89, 23);
		ListPanel.add(btnQuit_1);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int min;
				int max;
				int numItems;
				try {
					min = Integer.parseInt(txtMin_1.getText());
					max = Integer.parseInt(txtMax_1.getText());
					numItems = Integer.parseInt(txtNumitems.getText());
				} catch (Exception e1) {
					Logic.displayError("Invalid settings");
					return;
				}
				if (max < min) {
					Logic.displayError("Invalid settings");
				} else {
					table.setModel(Logic.getRandomListModel(min, max, numItems));
				}
			}
		});
		btnGenerate.setBackground(Color.WHITE);
		btnGenerate.setBounds(156, 272, 89, 23);
		ListPanel.add(btnGenerate);

		txtMin_1 = new JTextField();
		txtMin_1.setText("Min");
		txtMin_1.setBounds(150, 210, 86, 20);
		txtMin_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMin_1.getText().trim().equals("Min")) {
					txtMin_1.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMin_1.getText().trim().isEmpty()) {
					txtMin_1.setText("Min");
				}
			}
		});
		ListPanel.add(txtMin_1);
		txtMin_1.setColumns(10);

		txtMax_1 = new JTextField();
		txtMax_1.setText("Max");
		txtMax_1.setBounds(255, 210, 86, 20);
		txtMax_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMax_1.getText().trim().equals("Max")) {
					txtMax_1.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMax_1.getText().trim().isEmpty()) {
					txtMax_1.setText("Max");
				}
			}
		});
		ListPanel.add(txtMax_1);
		txtMax_1.setColumns(10);

		txtNumitems = new JTextField();
		txtNumitems.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtNumitems.getText().trim().equals("NumItems")) {
					txtNumitems.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNumitems.getText().trim().isEmpty()) {
					txtNumitems.setText("NumItems");
				}
			}
		});
		txtNumitems.setText("NumItems");
		txtNumitems.setBounds(150, 241, 86, 20);
		ListPanel.add(txtNumitems);
		txtNumitems.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Save button clicked
				JFileChooser jfc = new JFileChooser();
				jfc.showSaveDialog(MainFrame.mFrame);
				File f = jfc.getSelectedFile();
				if (f != null){
				Logic.writeToFile(Logic.getTableValues(table), f.getName(), true);
			}}
		});
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(255, 238, 89, 23);
		ListPanel.add(btnSave);
	}
}
