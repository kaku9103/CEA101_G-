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
		

        //查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String actPicNo = req.getParameter("actPicNo");
				if (actPicNo == null || (actPicNo.trim()).length() == 0) {
					errorMsgs.add("請選擇照片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
//				String actPicNo = null;
//				try {
//					actPicNo = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				ActPicService actPicSvc = new ActPicService();
				ActPicVO actPicVO =  actPicSvc.getOneActPic(actPicNo);
				
				if (actPicVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actPicVO", actPicVO); // 資料庫取出的actTypeVO物件,存入req
				String url = "/back-end/actpic/act_pic_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpic/act_pic_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//新增
        if ("insert".equals(action)) { // 來自addAct.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String actEventNo = req.getParameter("ActEventNo");
				if (actEventNo == null || actEventNo.trim().length() == 0) {
					errorMsgs.add("活動項目編號: 請勿空白");
				}
				
				String actPicNo = new String(req.getParameter("ActPicNo").trim());
				if (actPicNo == null || actPicNo.trim().length() == 0) {
					errorMsgs.add("照片編號: 請勿空白");
				} else if(actPicNo.equals("ActPicNo")) {
					errorMsgs.add(actPicNo);
				}
				
				//照片上傳
				Part actPic = req.getPart("ActPic");
				if(actPic == null) {
					errorMsgs.add("請新增照片");
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
					req.setAttribute("actPicVO", actPicVO); // 含有輸入格式錯誤的actTypeVO物件,也存入req
					errorMsgs.add("請重新確認輸入資料內容");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/actpic/act_pic_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActPicService actPicSvc = new ActPicService();
				actPicVO =  actPicSvc.addActPic(actPicNo, actEventNo,act_pic);
			
		
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/actpic/act_pic_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("輸入的資料為空值");
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpic/act_pic_add.jsp");
				failureView.forward(req, res);
			}
		} 
        
      //刪除
        if ("delete".equals(action)) { // 來自listAllAct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String actPicNo = req.getParameter("ActPicNo");
				
				/***************************2.開始刪除資料***************************************/
				ActPicService ActPicSvc = new ActPicService();
				ActPicSvc.deleteActPic(actPicNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/actpic/act_pic_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/actpic/act_pic_listAll.jsp");
				failureView.forward(req, res);
			}
		}
        
      //更新
      		if ("getOne_For_Update".equals(action)) { // 來自listAllAct.jsp的請求

      			List<String> errorMsgs = new LinkedList<String>();
      			// Store this set in the request scope, in case we need to
      			// send the ErrorPage view.
      			req.setAttribute("errorMsgs", errorMsgs);
      			
      			try {
      				/***************************1.接收請求參數****************************************/
      				String actPicNo = new String(req.getParameter("ActPicNo"));
      				
      				/***************************2.開始查詢資料****************************************/
      				ActPicService actPicSvc = new ActPicService();
      				ActPicVO actPicVO = actPicSvc.getOneActPic(actPicNo);
      								
      				/***************************3.查詢完成,準備轉交(Send the Success view)************/
      				req.setAttribute("actPicVO", actPicVO);         // 資料庫取出的empVO物件,存入req
      				String url = "/back-end/actpic/act_pic_input_update.jsp";
      				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
      				successView.forward(req, res);

      				/***************************其他可能的錯誤處理**********************************/
      			} catch (Exception e) {
      				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
      				RequestDispatcher failureView = req
      						.getRequestDispatcher("/back-end/actpic/act_pic_listAll.jsp");
      				failureView.forward(req, res);
      			}
      		}
      		
                  if ("update".equals(action)) { // 來自update_act_type_input.jsp的請求
      			
      			List<String> errorMsgs = new LinkedList<String>();
      			// Store this set in the request scope, in case we need to
      			// send the ErrorPage view.
      			req.setAttribute("errorMsgs", errorMsgs);
      		
      			try {
      				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
      				String actPicNo = new String(req.getParameter("ActPicNo").trim());
      				if(actPicNo == null || actPicNo.trim().length() == 0) {
      					errorMsgs.add("請輸入種類編號");
      				} 
      				
      				String actEventNo = req.getParameter("ActEventNo");
//      		    String actNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
      				if (actEventNo == null || actEventNo.trim().length() == 0) {
      					errorMsgs.add("活動類型名稱: 請勿空白");
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
      					req.setAttribute("actPicVO", actPicVO); // 含有輸入格式錯誤的actTypeVO物件,也存入req
      					RequestDispatcher failureView = req
      							.getRequestDispatcher("/back-end/actpic/act_pic_input_update.jsp");
      					failureView.forward(req, res);
      					return; //程式中斷
      				}
      				
      				/***************************2.開始修改資料*****************************************/
      				ActPicService ActPicSvc = new ActPicService();
      				actPicVO = ActPicSvc.updateActPic(actPicNo, actEventNo, ActPic);
      				
      				/***************************3.修改完成,準備轉交(Send the Success view)*************/
      				req.setAttribute("actPicVO", actPicVO); // 資料庫update成功後,正確的的actPicVO物件,存入req
      				String url = "/back-end/actpic/act_pic_listAll.jsp";
      				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
      				successView.forward(req, res);

      				/***************************其他可能的錯誤處理*************************************/
      			} catch (Exception e) {
      				errorMsgs.add("修改資料失敗:"+e.getMessage());
      				RequestDispatcher failureView = req
      						.getRequestDispatcher("/back-end/actpic/act_pic_input_update.jsp");
      				failureView.forward(req, res);
      			}
      		}
	}
}


