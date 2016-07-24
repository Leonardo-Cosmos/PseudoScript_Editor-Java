package org.pseudoscript.editor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.pseudoscript.editor.ui.ScriptFrame;

public class App {

	private static final Logger LOGGER = Logger.getLogger(App.class.getSimpleName());
	
	public static void main(String[] args) {
		DOMConfigurator.configure("log4j.xml");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			LOGGER.error("Failed to set look and feel.", ex);
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new ScriptFrame();
				frame.setVisible(true);
			}
		});
	}

}
