package com.idriss.model;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.idriss.dao.SubtitleDAO;

public class UploadModel {
	private final static String UPLOAD_DIRECTORY 	= "file/original/";
	private final static int MEMORY_THRESHOLD 	= 1024 * 1024 *5; 	// 5MB
	private final static int MAX_FILE_SIZE 	= 1024 * 1024 * 40; // 40MB
	private final static int MAX_REQUEST_SIZE	= 1024 * 1024 * 50; // 50MB

	public UploadModel() {  }
	
	public static void upload(HttpServletRequest request) {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);	
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		String uploadPath = request.getServletContext().getRealPath("")+"/"+UPLOAD_DIRECTORY;
		
		try {
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String fileExt = fileName.substring(fileName.lastIndexOf('.'));
						
						if(!fileExt.equalsIgnoreCase(".srt")) {
							request.setAttribute("error","Fichier de Sous-titres invalide!!!");
							return ;
						}
						
						String filePath = uploadPath+fileName;
						File storeFile = new File(filePath);
						item.write(storeFile);
						
						filePath = UPLOAD_DIRECTORY+fileName;
						
						boolean bool = SubtitleDAO.saveSubtitlesVO(fileName, filePath);
						if(bool) {
							request.setAttribute("success","Fichier de Sous-titre uploadé avec success!!!");
							return ;
						}
					}
				}
			} else {
				request.setAttribute("error","Erreur rencontrée!!!");
			}
		} catch (Exception e) {
			request.setAttribute("error","Erreur rencontrée: "+e.getMessage());
		}
	}
}
