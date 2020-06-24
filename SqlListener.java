import java.util.*;
public interface SqlListener
{
public void setErrorText(String s);
public void setOutputText(String s);
public void setTable(Object[][] data, String [] title);
public void drawDatabaseTable(List <Table>tables);
}