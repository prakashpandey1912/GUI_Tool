import java.sql.*;
import java.util.*;
class DataBase
{
private List<Table>tables;
private String dbName;
DataBase()
{
tables= new LinkedList<>();
this.dbName=null;
}

public void setDBName(String dbName)
{
this.dbName=dbName;
}
public void addTable(Table table)
{
this.tables.add(table);
}
public String getDBName()
{
return this.dbName;
}
public List<Table> getDBTables()
{
return tables;
}
public String toString()
{
return dbName;
}
}


class Table
{
private List<Column>columns;
private String tableName;

Table()
{
columns= new LinkedList<>();
this.tableName=null;
}
public void setTableName(String tableName)
{
this.tableName=tableName;
}
public void addColumns(Column column)
{
this.columns.add(column);
}
public String getTableName()
{
return this.tableName;
}
public List<Column> getColumns()
{
return columns;
}
public String toString()
{
return tableName;
}
}



class Column
{
String type;
String name;
Column()
{
this.type=null;
this.name=null;
}
public void setColumnType(String type)
{
this.type=type;
}
public void setColumnName(String name)
{
this.name=name;
}
public String getColumnType()
{
return this.type;
}
public String getColumnName()
{
return this.name;
}
public String toString()
{
return this.name;
}
}
 