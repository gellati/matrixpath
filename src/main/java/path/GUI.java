package path;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

public class GUI extends JFrame{

    JFrame jframe = null;
    JPanel gridAreaPanel = null;
    JPanel rowComboPanel = null;
    JPanel colComboPanel = null;
    JPanel contentPane = null;
    JPanel spinnerGrid = null;

    int width = 500;
    int height = 500;

    
    public GUI(){
	int [] rows = {1,2,3,4,5};
	int [] cols = {1,2,3,4,5};
	
	jframe = new JFrame("Pathfinder");
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	contentPane = new JPanel();
	contentPane.setLayout(new BorderLayout());
	JPanel  inputPane = new JPanel();

	rowComboPanel = gridComboBox(rows);
	inputPane.add(rowComboPanel);

	colComboPanel = gridComboBox(cols);
	inputPane.add(colComboPanel);
	inputPane.add(enterGridSizeButtonPanel("Enter"));

	contentPane.add(inputPane, BorderLayout.PAGE_START);
	
	gridAreaPanel = gridAreaPanel(rows[rows.length-1], cols[cols.length-1]);
	gridAreaPanel.setPreferredSize(new Dimension (width, height));
	contentPane.add(gridAreaPanel, BorderLayout.CENTER);

	contentPane.add(calculatePathButton("Calculate Path"), BorderLayout.PAGE_END);
	
	jframe.add(contentPane);

	jframe.setSize(800, 800);
	jframe.pack();
	jframe.setVisible(true);		
    }

    public void drawGrid(int [][] A){

	Guicontents gc = new Guicontents();
	JPanel spinnergrid = gc.buildSpinnerGrid(A);
       	jframe.add(spinnergrid);
    }

