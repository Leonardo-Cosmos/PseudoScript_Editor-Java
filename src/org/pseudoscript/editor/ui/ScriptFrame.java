package org.pseudoscript.editor.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.pseudoscript.editor.config.WindowConfig;
import org.pseudoscript.script.Script;
import org.pseudoscript.script.xml.XmlScriptConsumer;
import org.pseudoscript.script.xml.XmlScriptProvider;

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
	
	private static final String SCRIPT_FILE_DESCRIPTION = "Pseudo Script file";
	
	private static final Pattern SCRIPT_FILE_NAME_PATTERN = Pattern.compile(".+\\.[xX][mM][lL]") ;
	
	private final JFileChooser fileChooser;
	
	private OperationListPanel operationListPanel;
	
	private Script script;
	
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
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return SCRIPT_FILE_DESCRIPTION;
			}
			
			@Override
			public boolean accept(File f) {
				return SCRIPT_FILE_NAME_PATTERN.matcher(f.getName()).matches();
			}
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
		
		operationListPanel = new OperationListPanel();
		add(operationListPanel, BorderLayout.CENTER);
	}
	
	private void newFile(ActionEvent e) {
		
	}
	
	private void openFile(ActionEvent e) {
		int openFileOption = fileChooser.showOpenDialog(this);
		if (JFileChooser.APPROVE_OPTION == openFileOption) {
			File file = fileChooser.getSelectedFile();
			openScript(file);
		}
	}
	
	private void saveFile(ActionEvent e) {
		
	}
	
	private void saveAsFile(ActionEvent e) {
		int saveFileOption = fileChooser.showSaveDialog(this);
		if (JFileChooser.APPROVE_OPTION == saveFileOption) {
			File file = fileChooser.getSelectedFile();
			saveScript(file);
		}
	}
	
	private void openScript(File scriptFile) {
		XmlScriptConsumer consumer = new XmlScriptConsumer();
		Reader reader = null;
		script = null;
		try {
			reader = new FileReader(scriptFile);
			consumer.setInput(reader);
			script = consumer.consume();
		} catch (IOException ex) {
			LOGGER.error("Failed to open script file.", ex);
			JOptionPane.showConfirmDialog(this, "Failed to open script file.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					LOGGER.error("Failed to close file.", ex);
				}
			}
		}
	}
	
	private void saveScript(File scriptFile) {
		XmlScriptProvider provider = new XmlScriptProvider();
		Writer writer = null;
		try {
			writer = new FileWriter(scriptFile);
			provider.setOutput(writer);
			provider.provide(script);
		} catch (IOException ex) {
			LOGGER.error("Failed to save script file.", ex);
			JOptionPane.showConfirmDialog(this, "Failed to save script file.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					LOGGER.error("Failed to close file.", ex);
				}
			}
		}
	}
}
