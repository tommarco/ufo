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
		
		String sql2=" SELECT COUNT(*) AS cnt FROM sighting WHERE shape= ? "; //PASSO 1 SCRIVO LA QUERY
		String shapeScelta="circle";
		PreparedStatement st2= conn.prepareStatement(sql2); // PASSO 2 PREPARO LO STATEMENT E RICEVE LA STRINGA
		// CONTENENTE I PARAMETRI
		st2.setString(1, shapeScelta); // IMPOSTO IL VALORE DEI PARAMETRI IN QUESTO SI HA UN PARAMETRO IN CUI
		// NEL PUNTO INTERROGATIVO SI VA A SOSTITUIRE IL VALORE SHAPESCELTA
		ResultSet res2= st2.executeQuery();
		res2.first(); // METTO QUESTO PERCHè ESSENDO CHE MI RESTITUISCE SOLO UNA RIGA
		// USO .FIRST
		
		int count= res2.getInt("cnt");
		st.close();
		
		System.out.println("UFO di forma "+shapeScelta+" sono: "+count);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
