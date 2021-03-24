package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) {
	
		String jdbcURL="jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		
		try {
			Connection conn= DriverManager.getConnection(jdbcURL);
			String sql= "SELECT DISTINCT shape " 
					+ "FROM sighting " 
					+ "WHERE shape <>'' "
					+ "ORDER BY shape ASC";
			
		PreparedStatement st=conn.prepareStatement(sql);//la query la passo nel momento della preparazione
		//dello statement
		
		
		
		ResultSet res= st.executeQuery();
		
		List <String> formeUFO= new ArrayList <>();
		
		
		
		while (res.next()) {
			String forma= res.getString("shape"); //ESTRAGGO DALLA QUERY LA FORMA DALLA COLONNA shape
			
			formeUFO.add(forma);
		}
		st.close();
		System.out.println(formeUFO);
		
		String sql2=" SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? ";
		String shapeScelta="circle";
		PreparedStatement st2= conn.prepareStatement(sql2);
		st2.setString(1, shapeScelta);
		ResultSet res2= st2.executeQuery();
		res2.first();
		
		int count= res2.getInt("cnt");
		st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
