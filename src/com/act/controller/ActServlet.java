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
			
			if ("getOne_For_Display".equals(action)) { // 來自act_event_select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = req.getParameter("actNo");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請選擇編號或項目名稱");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/act/act_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					String actNo = null;
					try {
						actNo = new String(str);
					} catch (Exception e) {
						errorMsgs.add("編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/act/act_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					ActService actSvc = new ActService();
					ActVO actVO = actSvc.getOneAct(actNo);
					if (actVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/act/act_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("actVO", actVO); // 資料庫取出的actEventVO物件,存入req
					String url = "/back-end/act/act_listOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/act_select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
			if ("insert".equals(action)) { // 來自addAct.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String actNo = req.getParameter("actNo").trim();
					String actNameReg = "^(A,C,T0-9_){10}$";
					if (actNo == null || actNo.trim().length() == 0) {
						errorMsgs.add("請確認活動編號格式是否正確");
					} 
					
					String actEventNo = req.getParameter("actEventNo");
					if (actEventNo == null || actEventNo.trim().length() == 0) {
						errorMsgs.add("活動類型編號: 請勿空白");
					}
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("活動類型編號: 請勿空白");
					}
					
					String actStatus = req.getParameter("actStatus");
					
					
					java.sql.Date actRegTime = null;
					try {
						actRegTime = java.sql.Date.valueOf(req.getParameter("actRegTime").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					java.sql.Date deadLine = null;
					try {
						deadLine = java.sql.Date.valueOf(req.getParameter("deadLine").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					java.sql.Date actDate = null;
					try {
						actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					String actTime = req.getParameter("actTime");
				
					String participant = req.getParameter("participant");
					
					String actPrice = req.getParameter("actPrice");
					Integer act_price = null;
					try {
						act_price = new Integer(actPrice);
						if(act_price<=0){
							errorMsgs.add("價格不可為0");
						}
					} catch (Exception e) {
						errorMsgs.add("價格不正確");
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
						req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的actEventVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/actevent/act_event_add.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					ActService ActSvc = new ActService();
					actVO = ActSvc.addAct(actNo, actEventNo, actName, actStatus, actRegTime, 
							actDate, deadLine, actTime, participant, act_price);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/act/act_listAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("輸入的資料為空值");
					e.printStackTrace();
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/act_add.jsp");
					failureView.forward(req, res);
				}
			} 
			
			//刪除
			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					String actNo = req.getParameter("actNo").trim();
					
					/***************************2.開始刪除資料***************************************/
					ActService actSvc = new ActService();
					actSvc.deleteAct(actNo);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/back-end/act/act_listAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/act_listAll.jsp");
					failureView.forward(req, res);
				}
			}
			
			//查詢更新
			if ("getOne_For_Update".equals(action)) { // 來自listAllAct.jsp的請求

	  			List<String> errorMsgs = new LinkedList<String>();
	  			// Store this set in the request scope, in case we need to
	  			// send the ErrorPage view.
	  			req.setAttribute("errorMsgs", errorMsgs);
	  			
	  			try {
	  				/***************************1.接收請求參數****************************************/
	  				String actNo = new String(req.getParameter("actNo"));
	  				
	  				/***************************2.開始查詢資料****************************************/
	  				ActService actSvc = new ActService();
	  				ActVO actVO = actSvc.getOneAct(actNo);
	  								
	  				/***************************3.查詢完成,準備轉交(Send the Success view)************/
	  				req.setAttribute("actVO", actVO);         // 資料庫取出的empVO物件,存入req
	  				String url = "/back-end/act/act_input_update.jsp";
	  				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
	  				successView.forward(req, res);

	  				/***************************其他可能的錯誤處理**********************************/
	  			} catch (Exception e) {
	  				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
	  				RequestDispatcher failureView = req
	  						.getRequestDispatcher("/back-end/act/act_listAll.jsp");
	  				failureView.forward(req, res);
	  			}
	  		}
			
			//更新
	        if ("update".equals(action)) { // 來自act_update_input.jsp的請求
	  			
	  			List<String> errorMsgs = new LinkedList<String>();
	  			// Store this set in the request scope, in case we need to
	  			// send the ErrorPage view.
	  			req.setAttribute("errorMsgs", errorMsgs);
	  		
	  			try {
	  				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
	  				String actNo = req.getParameter("actNo").trim();
					String actNameReg = "^(A,C,T0-9_){10}$";
					if (actNo == null || actNo.trim().length() == 0) {
						errorMsgs.add("請確認活動編號格式是否正確");
					} 
					
					String actEventNo = req.getParameter("actEventNo");
					if (actEventNo == null || actEventNo.trim().length() == 0) {
						errorMsgs.add("活動類型編號: 請勿空白");
					}
					String actName = req.getParameter("actName");
					if (actName == null || actName.trim().length() == 0) {
						errorMsgs.add("活動類型編號: 請勿空白");
					}
					
					String actStatus = req.getParameter("actStatus");
					
					
					java.sql.Date actRegTime = null;
					try {
						actRegTime = java.sql.Date.valueOf(req.getParameter("actRegTime").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					java.sql.Date deadLine = null;
					try {
						deadLine = java.sql.Date.valueOf(req.getParameter("deadLine").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					java.sql.Date actDate = null;
					try {
						actDate = java.sql.Date.valueOf(req.getParameter("actDate").trim());
					} catch (IllegalArgumentException e) {
						actRegTime = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}
					
					String actTime = req.getParameter("actTime");
				
					String participant = req.getParameter("participant");
					
					String actPrice = req.getParameter("actPrice");
					Integer act_price = null;
					try {
						act_price = new Integer(actPrice);
						if(act_price<=0){
							errorMsgs.add("價格不可為0");
						}
					} catch (Exception e) {
						errorMsgs.add("價格不正確");
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
	  					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的actTypeVO物件,也存入req
	  					RequestDispatcher failureView = req
	  							.getRequestDispatcher("/back-end/act/act_input_update.jsp");
	  					failureView.forward(req, res);
	  					return; //程式中斷
	  				}
	  				
	  				/***************************2.開始修改資料*****************************************/
	  				ActService ActSvc = new ActService();
	  				actVO = ActSvc.updateAct(actNo, actEventNo, actName, actStatus, actRegTime, actDate, deadLine, actTime, participant, act_price);
	  				
	  				/***************************3.修改完成,準備轉交(Send the Success view)*************/
	  				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的actTypeVO物件,存入req
	  				String url = "/back-end/act/act_listAll.jsp";
	  				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
	  				successView.forward(req, res);

	  				/***************************其他可能的錯誤處理*************************************/
	  			} catch (Exception e) {
	  				errorMsgs.add("修改資料失敗:"+e.getMessage());
	  				RequestDispatcher failureView = req
	  						.getRequestDispatcher("/back-end/act/act_input_update.jsp");
	  				failureView.forward(req, res);
	  			}
	  		}
	
       }
	}
