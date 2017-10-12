//STEP 1. Import required packages
import java.sql.*;

public class SQDBConnection {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/arrestdb";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "NewPass1!";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT ArrestID, Firstname, Lastname, Charges, ImagePath FROM Arrests";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("ArrestID");         
         String first = rs.getString("Firstname");
         String last = rs.getString("Lastname");
		 String charges = rs.getString("Charges");
		 String imagePath = rs.getString("ImagePath");

         //Display values
         System.out.print("ID: " + id);         
         System.out.print(", First: " + first);
         System.out.println(", Last: " + last);
		 System.out.println(", Charges: " + charges);
		 System.out.println(", Image Path: " + imagePath);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample