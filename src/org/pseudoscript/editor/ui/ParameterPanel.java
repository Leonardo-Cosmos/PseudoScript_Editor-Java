package org.pseudoscript.editor.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.pseudoscript.assembly.ParameterDef;
import org.pseudoscript.script.ArgumentInfo;
import org.pseudoscript.script.ArgumentInfoImpl;
import org.pseudoscript.script.SimpleArgumentInfoImpl;

public class ParameterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7568294501004624230L;

	private ParameterDef parameterDef;
	
	public ParameterPanel(ParameterDef parameterDef) {
		this.parameterDef = parameterDef;
		
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new FlowLayout(FlowLayout.LEADING));
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText(parameterDef.getName());
		add(nameLabel);
		
		JTextField argumentTextField = new JTextField();
		add(argumentTextField);
	}
	
	public void load(ArgumentInfo argumentInfo) {
		
	}
	
	public ArgumentInfo save() {
		return new SimpleArgumentInfoImpl();
	}
	
}
