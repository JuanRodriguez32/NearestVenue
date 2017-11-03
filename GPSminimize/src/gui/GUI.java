package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import world.GPSMinimize;

public class GUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	private GPSMinimize gps;
	
	public GUI()
	{
		setTitle("GPSMinimize");
		setLayout(new BorderLayout());
		setSize(500,400);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		
		JButton load = new JButton("load");
		load.setActionCommand("load");
		load.addActionListener(this);
		panel.add(load);
		

		JButton find = new JButton("Find nearest venue");
		find.setActionCommand("find");
		find.addActionListener(this);
		panel.add(find);
		
		
		this.add(panel, BorderLayout.SOUTH);
		
		JLabel image = new JLabel();
		ImageIcon icon = new ImageIcon("data/preo.jpg");
		image.setIcon(icon);
		this.add(image, BorderLayout.CENTER);
		this.setBackground(Color.WHITE);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String c = arg0.getActionCommand();
		
		if(c.equals("load"))
		{
			loadVenues();
		}
		
		else if(c.equals("find"))
		{
			findNearestVenue();
		}
		
	}

	private void findNearestVenue() {
		
		try {
			gps.outPutFile("C:/Data/output.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}

	private void loadVenues() {
		File file = null;
		JFileChooser chooser = new JFileChooser("/src");
		if(chooser.showOpenDialog(this)== JFileChooser.APPROVE_OPTION)
		{
			file = chooser.getSelectedFile();
		}
		
		gps = new GPSMinimize(file);
		
		
		
		if(chooser.showOpenDialog(this)== JFileChooser.APPROVE_OPTION)
		{
			file = chooser.getSelectedFile();
		}
		
		gps.loadUsers(file);
	}
	
	public static void main(String[] args) {
		
		GUI gui = new GUI();
		gui.setVisible(true);
}

}
