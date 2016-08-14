package org.pseudoscript.editor.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.pseudoscript.assembly.OperationDef;
import org.pseudoscript.assembly.ParameterDef;
import org.pseudoscript.script.OperationInfo;
import org.pseudoscript.script.OperationInfoImpl;

public class OperationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8623709525284614892L;
	
	private static final String LABEL_FORMAT_EXECUTOR = "Executor: %s";
	private static final String LABEL_FORMAT_NAME = "Operation: %s";
	
	public OperationPanel(String executer, OperationDef operationDef) {
		initComponents(executer, operationDef);
	}
	
	private void initComponents(String executor, OperationDef operationDef) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel executorLabel = new JLabel();
		executorLabel.setText(String.format(LABEL_FORMAT_EXECUTOR, executor));
		panel.add(executorLabel);
		
		JLabel nameLabel = new JLabel();
		String name = operationDef.getName();
		nameLabel.setText(String.format(LABEL_FORMAT_NAME, name));
		panel.add(nameLabel);
		
		for (ParameterDef parameterDef : operationDef.getParameters()) {
			JPanel parameterPanel = new ParameterPanel(parameterDef);
			add(parameterPanel);
		}
		
		add(panel);
	}
	
	public void load(OperationInfo operationInfo) {
		
	}
	
	public OperationInfo save() {
		return new OperationInfoImpl();
	}
	
}
