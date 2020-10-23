package com.idriss.beans;

public class Subtitle {
	private int id;
	private String fileName;
	private String filePathVO;
	private String filePathVT;

	public Subtitle() {
		
	}

	public Subtitle(int id, String fileName, String filePath) {
		this.id = id;
		this.fileName = fileName;
		this.filePathVO = filePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePathVO() {
		return filePathVO;
	}

	public void setFilePathVO(String filePath) {
		this.filePathVO = filePath;
	}
	
	public String getFilePathVT() {
		return filePathVT;
	}

	public void setFilePathVT(String filePathVT) {
		this.filePathVT = filePathVT;
	}

}
