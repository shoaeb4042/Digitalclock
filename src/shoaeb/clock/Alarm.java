package shoaeb.clock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class Alarm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField hour;
	private JTextField min;
	private JTextField pm;
	private JLabel hh2;
	private JLabel mm;
	private JLabel mm2;
	private JLabel am;
	private JButton btnNewButton;
	private JTextField mes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Alarm dialog = new Alarm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Alarm() {
		setTitle("Set Alarm");
		setType(Type.UTILITY);
		setBounds(550 ,180, 376, 184);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		hour = new JTextField();
		hour.setFont(new Font("Tahoma", Font.BOLD, 16));
		hour.setHorizontalAlignment(SwingConstants.CENTER);
		hour.setText("01");
		hour.setEditable(false);
		hour.setBounds(28, 24, 61, 29);
		contentPanel.add(hour);
		hour.setColumns(10);
		
		min = new JTextField();
		min.setHorizontalAlignment(SwingConstants.CENTER);
		min.setText("00");
		min.setFont(new Font("Tahoma", Font.BOLD, 15));
		min.setEditable(false);
		min.setColumns(10);
		min.setBounds(137, 24, 66, 29);
		contentPanel.add(min);
		
		pm = new JTextField();
		pm.setText("AM");
		pm.setHorizontalAlignment(SwingConstants.CENTER);
		pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		pm.setToolTipText("AM");
		pm.setEditable(false);
		pm.setColumns(10);
		pm.setBounds(238, 24, 55, 29);
		contentPanel.add(pm);
		
		JLabel hh = new JLabel("");
		hh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x=Integer.parseInt(hour.getText());
				if(x<12){
					x++;
					if(x<10)
					hour.setText("0"+Integer.toString(x));
					else
						hour.setText(Integer.toString(x));
				}
			}
		});
		Image up = new ImageIcon(getClass().getResource("/up.png")).getImage();
		Image down = new ImageIcon(getClass().getResource("/d.png")).getImage();
		hh.setIcon(new ImageIcon(up));
		hh.setBounds(28, 64, 28, 29);
		contentPanel.add(hh);
		
		hh2 = new JLabel("");
		hh2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x=Integer.parseInt(hour.getText());
				if(x>0){
					x--;
					if(x<10)
					hour.setText("0"+Integer.toString(x));
					else
						hour.setText(Integer.toString(x));
				}
			}
		});
		hh2.setIcon(new ImageIcon(down));
		hh2.setBounds(68, 64, 28, 29);
		contentPanel.add(hh2);
		
		mm = new JLabel("");
		mm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x=Integer.parseInt(min.getText());
				if(x<60){
					x++;
					if(x<10)
					min.setText("0"+Integer.toString(x));
					else
						min.setText(Integer.toString(x));
				}
			}
		});
		mm.setIcon(new ImageIcon(up));
		mm.setBounds(137, 64, 28, 29);
		contentPanel.add(mm);
		
		mm2 = new JLabel("");
		mm2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x=Integer.parseInt(min.getText());
				if(x>0){
					x--;
					if(x<10)
					min.setText("0"+Integer.toString(x));
					else
						min.setText(Integer.toString(x));
				
			}
			}
		});
		mm2.setIcon(new ImageIcon(down));
		mm2.setBounds(175, 64, 28, 29);
		contentPanel.add(mm2);
		
		am = new JLabel("");
		am.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(pm.getText().equals("PM"))
					pm.setText("AM");
				else if(pm.getText().equals("AM"))
					pm.setText("PM");
			}
		});
		am.setIcon(new ImageIcon(down));
		am.setBounds(304, 24, 28, 29);
		contentPanel.add(am);
		
		btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File time=new File("clock");
				try{
					FileWriter b=new FileWriter(time);
					b.write(hour.getText()+":"+min.getText()+":00"+" "+pm.getText());
					b.close();
					//Thread.sleep(400);
				    //mes.setText( "Successfully Saved");
					dispose();
					JOptionPane.showMessageDialog(null, "Successfully Saved");
				   
					
				}
				catch(Exception e){
					
				}
			}
		});
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(78, 104, 55, 25);
		contentPanel.add(btnNewButton);
		
		mes = new JTextField();
		mes.setText("Click OK");
		mes.setHorizontalAlignment(SwingConstants.CENTER);
		mes.setFont(new Font("Tahoma", Font.BOLD, 12));
		mes.setBackground(Color.BLACK);
		mes.setForeground(Color.WHITE);
		mes.setEditable(false);
		mes.setBounds(185, 101, 147, 28);
		contentPanel.add(mes);
		mes.setColumns(10);
	}
}
