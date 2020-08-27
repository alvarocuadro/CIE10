import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQL {
	
 private  static Connection cn;

 public  static Connection getConnection() {//conecta a la DB de SQL
	 
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				cn=DriverManager.getConnection("jdbc:sqlserver://SQLPRODXHIS:1433;databaseName=xHis_Cap","sistemas_reader","r3ad3rs1spr0d");
			} catch (Exception e) {
				cn=null;
			}
			return cn;}
	
}
