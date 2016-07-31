package org.pseudoscript.editor.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.pseudoscript.editor.config.WindowConfig;

import jdk.nashorn.internal.runtime.ScriptFunction;

public class ScriptFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4793631349446121291L;
	
	private static final Logger LOGGER = Logger.getLogger(ScriptFrame.class.getSimpleName());
	
	private static final String FRAME_TITILE = "Pseudo Script - (%s)";
	
	private static final String DEFAULT_NEW_FILE_NAME = "new";
	
	private static final String FILE_MENU_TEXT = "File";
	
	private static final String FILE_NEW_MENU_ITEM_TEXT = "New";
	
	private static final String FILE_OPEN_MENU_ITEM_TEXT = "Open...";
	
	private static final String FILE_SAVE_MENU_ITEM_TEXT = "Save...";
	
	private static final String FILE_SAVE_AS_MENU_ITEM_TEXT = "Save as...";
	
	private final JFileChooser fileChooser = new JFileChooser();
	
	private OperationListPanel operationListPanel;
	
	public ScriptFrame() {
		
		initComponents();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				WindowConfig windowConfig = null;
				try {
					windowConfig = WindowConfig.load();
				} catch (IOException | JAXBException ex) {
					LOGGER.error("Failed to load WindowConfig.", ex);
					JOptionPane.showConfirmDialog(ScriptFrame.this, "Failed to load window configure.");
				}
				
				Dimension size = new Dimension(windowConfig.getSizeWidth(), windowConfig.getSizeHeight());
				setSize(size);
				
				Point location = new Point(windowConfig.getLocationX(), windowConfig.getLocationY());
				setLocation(location);
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				WindowConfig windowConfig = new WindowConfig();
				
				Dimension size = getSize();
				windowConfig.setSizeWidth(size.width);
				windowConfig.setSizeHeight(size.height);
				
				Point location = getLocation();
				windowConfig.setLocationX(location.x);
				windowConfig.setLocationY(location.y);
				
				try {
					WindowConfig.save(new WindowConfig());
				} catch (IOException | JAXBException ex) {
					LOGGER.error("Failed to save WindowConfig.", ex);
					JOptionPane.showConfirmDialog(ScriptFrame.this, "Failed to save window configure.");
				}
			}
			
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			};
		});
	}
	
	private void initComponents() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		setTitle(String.format(FRAME_TITILE, DEFAULT_NEW_FILE_NAME));
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu(FILE_MENU_TEXT);
		
		JMenuItem newFileMenuItem = new JMenuItem(FILE_NEW_MENU_ITEM_TEXT);
		newFileMenuItem.addActionListener(this::newFile);
		fileMenu.add(newFileMenuItem);
		
		JMenuItem openFileMenuItem = new JMenuItem(FILE_OPEN_MENU_ITEM_TEXT);
		openFileMenuItem.addActionListener(this::openFile);
		fileMenu.add(openFileMenuItem);
		
		JMenuItem saveFileMenuItem = new JMenuItem(FILE_SAVE_MENU_ITEM_TEXT);
		saveFileMenuItem.addActionListener(this::saveFile);
		fileMenu.add(saveFileMenuItem);
		
		JMenuItem saveAsFileMenuItem = new JMenuItem(FILE_SAVE_AS_MENU_ITEM_TEXT);
		saveAsFileMenuItem.addActionListener(this::saveAsFile);
		fileMenu.add(saveAsFileMenuItem);
		
		menuBar.add(fileMenu);
		
		add(menuBar, BorderLayout.NORTH);
	}
	
	private void newFile(ActionEvent e) {
		
	}
	
	private void openFile(ActionEvent e) {
		
	}
	
	private void saveFile(ActionEvent e) {
		
	}
	
	private void saveAsFile(ActionEvent e) {
		
	}
	
}
