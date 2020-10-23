package com.idriss.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.idriss.dao.SubtitleDAO;

public class EditModel {
	private final String UPLOAD_DIRECTORY 	= "file/translate/";
	private ArrayList<String> originalSubtitles = new ArrayList<String>();
	private ArrayList<String> transleteSubtitles = new ArrayList<String>();
	
	
	public String getFile(HttpServletRequest request, String filePath) {
		BufferedReader br;
		String realPathFile = request.getServletContext().getRealPath(filePath);
		
		try {
			br = new BufferedReader(new FileReader(realPathFile));
			String line;
			
			while((line = br.readLine()) != null) {
				this.originalSubtitles.add(line);
			}
			br.close();
			return filePath;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveToDatabase(HttpServletRequest request, String filePath) {
		BufferedWriter bw;
		PrintWriter pw;
		String fileName = filePath.substring(filePath.lastIndexOf('/')+1);
		String realPathFile = request.getServletContext().getRealPath(UPLOAD_DIRECTORY+fileName);
		try {
			bw = new BufferedWriter(new FileWriter(realPathFile));
			pw = new PrintWriter(bw);
			String line;
			
			for(int i=0; i<request.getParameterMap().size()-1; i++) {
				line = request.getParameter("line"+i);
				pw.println(line);
				this.transleteSubtitles.add(line);
			}
			request.setAttribute("translateLine", this.transleteSubtitles);
			SubtitleDAO.saveSubtitlesVT(fileName, UPLOAD_DIRECTORY+fileName);
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getOriginalSubtitles() {
		return originalSubtitles;
	}

}
