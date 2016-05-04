import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;


public class Guicontents extends JPanel{
    public Guicontents(){
    }

    public JPanel buildSpinnerGrid(int [][] A){
	
	JPanel spinnerGrid = new JPanel(new GridLayout(A.length, 1));
	for(int i = 0; i < A.length; i++){
	    JPanel spinnerRow = new JPanel();
	    for(int j = 0; j < A[0].length; j++){
		JPanel panel = new JPanel();
		SpinnerNumberModel model = new SpinnerNumberModel(A[i][j], 0, 100, 1);		JSpinner jspinner = new JSpinner(model);
		jspinner.getEditor().getComponent(0).setBackground(Color.RED);
		spinnerRow.add(jspinner);
	    }
	    spinnerGrid.add(spinnerRow);
	}
	return spinnerGrid;
	
    }
}	





