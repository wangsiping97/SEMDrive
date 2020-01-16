package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import log.Main;

import java.awt.LayoutManager;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class RemoteFiles extends JFrame {
	String columns[] = { "File Name", "Size" };  
	Object rows[][];
	String remoteFile;
	String prefix;

	public RemoteFiles(Object [][]files, String remotePrefix) {
		this.rows = files;
		//this.prefix = remotePrefix + '/';
		this.prefix = remotePrefix;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame("View your files on the server");  
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
        TableModel model = new DefaultTableModel(rows, columns) {  
            public Class getColumnClass(int column) {  
                Class returnValue;  
                if ((column >= 0) && (column < getColumnCount())) {  
                    returnValue = getValueAt(0, column).getClass();  
                } else {  
                    returnValue = Object.class;  
                }  
                return returnValue;  
            }  
        };  
        
        final JTable table = new JTable(model);
           
        table.addMouseListener(new java.awt.event.MouseAdapter(){
        		public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
        			//得到选中的行列的索引值
        			int r= table.getSelectedRow();//得到选中的单元格的值，表格中都是字符;
        			//String info = "";
        			Object value= table.getValueAt(r, 0);
        			//info += r+"行值 : "+value.toString()+'\n';
        			System.out.print(value.toString());
        			if(value.toString() != null) {remoteFile = value.toString();}
        			//javax.swing.JOptionPane.showMessageDialog(null,info);
        		}
        	}); 
        
        
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(  
                model);  
        table.setRowSorter(sorter);  
        JScrollPane pane = new JScrollPane(table);  
        frame.getContentPane().add(pane, BorderLayout.CENTER);
        JPanel panel = new JPanel(new BorderLayout());  
        JLabel label = new JLabel("Filter");  
        panel.add(label, BorderLayout.WEST); 
        
        final JTextField filterText = new JTextField("");  
        panel.add(filterText, BorderLayout.CENTER);  
        frame.getContentPane().add(panel, BorderLayout.NORTH);  
        JButton button = new JButton("Filter");
        panel.add(button, BorderLayout.EAST);
        
        
        button.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String text = filterText.getText();  
                if (text.length() == 0) {  
                    sorter.setRowFilter(null);  
                } else {  
                    sorter.setRowFilter(RowFilter.regexFilter(text));  
                }  
            }  
        });
        
        JPanel panel_1 = new JPanel((LayoutManager) null);
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout());
        
        JButton btnDel = new JButton("Delete the File");
        panel_1.add(btnDel, BorderLayout.EAST);
        
        JButton btnDown = new JButton("Download the File");
        btnDown.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        panel_1.add(btnDown, BorderLayout.CENTER);
        
        JLabel lblNewLabel = new JLabel("Please select one file at a time.");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(lblNewLabel, BorderLayout.NORTH);
        
        JButton btnReturn = new JButton("Return");
        panel_1.add(btnReturn, BorderLayout.WEST);
        
        btnReturn.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            		frame.dispose();
            		FileOperation fo = new FileOperation(prefix);
            		fo.frame.setVisible(true);
            }  
        });
        
        btnDel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
    			selectdirs fo = new selectdirs();
    			TestScp test = new TestScp();
    			try {
					test.delFile(remoteFile, prefix + '/');
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			//frame.setVisible(true);
    			//new RemoteFiles(rows, remoteTargetDirectory);
    			
    			Object[][] rows = null;
    			try {
    				rows = test.getFileProperties(prefix);
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			new RemoteFiles(rows, prefix);
            }
        });
        
        
        btnDown.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	frame.dispose();
    			selectdirs fo = new selectdirs();
    			
    			TestScp test = new TestScp();

    			//test.putFile(fo.openDic(), remoteTargetDirectory);
    			try {
					test.getFile(remoteFile,fo.openDic() ,prefix + '/');
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			frame.setVisible(true);
            }
        });
        
        
        
        frame.setSize(384, 250);  
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);  
	}

}
