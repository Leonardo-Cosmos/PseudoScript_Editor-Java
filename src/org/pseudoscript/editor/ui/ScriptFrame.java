package org.pseudoscript.editor.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.pseudoscript.editor.config.WindowConfig;

public class ScriptFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4793631349446121291L;
	
	private static final Logger LOGGER = Logger.getLogger(ScriptFrame.class.getSimpleName());
	
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
	}
	
}
