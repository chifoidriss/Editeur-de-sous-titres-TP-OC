package com.idriss.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idriss.model.EditModel;

@WebServlet({"/Edit", "/edit"})
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String fileExported;
       
    public Edit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String fileSource = request.getParameter("filePath");
		System.out.println(fileSource);
		if(fileSource != null && !fileSource.isEmpty()) {
			EditModel editeur = new EditModel();
			
			this.fileExported = editeur.getFile(request, fileSource);
			if(this.fileExported != null) {
				request.setAttribute("subtitles", editeur.getOriginalSubtitles());
				this.getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request,response);
			}
		}
		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EditModel editeur = new EditModel();
		if(this.fileExported != null) {
			editeur.saveToDatabase(request, fileExported);
			request.setAttribute("filePath", fileExported);
		}else {
			request.setAttribute("error", "Fichier source introuvable!!!");
		}
		doGet(request, response);
	}

}
