package com.actorder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.actevent.model.ActEventService;
import com.actevent.model.ActEventVO;
import com.actorder.model.*;


public class ActOrderServlet extends HttpServlet{
	
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
						String actOdno = req.getParameter("actOdno");
						if (actOdno == null || (actOdno.trim()).length() == 0) {
							errorMsgs.add("請輸入訂單編號");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/act_order/act_order_select_page.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						
//						String act_odno = null;
//						try {
//							act_odno = new String(actOdno);
//						} catch (Exception e) {
//							errorMsgs.add("編號格式不正確");
//						}
//						// Send the use back to the form, if there were errors
//						if (!errorMsgs.isEmpty()) {
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/back-end/actorder/act_order_select_page.jsp");
//							failureView.forward(req, res);
//							return;//程式中斷
//						}
						
						/***************************2.開始查詢資料*****************************************/
						ActOrderService actOrderSvc = new ActOrderService();
						ActOrderVO actOrderVO =   actOrderSvc.getOneActOrder(actOdno);
						
						if (actOrderVO == null) {
							errorMsgs.add("查無資料");
						}
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/act_order/act_order_select_page.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
						req.setAttribute("actOrderVO", actOrderVO); // 資料庫取出的actTypeVO物件,存入req
						String url = "/back-end/backend-listOne.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/act_order/act_order_select_page.jsp");
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
						String actOdno = new String(req.getParameter("actOdno"));
						
