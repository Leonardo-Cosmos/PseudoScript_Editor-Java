package org.pseudoscript.editor.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.pseudoscript.assembly.OperationDef;
import org.pseudoscript.script.OperationInfo;
import org.pseudoscript.script.OperationInfoImpl;

public class OperationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8623709525284614892L;
	
	private static final String LABEL_FORMAT_EXECUTOR = "Executor: ";
	private static final String LABEL_FORMAT_NAME = "Operation: ";

	public OperationPanel(OperationDef operationDef) {
		initComponents(operationDef);
	}
	
	private void initComponents(OperationDef operationDef) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel executorLabel = new JLabel();
		executorLabel.setText(String.format(LABEL_FORMAT_EXECUTOR,
				operationDef.getExecutor()));
		panel.add(executorLabel);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText(String.format(LABEL_FORMAT_NAME,
				operationDef.getName()));
		panel.add(nameLabel);
		
		add(panel);
	}
	
	public OperationInfo getInfo() {
		return new OperationInfoImpl();
	}
	
}
