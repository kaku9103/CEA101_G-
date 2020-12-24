package com.actorder.model;

import java.util.*;
import java.sql.*;

public class ActOrderDAO implements ActOrderDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "CEA101G1";
	String password = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO ACT_ORDER(ACT_ODNO,ACT_NO,MB_ID,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE)"
			+ "VALUES('ACTOD' || LPAD(to_char(ACTODNO_SEQ.NEXTVAL), 10, '0') ,? ,? ,? ,? ,? ,?)";
	
	private static final String GET_ALL_STMT =
			"SELECT ACT_ODNO,ACT_NO,MB_ID,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE FROM ACT_ORDER order By ACT_ODNO";
	
	private static final String GET_ONE_STMT = 
			"SELECT ACT_ODNO,ACT_NO,MB_ID,OD_TIME,OD_STATUS,PPL,TOTAL_PRICE FROM ACT_ORDER where ACT_ODNO = ?";
	
	private static final String DELETE =
			"DELETE FROM ACT_ORDER where ACT_ODNO = ?";
	
	private static final String UPDATE =
			"UPDATE ACT_ORDER SET ACT_NO=?,MB_ID=?,OD_TIME=?,OD_STATUS=?,PPL=?,TOTAL_PRICE=? where ACT_ODNO = ?";
	
	@Override
	public void insert(ActOrderVO actOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actOrderVO.getActNo());
			pstmt.setString(2,actOrderVO.getMbId());
			pstmt.setDate(3,actOrderVO.getOdTime());
			pstmt.setString(4,actOrderVO.getOdStatus());
			pstmt.setInt(5,actOrderVO.getPpl());
			pstmt.setInt(6,actOrderVO.getTotalPrice());
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}
	@Override
	public void update(ActOrderVO actOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,actOrderVO.getActNo());
			pstmt.setString(2,actOrderVO.getMbId());
			pstmt.setDate(3,actOrderVO.getOdTime());
			pstmt.setString(4,actOrderVO.getOdStatus());
			pstmt.setInt(5,actOrderVO.getPpl());
			pstmt.setInt(6,actOrderVO.getTotalPrice());
			pstmt.setString(7,actOrderVO.getActOdno());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void delete(String actOdno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actOdno);
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public ActOrderVO findByPrimaryKey(String actOdno) {
		ActOrderVO actOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,actOdno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actOrderVO = new ActOrderVO();
				actOrderVO.setActOdno(rs.getString("ACT_ODNO"));
				actOrderVO.setActNo(rs.getString("ACT_NO"));
				actOrderVO.setMbId(rs.getString("MB_ID"));
				actOrderVO.setOdTime(rs.getDate("OD_TIME"));
				actOrderVO.setOdStatus(rs.getString("OD_STATUS"));
				actOrderVO.setPpl(rs.getInt("PPL"));
				actOrderVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));
		       		
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return actOrderVO;
	}
	@Override
	public List<ActOrderVO> getAll() {
		List<ActOrderVO> list = new ArrayList<ActOrderVO>();
		ActOrderVO actOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
                actOrderVO = new ActOrderVO();
				actOrderVO.setActOdno(rs.getString("ACT_ODNO"));
				actOrderVO.setActNo(rs.getString("ACT_NO"));
				actOrderVO.setMbId(rs.getString("MB_ID"));
				actOrderVO.setOdTime(rs.getDate("OD_TIME"));
				actOrderVO.setOdStatus(rs.getString("OD_STATUS"));
				actOrderVO.setPpl(rs.getInt("PPL"));
				actOrderVO.setTotalPrice(rs.getInt("TOTAL_PRICE"));
				list.add(actOrderVO);
		       		
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
		            +se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

}
