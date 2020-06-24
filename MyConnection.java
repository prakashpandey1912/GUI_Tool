import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
class MyConnection extends JFrame implements ActionListener
{
private JLabel portNumberLabel,serverNameLabel,databaseLabel,serverError,portError,pathError;
private JTextField portNumber,serverName,databaseName;
private JButton connect ,cancel,create;
MyConnection ()
{
super("Connection Window");
serverError = new JLabel("Server name may be wrong");
portError = new JLabel("Port name may be wrong");
pathError = new JLabel("DataBase Path  may be wrong");

portNumberLabel = new JLabel("Port Number");
serverNameLabel = new JLabel("Server Name");
databaseLabel = new JLabel("Database Name");

portNumber = new JTextField ();
serverName = new JTextField ();
databaseName = new JTextField ();

connect = new JButton("Connect");
cancel = new JButton("Cancel");
create = new JButton("Create");

JPanel p1 = new JPanel();
p1.setLayout(null);
portError.setForeground(Color.red);
portError.setBounds(200,5,280,15);
p1.add(portError);
portNumberLabel.setBounds(20,20,100,30);
p1.add(portNumberLabel);
portNumber.setBounds(200,20,150,30);
p1.add(portNumber);
serverError.setForeground(Color.red);
serverError.setBounds(200,50,280,15);
p1.add(serverError);
serverNameLabel.setBounds(20,65,100,30);
p1.add(serverNameLabel);
serverName.setBounds(200,65,150,30);
p1.add(serverName);
pathError.setForeground(Color.red);
pathError.setBounds(200,95,280,15);
p1.add(pathError);
databaseLabel.setBounds(20,110,100,30);
p1.add(databaseLabel);
databaseName.setBounds(200,110,150,30);
p1.add(databaseName);
Font font2=new Font("Calligrapher ",Font.PLAIN,11);
serverError.setFont(font2);
portError.setFont(font2);
pathError.setFont(font2);

JPanel p2 = new JPanel();
p2.setLayout(null);
connect.setBounds(20,30,100,30);
create.setBounds(150,30,100,30);
cancel.setBounds(280,30,100,30);
p2.add(connect);
p2.add(cancel);
p2.add(create);
JPanel p3 = new JPanel();
p3.setLayout(null);
p2.setBounds(0,150,380,80);
p1.setBounds(10,10,380,150);
p3.add(p1);
p3.add(p2);
add(p3);                                      
setSize(400,280);
setVisible(true);
setLocationRelativeTo(null);
portError.setVisible(false);
serverError.setVisible(false);
pathError.setVisible(false);  
connect.addActionListener(this);
cancel.addActionListener(this);
create.addActionListener(this);
}

public void actionPerformed(ActionEvent e) 
{
if(e.getSource()==connect)
{
MyClass s=MyClass.getInstance();
s.connectToDatabase(portNumber.getText(),serverName.getText(),databaseName.getText(),"");
if(s.c==null)
{
portError.setVisible(true);
serverError.setVisible(true);
pathError.setVisible(true); 
}
else
{
view.setEnableAfterConnection();
s.createDataStructure(databaseName.getText());
s.getTables();
dispose();
}
}
if(e.getSource()==create)
{
MyClass s=MyClass.getInstance();
s.connectToDatabase(portNumber.getText(),serverName.getText(),databaseName.getText(),";create=true");
s.getTables();
dispose();
}
if(e.getSource()==cancel)
{
dispose();
}
}
}