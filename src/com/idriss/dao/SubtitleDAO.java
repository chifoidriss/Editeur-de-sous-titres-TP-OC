package com.idriss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.idriss.beans.Subtitle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SubtitleDAO {
	
	private static final Connection BDD = DAOFactory.getConnection();

	public static boolean saveSubtitlesVO(String fileName, String filePath) {
		try {
			if(!existToDatabase(fileName)) {
				String query = "INSERT INTO subtitle(fileName,filePathVO) VALUES(?,?)";
				
				PreparedStatement prepare = BDD.prepareStatement(query);
				prepare.setString(1, fileName);
				prepare.setString(2, filePath);
				
				int bienEnregistrer = prepare.executeUpdate();
				if(bienEnregistrer == 1) {
					return true;
				}else {
					return false;
				}
			}else {
				return true;
			}
		}catch(SQLException e) {
			System.out.println("Error SQL: "+e);
		}
		return false;
	}
	
	public static boolean saveSubtitlesVT(String fileName, String filePath) {
		try {
			if(existToDatabase(fileName)) {
				String query = "UPDATE subtitle SET filePathVT=? WHERE fileName=?";
				
				PreparedStatement prepare = BDD.prepareStatement(query);
				prepare.setString(1, filePath);
				prepare.setString(2, fileName);
				
				int bienEnregistrer = prepare.executeUpdate();
				if(bienEnregistrer==1) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}catch(SQLException e) {
			System.out.println("Error SQL: "+e);
		}
		return false;
	}
	
	public static ArrayList<Subtitle> getSubtitlesToDatabase(){
		String query = "SELECT * FROM subtitle ORDER BY id DESC";
		ArrayList<Subtitle> subtitles = new ArrayList<Subtitle>();
		
		try {
			Statement st = BDD.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Subtitle subtitle = new Subtitle();
				
				subtitle.setId(rs.getInt("id"));
				subtitle.setFileName(rs.getString("fileName"));
				subtitle.setFilePathVO(rs.getString("filePathVO"));
				subtitle.setFilePathVT(rs.getString("filePathVT"));
				
				subtitles.add(subtitle);
			}
		}catch(SQLException e) {
			System.out.println("Error SQL: "+e);
		}
		return subtitles;
	}
	
	private static boolean existToDatabase(String fileName) {
		String query = "SELECT * FROM subtitle WHERE fileName LIKE '"+fileName+"'";
		
		try {
			Statement st = BDD.createStatement();
			ResultSet rs = st.executeQuery(query);
			int i =0;
			while(rs.next()) {
				i++;
			}
			if(i==1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			System.out.println("Error SQL: "+e);
		}
		return false;
	}
}