						/***************************2.開始查詢資料****************************************/
						ActOrderService actOrderSvc = new ActOrderService();
						ActOrderVO actOrderVO = actOrderSvc.getOneActOrder(actOdno);
										
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("actOrderVO", actOrderVO);         // 資料庫取出的empVO物件,存入req
						String url = "/back-end/actorder/act_order_update.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/actorder/act_order_listAll.jsp");
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
						String actOdno = new String(req.getParameter("actOdno").trim());
						if(actOdno == null || actOdno.trim().length() == 0) {
							errorMsgs.add("訂單編號");
						}
						
						String odStatus = req.getParameter("odStatus");
						if (odStatus == null || odStatus.trim().length() == 0) {
							errorMsgs.add("訂單狀態");
						} 
						
						String actNo = req.getParameter("actNo");
						if (actNo == null || actNo.trim().length() == 0) {
							errorMsgs.add("活動編號: 請勿空白");
						} 
						
						String ppl = req.getParameter("ppl");
						if (ppl == null || ppl.trim().length() == 0) {
							errorMsgs.add("參加人數: 請勿空白");
						}
						Integer Numppl = null;
						try {
						   Numppl = new Integer(ppl);
						} catch (Exception e) {
							errorMsgs.add("編號格式不正確");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/actorder/act_order_select_page.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
						
						String mbId = req.getParameter("mbId").trim();
						if (mbId == null || mbId.trim().length() == 0) {
							errorMsgs.add("請確認活動編號格式是否正確");
						} 
						
						java.sql.Date odTime = null;
						try {
							odTime = java.sql.Date.valueOf(req.getParameter("odTime").trim());
						} catch (IllegalArgumentException e) {
							odTime = new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入日期!");
						}
						
						String totalPrice = req.getParameter("totalPrice");
						if (totalPrice == null || totalPrice.trim().length() == 0) {
							errorMsgs.add("參加人數: 請勿空白");
						} 
						Integer total_price = null;
						try {
							   total_price = new Integer(totalPrice);
							} catch (Exception e) {
								errorMsgs.add("編號格式不正確");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/back-end/actorder/act_order_select_page.jsp");
								failureView.forward(req, res);
								return;//程式中斷
							}
							
						
						ActOrderVO actOrderVO = new ActOrderVO();
						actOrderVO.setActOdno(actOdno);
						actOrderVO.setActNo(actNo);
						actOrderVO.setMbId(mbId);
						actOrderVO.setOdTime(odTime);
						actOrderVO.setOdStatus(odStatus);
						actOrderVO.setPpl(Numppl);
						actOrderVO.setTotalPrice(total_price);
						
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("actOrderVO", actOrderVO); // 含有輸入格式錯誤的actTypeVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/actorder/act_order_update.jsp");
							failureView.forward(req, res);
							return; //程式中斷
						}
						
						/***************************2.開始修改資料*****************************************/
						ActOrderService ActOrderSvc = new ActOrderService();
						actOrderVO = ActOrderSvc.updateActOrder(actOdno, actNo, mbId, odTime, odStatus, Numppl, total_price);
						
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						req.setAttribute("actOrderVO", actOrderVO); // 資料庫update成功後,正確的的actOrderVO物件,存入req
						String url = "/back-end/actorder/act_order_listAll.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/actorder/act_order_update.jsp");
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
		        			String actOdno = new String(req.getParameter("actOdno").trim());
							if(actOdno == null || actOdno.trim().length() == 0) {
								errorMsgs.add("訂單編號");
							}else if(actOdno.equals("actOdno")) {
								errorMsgs.add("已有相同訂單編號");
							}
							
							String odStatus = req.getParameter("odStatus");
							if (odStatus == null || odStatus.trim().length() == 0) {
								errorMsgs.add("訂單狀態");
							} 
							
							String actNo = req.getParameter("actNo");
							if (actNo == null || actNo.trim().length() == 0) {
								errorMsgs.add("活動編號: 請勿空白");
							} 
							
							String ppl = req.getParameter("ppl");
							if (ppl == null || ppl.trim().length() == 0) {
								errorMsgs.add("參加人數: 請勿空白");
							}
							Integer Numppl = null;
							try {
							   Numppl = new Integer(ppl);
							} catch (Exception e) {
								errorMsgs.add("編號格式不正確");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/back-end/actorder/act_order_add.jsp");
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							String mbId = req.getParameter("mbId").trim();
							if (mbId == null || mbId.trim().length() == 0) {
								errorMsgs.add("請確認活動編號格式是否正確");
							} 
							
							java.sql.Date odtime = null;
							try {
								odtime = java.sql.Date.valueOf(req.getParameter("odTime").trim());
							} catch (IllegalArgumentException e) {
								odtime = new java.sql.Date(System.currentTimeMillis());
								errorMsgs.add("請輸入日期!");
							}
							
							String totalPrice = req.getParameter("totalPrice");
							if (totalPrice == null || totalPrice.trim().length() == 0) {
								errorMsgs.add("參加人數: 請勿空白");
							} 
							Integer total_price = null;
							try {
								   total_price = new Integer(totalPrice);
								} catch (Exception e) {
									errorMsgs.add("編號格式不正確");
								}
								// Send the use back to the form, if there were errors
								if (!errorMsgs.isEmpty()) {
									RequestDispatcher failureView = req
											.getRequestDispatcher("/back-end/actorder/act_order_add.jsp");
									failureView.forward(req, res);
									return;//程式中斷
								}
								
							
							ActOrderVO actOrderVO = new ActOrderVO();
							actOrderVO.setActOdno(actOdno);
							actOrderVO.setActNo(actNo);
							actOrderVO.setMbId(mbId);
							actOrderVO.setOdTime(odtime);
							actOrderVO.setOdStatus(odStatus);
							actOrderVO.setPpl(Numppl);
							actOrderVO.setTotalPrice(total_price);

		        			// Send the use back to the form, if there were errors
		        			if (!errorMsgs.isEmpty()) {
		        				req.setAttribute("actOrderVO", actOrderVO); // 含有輸入格式錯誤的actEventVO物件,也存入req
		        				RequestDispatcher failureView = req
		        						.getRequestDispatcher("/back-end/actorder/act_order_add.jsp");
		        				failureView.forward(req, res);
		        				return;
		        			}
		        			
		        			/***************************2.開始新增資料***************************************/
		        			ActOrderService ActOrderSvc = new ActOrderService();
		        			actOrderVO = ActOrderSvc.addActOrder(actOdno, actNo, mbId, odtime, odStatus, Numppl, total_price);
		        			
		        			/***************************3.新增完成,準備轉交(Send the Success view)***********/
		        			String url = "/back-end/actorder/act_order_listAll.jsp";
		        			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
		        			successView.forward(req, res);				
		        			
		        			/***************************其他可能的錯誤處理**********************************/
		        		} catch (Exception e) {
		        			errorMsgs.add("輸入的資料為空值");
		        			e.printStackTrace();
		        			RequestDispatcher failureView = req
		        					.getRequestDispatcher("/back-end/actorder/act_order_add.jsp");
		        			failureView.forward(req, res);
		        		}
		        	} 
		
		}

}
