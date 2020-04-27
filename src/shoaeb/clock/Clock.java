package shoaeb.clock;


import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import sun.audio.*;

import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class Clock extends JFrame {

	private JPanel contentPane;
	private JTextField tb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clock frame = new Clock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Clock() {
		setType(Type.UTILITY);
		setTitle("Digital Clock");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 480, 235);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		Image down = new ImageIcon(getClass().getResource("/Documents-icon.png")).getImage();
		mnNewMenu.setIcon(new ImageIcon(down));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Delete Alarm");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File time=new File("clock");
				try{
					FileWriter b=new FileWriter(time);
					b.write("00:00:00 AM");
					b.close();
					
				   
					
				}
				catch(Exception e){
					
				}
			}
		});
		Image ww = new ImageIcon(getClass().getResource("/exit.png")).getImage();
		mntmNewMenuItem_1 .setIcon(new ImageIcon(ww));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmNewMenuItem .setIcon(new ImageIcon(ww));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Alarm");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Alarm().setVisible(true);
			}
		});
		Image da = new ImageIcon(getClass().getResource("/alert-icon.png")).getImage();
		mnNewMenu_1.setIcon(new ImageIcon(da));
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu_1);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digital Clock By Shoaeb");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(153, 148, 169, 32);
		contentPane.add(lblNewLabel);
		
		tb = new JTextField();
		tb.setHorizontalAlignment(SwingConstants.CENTER);
		tb.setForeground(Color.GREEN);
		tb.setBackground(Color.BLACK);
		tb.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 99));
		tb.setEditable(false);
		tb.setBounds(0, 0, 474, 148);
		contentPane.add(tb);
		tb.setColumns(10);
		try {
			Thread x=new Thread(new Runnable(){
				public void run(){
				while(true){
					try {
						Thread.sleep(100);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e);
					}
					  DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
			          Date date = new Date();
			          String com=null;
			          tb.setText( dateFormat.format(date));
			          File time=new File("clock");
						try{
							BufferedReader b=new BufferedReader(new FileReader(time));
							com=b.readLine();
						    b.close();
						   
							
						}
						catch(Exception e){
							
						}
			          if(tb.getText().equals(com)){
				          
				          playSound(" ");
				         
			          }
			         
			          
				}
					
				}
			});
			x.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		
		}
		
	}
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		         Clock.class.getResourceAsStream("/alarm.wav"));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
}
