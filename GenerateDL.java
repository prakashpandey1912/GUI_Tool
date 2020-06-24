import java.util.*;
import java.io.*;
class GenerateDL
{
private String fullPath;
private String pkg;
GenerateDL()
{
this.fullPath=RadSettings.fullPath;
this.pkg=RadSettings.pkg;
}
public void generateDTO(Table table)
{

try
{
File file=new File(fullPath);
if(file.exists()==false)
{
file.mkdirs();
}
String className=table.getTableName();
String propertyName;
String propertyType;
String javaType;
className=className.substring(0,1).toUpperCase()+className.substring(1).toLowerCase();
while(className.indexOf('_')>=0)
{
int i=className.indexOf('_');
String s1=className.substring(0,i);
String s2=className.substring(i+1);
s2=s2.substring(0,1).toUpperCase()+s2.substring(1).toLowerCase();
className=s1+s2;
}

String fileName=fullPath+"\\"+className+".java";
System.out.println(fileName);
File javaFile=new File(fileName);
if(javaFile.exists()) javaFile.delete();
RandomAccessFile randomAccessFile=new RandomAccessFile(javaFile,"rw");
randomAccessFile.writeBytes("package "+pkg+";\r\n");
randomAccessFile.writeBytes("public class  "+className+" implements java.io.Serializable\r\n");
randomAccessFile.writeBytes("{\r\n");
List<Column> cc=table.getColumns();
for(Column c:cc)
{
propertyName=c.getColumnName();
// some 
propertyName=propertyName.toLowerCase();
while(propertyName.indexOf('_')>=0)
{
int i=propertyName.indexOf('_');
String s1=propertyName.substring(0,i);
String s2=propertyName.substring(i+1);
s2=s2.substring(0,1).toUpperCase()+s2.substring(1).toLowerCase();
propertyName=s1+s2;
}
propertyType=c.getColumnType();
// some if conditions to decide javaType
javaType="";
if(propertyType.equals("INTEGER")) javaType="int";
if(propertyType.equals("CHAR")) javaType="String";
randomAccessFile.writeBytes("private "+javaType+" "+propertyName+";\r\n");
}



randomAccessFile.writeBytes("public "+className+"()\r\n");

randomAccessFile.writeBytes("{\r\n");



for(Column c:cc)

{

propertyName=c.getColumnName();


propertyName=propertyName.toLowerCase();
while(propertyName.indexOf('_')>=0)
{
int i=propertyName.indexOf('_');
String s1=propertyName.substring(0,i);
String s2=propertyName.substring(i+1);
s2=s2.substring(0,1).toUpperCase()+s2.substring(1).toLowerCase();
propertyName=s1+s2;
}propertyType=c.getColumnType();

javaType="";

if(propertyType.equals("INTEGER")) javaType="int";

if(propertyType.equals("CHAR")) javaType="String";

randomAccessFile.writeBytes("this."+propertyName+" = null;\r\n");
}

randomAccessFile.writeBytes("}\r\n");


for(Column c:cc)
{
propertyName=c.getColumnName();
propertyName=propertyName.toLowerCase();
while(propertyName.indexOf('_')>=0)
{
int i=propertyName.indexOf('_');
String s1=propertyName.substring(0,i);
String s2=propertyName.substring(i+1);
s2=s2.substring(0,1).toUpperCase()+s2.substring(1).toLowerCase();
propertyName=s1+s2;
}
// some 
propertyType=c.getColumnType();
// some if conditions to decide javaType
javaType="";
if(propertyType.equals("INTEGER")) javaType="int";
if(propertyType.equals("CHAR")) javaType="String";
randomAccessFile.writeBytes("public void set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1)+"("+javaType+" "+propertyName+")\r\n");
randomAccessFile.writeBytes("{\r\n");
randomAccessFile.writeBytes("this."+propertyName+"="+propertyName+";\r\n");
randomAccessFile.writeBytes("}\r\n");
// more code to generate getter method
}
for(Column c:cc)
{
propertyName=c.getColumnName();
propertyName=propertyName.toLowerCase();
while(propertyName.indexOf('_')>=0)
{
int i=propertyName.indexOf('_');
String s1=propertyName.substring(0,i);
String s2=propertyName.substring(i+1);
s2=s2.substring(0,1).toUpperCase()+s2.substring(1).toLowerCase();
propertyName=s1+s2;
}
propertyType=c.getColumnType();
javaType="";
if(propertyType.equals("INTEGER")) javaType="int";
if(propertyType.equals("CHAR")) javaType="String";
randomAccessFile.writeBytes("public "+javaType+" get"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1)+"()\r\n");
randomAccessFile.writeBytes("{\r\n");
randomAccessFile.writeBytes("return  "+propertyName+";\r\n");
randomAccessFile.writeBytes("}\r\n");
// more code to generate getter method
}

randomAccessFile.writeBytes("}\r\n");
randomAccessFile.close();
}catch(Exception e)
{
System.out.println(e);
}
}
}


