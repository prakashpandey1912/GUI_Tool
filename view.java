import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
class view extends JFrame implements ActionListener,SqlListener,ListSelectionListener
{

DefaultListModel<Table> model;
private JList tableList;
private JTextField sql;
private JTextArea error,output;
//private JButton connect,quit,disconnect;
private JButton run,clear;
private JButton generateDTO;
private JPanel p8;
private JPanel pTable,listPanel; 
private JTable table; 
private JScrollPane jsp; 
private MyModel m;
private Object[][] data ;
private String[] title;
private MyClass myClass;
JScrollPane scrollPane;
private JMenuBar mb;
private JMenu menu;
public static JMenuItem connect,disconnect,quit,radSettings;
Table DTOTable;
private JLabel moduleTitleLabel,errorLabel,outputLabel,sqlLabel,dbTables;

view()
{
super("GUI Main Page");

DTOTable=null;
data = new Object[0][0];
title = new String[0];
m = new MyModel();
myClass=MyClass.getInstance();
myClass.setSqlListener(this);
errorLabel =new JLabel("Error");
outputLabel= new JLabel("Output");
sqlLabel= new JLabel("SQL  Statement");
dbTables = new JLabel("Database Tables");
dbTables.setBounds(120,25,150,30);
Font fnn=new Font("Calibary",Font.BOLD,16); 
dbTables.setFont(fnn);


model = new DefaultListModel<>();
tableList= new JList<>( model );
tableList.setBounds(15,15,300,630);
tableList.setBorder(BorderFactory.createLineBorder(Color.black));
scrollPane = new JScrollPane(tableList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scrollPane.setBounds(10,70,320,600);

sql=new JTextField();
error=new JTextArea();
output=new JTextArea();
error.setBackground(Color.black);
error.setForeground(Color.red);
output.setForeground(Color.green);
Font fn=new Font("Verdana",Font.PLAIN,16);  
error.setFont(fn); 
output.setFont(fn);
sql.setFont(fn);
error.setLineWrap(true);
output.setLineWrap(true);


connect=new JMenuItem("Connect");
disconnect=new JMenuItem("Disconnect");
quit=new JMenuItem("QUIT");
radSettings=new JMenuItem("RAD Settings");

menu=new JMenu("MENU");
menu.add(connect);
menu.add(disconnect);
menu.add(radSettings);
menu.add(quit);



mb=new JMenuBar();
mb.add(menu);
setJMenuBar(mb);

connect.setEnabled(true);
disconnect.setEnabled(false);
quit.setEnabled(true);


run=new JButton("Run");
clear=new JButton("Clear");
generateDTO=new JButton("Generate DTO");

radSettings.addActionListener(this);
connect.addActionListener(this);
disconnect.addActionListener(this);
quit.addActionListener(this);
run.addActionListener(this);
clear.addActionListener(this);
menu.addActionListener(this);
tableList.addListSelectionListener(this);
generateDTO.addActionListener(this);

//Sql area where we write a code
JPanel p3=new JPanel();
p3.setLayout(null);
p3.setBounds(15,5,975,55);
sqlLabel.setBounds(10,0,500,30);
sql.setBounds(10,25,700,30);
run.setBounds(750,25,80,30);
clear.setBounds(850,25,80,30);
p3.add(sqlLabel);
p3.add(sql);
p3.add(run);
p3.add(clear);

//error wala panel
JPanel p4=new JPanel();
p4.setLayout(null);
p4.setBounds(15,470,1000,260);
errorLabel.setBounds(15,0,150,30);
error.setBounds(20,30,970,220);
p4.add(errorLabel);
p4.add(error);
p4.setBorder(BorderFactory.createLineBorder(Color.black));


//contain sql and output panel
JPanel p5=new JPanel();
p5.setLayout(null);
p5.setBounds(15,5,1000,465);
p5.add(p3);
p5.setBorder(BorderFactory.createLineBorder(Color.black));

//output table
pTable=new JPanel();
table=new JTable(m); 
Font f=new Font("Verdana",Font.PLAIN,16); 
table.setFont(f); 
table.setRowHeight(30);
table.getTableHeader().setReorderingAllowed(false); 
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); 
pTable.setLayout(new BorderLayout()); 
pTable.add(jsp,BorderLayout.CENTER);   
pTable.setVisible(false); 
pTable.setBounds(15,30,985,355);

//output panel
p8=new JPanel();
p8.setLayout(null);
p8.setBounds(0,65,1000,400);
output.setBorder(BorderFactory.createLineBorder(Color.black));
outputLabel.setBounds(15,0,150,30);
output.setBounds(20,30,970,355);
output.setVisible(true);
p8.add(outputLabel);
p8.add(pTable);
p8.add(output);
p8.setBorder(BorderFactory.createLineBorder(Color.black));
p5.add(p8);

//DTO panel 
generateDTO.setBounds(60,680,200,30);
generateDTO.setEnabled(false);
listPanel = new JPanel();
listPanel.setLayout(null);
listPanel.setBounds(1030,5,340,725);
listPanel.setBorder(BorderFactory.createLineBorder(Color.black));
listPanel.add(dbTables);
listPanel.add(scrollPane);
listPanel.add(generateDTO);

//full Panel
JPanel p9=new JPanel();
p9.setLayout(null);
p9.add(p5);
p9.add(p4);
p9.add(listPanel); 
add(p9,BorderLayout.CENTER);
p9.setBorder(BorderFactory.createLineBorder(Color.black));
setLocation(10,10);
setSize(1400,800);
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}


public static void setEnableAfterConnection()
{
connect.setEnabled(false);
disconnect.setEnabled(true);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==connect)
{
MyConnection c = new MyConnection();
}
if(e.getSource()==disconnect)
{
generateDTO.setEnabled(false);
model.removeAllElements();
myClass.disConnect();
connect.setEnabled(true);
disconnect.setEnabled(false);
}
if(e.getSource()==quit)
{
myClass.disConnect();
connect.setEnabled(true);
disconnect.setEnabled(false);
quit.setEnabled(true);
System.exit(0);
}
if(e.getSource()==radSettings)
{
RadSettings r=new RadSettings();
}
if(e.getSource()==generateDTO)
{
error.setText("");
if(RadSettings.fullPath==null)
{
generateDTO.setEnabled(false);
error.setText("First set RAD Settings then select a table");
}
else
{
GenerateDL gdl=new GenerateDL();
System.out.println(DTOTable.getTableName());
gdl.generateDTO(DTOTable);
}
}
if(e.getSource()==clear)
{
sql.setText("");
}
if(e.getSource()==run)
{
String s=sql.getText();
if(s.trim().length()==0)
{
error.setText("Please! enter sql statement");
}
else
{
if(s.trim().indexOf("select") != -1) myClass.myExecuteQuery(s.trim());
else myClass.myExecuteUpdate(s.trim());
}
}
}
public void valueChanged(ListSelectionEvent ev)
{
 if (!ev .getValueIsAdjusting()) {
DTOTable=(Table)tableList.getSelectedValue();
generateDTO.setEnabled(true);
 MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();

 if (mouseEvent.getClickCount() == 2) {
         int index = theList.locationToIndex(mouseEvent.getPoint());
          if (index >= 0) {
           Object  o = theList.getModel().getElementAt(index);
DTOTable=(Table)o;
myClass.myExecuteQuery(" select * from "+o.toString().trim()+"");
sql.setText((" select * from "+o.toString().trim()+""));
          }
        }
      }
    };
