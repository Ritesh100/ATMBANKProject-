package atmjava;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;

public class JDBCConnection {
	private String password = "";
	private static ImageIcon format=null;
	byte[] pimage=null;
	Connection conn;
	public JDBCConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	    final String url = "jdbc:mysql://localhost:3306/bank";
	    final String user = "root";
	    
	    try {
	    	conn = DriverManager.getConnection(url, user, password);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public int getDataInt(String query) {
		int toReturnData = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			toReturnData = rs.getInt(1); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturnData;
	}
	
	String getData(String query) {
		String toReturnData = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			toReturnData = rs.getString(1); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturnData;
	}
	
	int ifExists(String query) {
		int count = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		     	rs.next();
		         count = rs.getInt(1);

		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public String[][] dataSet(int count, int u_id) {
		String data[][] = new String[count][4];
		try {
			Statement stm = conn.createStatement();
		    ResultSet res = stm.executeQuery("SELECT * FROM transactions WHERE user_id = '"+u_id+"' order by date_performed desc");
		    int i = 0;
		    while (res.next()) {
		        String id = res.getString("transaction_id");
		        String user = res.getString("user_name");
		        if(user == null) {
		        	user = "null";
		        }
		        String amount = res.getString("amount");
		        String date = res.getString("date_performed");
		        String remarks = res.getString("remarks");
		        String action = res.getString("debit_credit");
		        if(action.equals("debit")) {
		        	amount = "-" + amount;
		        }
		        data[i][0] = id;
		        data[i][1] = amount;
		        data[i][2] = date;
		        data[i][3] = remarks;
		        i++;
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	boolean isEmpty(String query) {
		boolean isEmpty = true;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		    boolean isNext = rs.next();
		    if(isNext == false) {
		    	isEmpty = true;
		    } else {
		    	isEmpty = false;
		    }

		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isEmpty;
	}
	
	void executeQuery(String query) {
		try {
			Statement st = conn.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void preparedQuery(String defCode, String prefix, String firstName, String lastName, String address,
			String email, String date, String gender, String marriedStatus, String passwordInput, String path, int i) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user(account_number,title, first_name, last_name, address, email, dob ,gender, marriagestatus, password, photo, atm_service, balance) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			InputStream img = new FileInputStream(path);
            pstmt.setString(1, defCode);
            pstmt.setString(2, prefix);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, address);
            pstmt.setString(6, email);
            pstmt.setString(7, date);
            pstmt.setString(8, gender);
            pstmt.setString(9, marriedStatus);
            pstmt.setString(10, passwordInput);
            pstmt.setBlob(11, img);
            pstmt.setBoolean(12, false);
            pstmt.setInt(13, 0);
            pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ImageIcon getImage(String query) {
		try {
			PreparedStatement prep = conn.prepareStatement(query);
			ResultSet rs=prep.executeQuery();
			if(rs.next())
	           {          
	               byte[] imagedata=rs.getBytes("photo");
	               format=new ImageIcon(imagedata);
	               Image mm = format.getImage();
	               Image img2=mm.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
	               ImageIcon image=new ImageIcon(img2);
	               return image;
	           }
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
