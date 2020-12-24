package com.act.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.act.model.*;
import com.actevent.model.ActEventService;
import com.actevent.model.ActEventVO;

public class ActServlet extends HttpServlet{
		
		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			if ("getOne_For_Display".equals(action)) { // �Ӧ�act_event_select_page.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
					String str = req.getParameter("actNo");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("�п�ܽs���ζ��ئW��");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/act/act_select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
					
					String actNo = null;
					try {
						actNo = new String(str);
					} catch (Exception e) {
						errorMsgs.add("�s���榡�����T");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/act/act_select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
					
					/***************************2.�}�l�d�߸��*****************************************/
					ActService actSvc = new ActService();
					ActVO actVO = actSvc.getOneAct(actNo);
					if (actVO == null) {
						errorMsgs.add("�d�L���");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/act/act_select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
					
					/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
					req.setAttribute("actVO", actVO); // ��Ʈw���X��actEventVO����,�s�Jreq
					String url = "/back-end/act/act_listOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAct.jsp
					successView.forward(req, res);

					/***************************��L�i�઺���~�B�z*************************************/
				} catch (Exception e) {
					errorMsgs.add("�L�k���o���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/act_select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
			if ("insert".equals(action)) { // �Ӧ�addAct.jsp���ШD  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					String actNo = req.getParameter("actNo").trim();
					String actNameReg = "^(A,C,T0-9_){10}$";
					if (actNo == null || actNo.trim().length() == 0) {
						errorMsgs.add("�нT�{���ʽs���榡�O�_���T");
					} 
					
					String actEventNo = req.getParameter("actEventNo");
					if (actEventNo == null || actEventNo.trim().length() == 0) {
						errorMsgs.add("���������s��: �ФŪť�");
					}
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("���������s��: �ФŪť�");
					}
					
					String actStatus = req.getParameter("actStatus");
					
					
					java.sql.Date actRegTime = null;
					try {
						actRegTime = java.sql.Date.valueOf(req.getParameter("actRegTime").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					
					java.sql.Date deadLine = null;
					try {
						deadLine = java.sql.Date.valueOf(req.getParameter("deadLine").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					java.sql.Date actDate = null;
					try {
						actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					
					String actTime = req.getParameter("actTime");
				
					String participant = req.getParameter("participant");
					
					String actPrice = req.getParameter("actPrice");
					Integer act_price = null;
					try {
						act_price = new Integer(actPrice);
						if(act_price<=0){
							errorMsgs.add("���椣�i��0");
						}
					} catch (Exception e) {
						errorMsgs.add("���椣���T");
					}
				
					
					ActVO actVO = new ActVO();
					actVO.setActNo(actNo);
					actVO.setActEventNo(actEventNo);
					actVO.setActName(actName);
					actVO.setActStatus(actStatus);
					actVO.setActRegTime(actRegTime);
					actVO.setDeadLine(deadLine);
					actVO.setActDate(actDate);
					actVO.setActTime(actTime);
					actVO.setParticipant(participant);
					actVO.setActPrice(act_price);
				

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("actVO", actVO); // �t����J�榡���~��actEventVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/actevent/act_event_add.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�s�W���***************************************/
					ActService ActSvc = new ActService();
					actVO = ActSvc.addAct(actNo, actEventNo, actName, actStatus, actRegTime, 
							actDate, deadLine, actTime, participant, act_price);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					String url = "/back-end/act/act_listAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add("��J����Ƭ��ŭ�");
					e.printStackTrace();
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/act_add.jsp");
					failureView.forward(req, res);
				}
			} 
			
			//�R��
			if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.�����ШD�Ѽ�***************************************/
					String actNo = req.getParameter("actNo").trim();
					
					/***************************2.�}�l�R�����***************************************/
					ActService actSvc = new ActService();
					actSvc.deleteAct(actNo);
					
					/***************************3.�R������,�ǳ����(Send the Success view)***********/								
					String url = "/back-end/act/act_listAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
					successView.forward(req, res);
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add("�R����ƥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/act_listAll.jsp");
					failureView.forward(req, res);
				}
			}
			
			//�d�ߧ�s
			if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllAct.jsp���ШD

	  			List<String> errorMsgs = new LinkedList<String>();
	  			// Store this set in the request scope, in case we need to
	  			// send the ErrorPage view.
	  			req.setAttribute("errorMsgs", errorMsgs);
	  			
	  			try {
	  				/***************************1.�����ШD�Ѽ�****************************************/
	  				String actNo = new String(req.getParameter("actNo"));
	  				
	  				/***************************2.�}�l�d�߸��****************************************/
	  				ActService actSvc = new ActService();
	  				ActVO actVO = actSvc.getOneAct(actNo);
	  								
	  				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
	  				req.setAttribute("actVO", actVO);         // ��Ʈw���X��empVO����,�s�Jreq
	  				String url = "/back-end/act/act_input_update.jsp";
	  				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
	  				successView.forward(req, res);

	  				/***************************��L�i�઺���~�B�z**********************************/
	  			} catch (Exception e) {
	  				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
	  				RequestDispatcher failureView = req
	  						.getRequestDispatcher("/back-end/act/act_listAll.jsp");
	  				failureView.forward(req, res);
	  			}
	  		}
			
			//��s
	        if ("update".equals(action)) { // �Ӧ�act_update_input.jsp���ШD
	  			
	  			List<String> errorMsgs = new LinkedList<String>();
	  			// Store this set in the request scope, in case we need to
	  			// send the ErrorPage view.
	  			req.setAttribute("errorMsgs", errorMsgs);
	  		
	  			try {
	  				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
	  				String actNo = req.getParameter("actNo").trim();
					String actNameReg = "^(A,C,T0-9_){10}$";
					if (actNo == null || actNo.trim().length() == 0) {
						errorMsgs.add("�нT�{���ʽs���榡�O�_���T");
					} 
					
					String actEventNo = req.getParameter("actEventNo");
					if (actEventNo == null || actEventNo.trim().length() == 0) {
						errorMsgs.add("���������s��: �ФŪť�");
					}
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("���������s��: �ФŪť�");
					}
					
					String actStatus = req.getParameter("actStatus");
					
					
					java.sql.Date actRegTime = null;
					try {
						actRegTime = java.sql.Date.valueOf(req.getParameter("actRegTime").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					
					java.sql.Date deadLine = null;
					try {
						deadLine = java.sql.Date.valueOf(req.getParameter("deadLine").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					java.sql.Date actDate = null;
					try {
						actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					
					String actTime = req.getParameter("actTime");
				
					String participant = req.getParameter("participant");
					
					String actPrice = req.getParameter("actPrice");
					Integer act_price = null;
					try {
						act_price = new Integer(actPrice);
						if(act_price<=0){
							errorMsgs.add("���椣�i��0");
						}
					} catch (Exception e) {
						errorMsgs.add("���椣���T");
					}
				
					
					ActVO actVO = new ActVO();
					actVO.setActNo(actNo);
					actVO.setActEventNo(actEventNo);
					actVO.setActName(actName);
					actVO.setActStatus(actStatus);
					actVO.setActRegTime(actRegTime);
					actVO.setDeadLine(deadLine);
					actVO.setActDate(actDate);
					actVO.setActTime(actTime);
					actVO.setParticipant(participant);
					actVO.setActPrice(act_price);
	  				
	  				
	  				// Send the use back to the form, if there were errors
	  				if (!errorMsgs.isEmpty()) {
	  					req.setAttribute("actVO", actVO); // �t����J�榡���~��actTypeVO����,�]�s�Jreq
	  					RequestDispatcher failureView = req
	  							.getRequestDispatcher("/back-end/act/act_input_update.jsp");
	  					failureView.forward(req, res);
	  					return; //�{�����_
	  				}
	  				
	  				/***************************2.�}�l�ק���*****************************************/
	  				ActService ActSvc = new ActService();
	  				actVO = ActSvc.updateAct(actNo, actEventNo, actName, actStatus, actRegTime, actDate, deadLine, actTime, participant, act_price);
	  				
	  				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
	  				req.setAttribute("actVO", actVO); // ��Ʈwupdate���\��,���T����actTypeVO����,�s�Jreq
	  				String url = "/back-end/act/act_listAll.jsp";
	  				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
	  				successView.forward(req, res);

	  				/***************************��L�i�઺���~�B�z*************************************/
	  			} catch (Exception e) {
	  				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
	  				RequestDispatcher failureView = req
	  						.getRequestDispatcher("/back-end/act/act_input_update.jsp");
	  				failureView.forward(req, res);
	  			}
	  		}
	
       }
	}
