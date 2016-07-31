package org.pseudoscript.editor.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.pseudoscript.script.OperationInfo;

public class OperationListPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5192783289332258194L;
	
	public OperationListPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void load(List<OperationInfo> operationInfos) {
		for (OperationInfo operationInfo : operationInfos) {
			addOperation(operationInfo);
		}
	}
	
	public List<OperationInfo> save() {
		// TODO Save OperationInfo list.
		return new ArrayList<>();
	}
	
	public void addOperation(OperationInfo operationInfo) {
		add(Box.createVerticalStrut(10));
		
	}
	
}