    public JPanel enterGridSizeButtonPanel(String title){
	JButton button = new JButton(title);
	JPanel buttonPanel = new JPanel(new BorderLayout());
	buttonPanel.add(button);
	button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){

		    /*
		    JPanel c = getRowComboPanel();
		    Component [] components = c.getComponents();
		    System.out.println(c.getComponentCount());
		    //		    Component comp = c.getComponent(0);
		    for(int i = 0; i < components.length; i++){
			if(components[i] instanceof JComboBox){	    
			    JComboBox jcb = (JComboBox) c.getComponent(i);
			    System.out.println(jcb.getSelectedItem());
			}
		    }
		    */

		    JComboBox rjcb = (JComboBox) rowComboPanel.getComponent(0);
		    System.out.println(rjcb.getSelectedItem());
		    JComboBox cjcb = (JComboBox) colComboPanel.getComponent(0);
		    System.out.println(cjcb.getSelectedItem());
		    JPanel newGrid = gridAreaPanel((Integer)rjcb.getSelectedItem(), (Integer)cjcb.getSelectedItem());
		    newGrid.setPreferredSize(new Dimension(500, 500));

		    gridAreaPanel.removeAll();
       		    gridAreaPanel.add(newGrid);

		    gridAreaPanel.repaint();
		    gridAreaPanel.revalidate();		    
		}
	    });
	return buttonPanel;
    }

    public JPanel getRowComboPanel(){
	return rowComboPanel;
    }

    public JPanel getColComboPanel(){
	return colComboPanel;
    }

    public JPanel getGridAreaPanel(){
	return gridAreaPanel;
    }

    public JPanel calculatePathButton(String title){
	JButton button = new JButton(title);
	JPanel buttonPanel = new JPanel(new BorderLayout());
	buttonPanel.add(button);
	button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    JPanel gridPanel = (JPanel) getGridAreaPanel();
		    System.out.println(gridPanel.getComponentCount());
		    JPanel jp0 = (JPanel) gridPanel.getComponent(0);


		    JSpinner jsp = (JSpinner)spinnerGrid.getComponent(0);
		    System.out.println(jsp.getValue());

		    System.out.println(jp0.getComponentCount());

		    //		    JSpinner jpc1 = (JSpinner) jp0.getComponent(0);
		    System.out.println(spinnerGrid.getComponentCount()); // number of jspinners
		    GridLayout lm =  (GridLayout)spinnerGrid.getLayout();
		    System.out.println(lm.getRows());
		    System.out.println(lm.getColumns());

		    int [][] m = createMatrix(lm.getRows(), lm.getColumns(), spinnerGrid);

		    printMatrix(m);

		    PathFinder pf = new PathFinder();

		    ArrayList<String> pathList = pf.robotPaths(m, lm.getRows(), lm.getColumns());
		    ArrayList<Path> paths = pf.parsePaths(pathList);
		    Path p = pf.getMostValuablePath(paths);

		    JPanel bestPathGridPanel = bestPathGridPanel(m, p);
		    gridAreaPanel.removeAll();
		    gridAreaPanel.add(bestPathGridPanel);
		    gridAreaPanel.repaint();
		    gridAreaPanel.revalidate();

		}
	    });	
	return buttonPanel;
    }


    public JPanel gridAreaPanel(int rows, int cols){

	JPanel containerPanel = new JPanel();
	containerPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
	containerPanel.setLayout(new BorderLayout());
	
	spinnerGrid = new JPanel(new GridLayout(rows, cols)); // or gridbag
	for(int i = 0; i < rows; i++){
	    for(int j = 0; j < cols; j++){
		JPanel panel = new JPanel();
		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
		JSpinner jspinner = new JSpinner(model);
		((JSpinner.NumberEditor)jspinner.getEditor()).getTextField().setColumns(10);
		jspinner.setPreferredSize(new Dimension(50, 50));
		//		jspinner.getEditor().getComponent(0).setBackground(Color.RED);
		spinnerGrid.add(jspinner);
	    }
	}
	containerPanel.add(spinnerGrid, BorderLayout.CENTER);
	return containerPanel;	
    }


    public JPanel gridAreaPanel(int rows, int cols, int [][] matrix){

	JPanel spinnerGrid = new JPanel(new GridLayout(rows, cols)); // or gridbag

	for(int i = 0; i < rows; i++){
	    //	    JPanel spinnerRow = new JPanel();
	    for(int j = 0; j < cols; j++){
		//JPanel panel = new JPanel();
		SpinnerNumberModel model = new SpinnerNumberModel(matrix[i][j], 0, 100, 1);
		JSpinner jspinner = new JSpinner(model);
		//	((JSpinner.NumberEditor)jspinner.getEditor()).getTextField().setColumns(10);
		//jspinner.setPreferredSize(new Dimension(50, 50));
		//		jspinner.getEditor().getComponent(0).setBackground(Color.RED);
		spinnerGrid.add(jspinner);
	    }
	}

	JPanel containerPanel = new JPanel();
	containerPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
	containerPanel.setLayout(new BorderLayout());
	containerPanel.add(spinnerGrid, BorderLayout.CENTER);
	return containerPanel;	
    }
    
    /*
     * todo: color the best path
     *
     */
    
    public JPanel bestPathGridPanel(int [][] grid,  Path path){
	JPanel jp = new JPanel();
	int nodeCounter = 0;

	JPanel spinnerGrid = new JPanel(new GridLayout(grid.length, grid[0].length));
	for(int i = 0; i < grid.length; i++){
	    for(int j = 0; j < grid[i].length; j++){		
		SpinnerNumberModel model = new SpinnerNumberModel(grid[i][j], 0, 100, 1);
		JSpinner jspinner = new JSpinner(model);
		if(path.getNode(nodeCounter).getX() == i && path.getNode(nodeCounter).getY() == j){
		    jspinner.getEditor().getComponent(0).setBackground(Color.RED);
		    nodeCounter++;
		}
		spinnerGrid.add(jspinner);
	    }
	}

	JPanel containerPanel = new JPanel();
	containerPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
	containerPanel.setLayout(new BorderLayout());
	containerPanel.add(spinnerGrid, BorderLayout.CENTER);	
	
	return containerPanel;
    }

    
    public int [][] createMatrix(int rows, int cols, JPanel grid){
	int [][] matrix = new int [rows][cols];
	int counter = 0;
	for(int i = 0; i < rows; i++){
	    for(int j = 0; j < cols; j++){
		JSpinner jsp = (JSpinner)grid.getComponent(counter);
		matrix[i][j] = (Integer)jsp.getValue();
		counter++;
	    }
	}
	return matrix;	
    }

    public void printMatrix(int [][] m){
	for(int i = 0; i < m.length; i++){
	    for(int j = 0; j < m[i].length; j++){
		System.out.print(m[i][j] + " ");
	    }
	    System.out.println();
	}
    }

    
    public JPanel gridComboBox(int [] a){

	//	DefaultComboBoxModel jcbModel = new DefaultComboBoxModel(a);

	JComboBox jcb = new JComboBox();
	
	for(int i: a){
	    jcb.addItem(i);
	}
		
	JPanel comboPanel = new JPanel();
	comboPanel.add(jcb);
	return comboPanel;
    }


}