tableList.addMouseListener(mouseListener);
}
}

//printing in different section
public void setErrorText(String s)
{
error.setText(s);
output.setText("");
}
public void setOutputText(String s)
{
pTable.setVisible(false);
output.setVisible(true);
output.setText(s);
error.setText("");
}

//table data work
public void setTable(Object[][] data, String [] title)
{
this.data=data;
this.title=title;
AbstractTableModel atm = (AbstractTableModel) table.getModel();
atm.fireTableStructureChanged();
output.setVisible(false);
pTable.setVisible(true);
error.setText("");
}
public void drawDatabaseTable(List<Table> tables)
{
model.removeAllElements();
for(Table s:tables)
{
model.addElement(s);                                                    
}
}

//inner class
public class MyModel extends AbstractTableModel 
{ 
public MyModel() 
{ 
} 
public int getColumnCount() 
{ 
return title.length; 
} 
public String getColumnName(int columnIndex) 
{ 
return title[columnIndex]; 
} 
public int getRowCount() 
{ 
return data.length; 
} 
public boolean isCellEditable(int rowIndex,int columnIndex) 
{  
return false; 
} 
public Object getValueAt(int rowIndex,int columnIndex) 
{ 
return data[rowIndex][columnIndex]; 
} 
} 

//main
public static void main(String gg[])
{
try
{
view a=new view();
}catch(Exception e)
{
System.out.println(e);
}
}
}