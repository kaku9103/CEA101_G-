package com.actpic.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.actpic.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ActPicServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

        //�d��
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String actPicNo = req.getParameter("actPicNo");
				if (actPicNo == null || (actPicNo.trim()).length() == 0) {
					errorMsgs.add("�п�ܷӤ��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				
//				String actPicNo = null;
//				try {
//					actPicNo = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ActPicService actPicSvc = new ActPicService();
				ActPicVO actPicVO =  actPicSvc.getOneActPic(actPicNo);
				
				if (actPicVO == null) {
					errorMsgs.add("�d�L���");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("actPicVO", actPicVO); // ��Ʈw���X��actTypeVO����,�s�Jreq
				String url = "/back-end/actpic/act_pic_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAct.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//�s�W
        if ("insert".equals(action)) { // �Ӧ�addAct.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String actEventNo = req.getParameter("ActEventNo");
				if (actEventNo == null || actEventNo.trim().length() == 0) {
					errorMsgs.add("���ʶ��ؽs��: �ФŪť�");
				}
				
				String actPicNo = new String(req.getParameter("ActPicNo").trim());
				if (actPicNo == null || actPicNo.trim().length() == 0) {
					errorMsgs.add("�Ӥ��s��: �ФŪť�");
				} else if(actPicNo.equals("ActPicNo")) {
					errorMsgs.add(actPicNo);
				}
				
				//�Ӥ��W��
				Part actPic = req.getPart("ActPic");
				if(actPic == null) {
					errorMsgs.add("�зs�W�Ӥ�");
				}
				InputStream picIn = actPic.getInputStream();
				byte[] act_pic = new byte[picIn.available()];
				picIn.read(act_pic);
				picIn.close();

				ActPicVO actPicVO = new ActPicVO();
				actPicVO.setActPicNo(actPicNo);
				actPicVO.setActEventNo(actEventNo);
				actPicVO.setActPic(act_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actPicVO", actPicVO); // �t����J�榡���~��actTypeVO����,�]�s�Jreq
					errorMsgs.add("�Э��s�T�{��J��Ƥ��e");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpic/act_pic_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ActPicService actPicSvc = new ActPicService();
				actPicVO =  actPicSvc.addActPic(actPicNo, actEventNo,act_pic);
			
		
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/actpic/act_pic_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("��J����Ƭ��ŭ�");
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpic/act_pic_add.jsp");
				failureView.forward(req, res);
			}
		} 
        
      //�R��
        if ("delete".equals(action)) { // �Ӧ�listAllAct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String actPicNo = req.getParameter("ActPicNo");
				
				/***************************2.�}�l�R�����***************************************/
				ActPicService ActPicSvc = new ActPicService();
				ActPicSvc.deleteActPic(actPicNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/actpic/act_pic_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpic/act_pic_listAll.jsp");
				failureView.forward(req, res);
			}
		}
        
      //��s
      		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllAct.jsp���ШD

      			List<String> errorMsgs = new LinkedList<String>();
      			// Store this set in the request scope, in case we need to
      			// send the ErrorPage view.
      			req.setAttribute("errorMsgs", errorMsgs);
      			
      			try {
      				/***************************1.�����ШD�Ѽ�****************************************/
      				String actPicNo = new String(req.getParameter("ActPicNo"));
      				
      				/***************************2.�}�l�d�߸��****************************************/
      				ActPicService actPicSvc = new ActPicService();
      				ActPicVO actPicVO = actPicSvc.getOneActPic(actPicNo);
      								
      				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
      				req.setAttribute("actPicVO", actPicVO);         // ��Ʈw���X��empVO����,�s�Jreq
      				String url = "/back-end/actpic/act_pic_input_update.jsp";
      				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
      				successView.forward(req, res);

      				/***************************��L�i�઺���~�B�z**********************************/
      			} catch (Exception e) {
      				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
      				RequestDispatcher failureView = req
      						.getRequestDispatcher("/back-end/actpic/act_pic_listAll.jsp");
      				failureView.forward(req, res);
      			}
      		}
      		
                  if ("update".equals(action)) { // �Ӧ�update_act_type_input.jsp���ШD
      			
      			List<String> errorMsgs = new LinkedList<String>();
      			// Store this set in the request scope, in case we need to
      			// send the ErrorPage view.
      			req.setAttribute("errorMsgs", errorMsgs);
      		
      			try {
      				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
      				String actPicNo = new String(req.getParameter("ActPicNo").trim());
      				if(actPicNo == null || actPicNo.trim().length() == 0) {
      					errorMsgs.add("�п�J�����s��");
      				} 
      				
      				String actEventNo = req.getParameter("ActEventNo");
//      		    String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
      				if (actEventNo == null || actEventNo.trim().length() == 0) {
      					errorMsgs.add("���������W��: �ФŪť�");
      				} 
      				
      				Part actPic = req.getPart("ActPic");
    				InputStream picIn = actPic.getInputStream();
    				byte[] ActPic = new byte[picIn.available()];
    				picIn.read(ActPic);
    				picIn.close();

    				ActPicVO actPicVO = new ActPicVO();
    				actPicVO.setActPicNo(actPicNo);
    				actPicVO.setActEventNo(actEventNo);
    				actPicVO.setActPic(ActPic);
      				

      				
      				// Send the use back to the form, if there were errors
      				if (!errorMsgs.isEmpty()) {
      					req.setAttribute("actPicVO", actPicVO); // �t����J�榡���~��actTypeVO����,�]�s�Jreq
      					RequestDispatcher failureView = req
      							.getRequestDispatcher("/back-end/actpic/act_pic_input_update.jsp");
      					failureView.forward(req, res);
      					return; //�{�����_
      				}
      				
      				/***************************2.�}�l�ק���*****************************************/
      				ActPicService ActPicSvc = new ActPicService();
      				actPicVO = ActPicSvc.updateActPic(actPicNo, actEventNo, ActPic);
      				
      				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
      				req.setAttribute("actPicVO", actPicVO); // ��Ʈwupdate���\��,���T����actPicVO����,�s�Jreq
      				String url = "/back-end/actpic/act_pic_listAll.jsp";
      				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
      				successView.forward(req, res);

      				/***************************��L�i�઺���~�B�z*************************************/
      			} catch (Exception e) {
      				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
      				RequestDispatcher failureView = req
      						.getRequestDispatcher("/back-end/actpic/act_pic_input_update.jsp");
      				failureView.forward(req, res);
      			}
      		}
	}
}


