package com.acttype.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ActTypeDAO implements ActTypeDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "CEA101G1";
	String password = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ACT_TYPE (ACT_TYPE_NO,ACT_TYPE_NAME) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT ACT_TYPE_NO,ACT_TYPE_NAME FROM ACT_TYPE  order By ACT_TYPE_NO";
	private static final String GET_ONE_STMT = 
			"SELECT ACT_TYPE_NO,ACT_TYPE_NAME FROM¡@ACT_TYPE where ACT_TYPE_NO = ?";
	private static final String DELETE = 
			"DELETE FROM ACT_TYPE where ACT_TYPE_NO = ?";
	private static final String UPDATE = 
			"UPDATE ACT_TYPE set ACT_TYPE_NAME=? where ACT_TYPE_NO=?";

	@Override
	public void insert(ActTypeVO actTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,actTypeVO.getActTypeNo());
			pstmt.setString(2,actTypeVO.getActTypeName());
			
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
	public void update(ActTypeVO actTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,actTypeVO.getActTypeName());
			pstmt.setString(2,actTypeVO.getActTypeNo());
			
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
	public void delete(String actTypeno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,actTypeno);
			
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
	public ActTypeVO findByPrimaryKey(String actTypeno) {
		ActTypeVO actTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,actTypeno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actTypeVO = new ActTypeVO();
				
				actTypeVO.setActTypeNo(rs.getString("ACT_TYPE_NO"));
				actTypeVO.setActTypeName(rs.getString("ACT_TYPE_NAME"));
				
		       		
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
		
		return actTypeVO;
	}
		
		
	

	@Override
	public List<ActTypeVO> getAll() {
		List<ActTypeVO> list = new ArrayList<ActTypeVO>();
		ActTypeVO actTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				actTypeVO = new ActTypeVO();
				
				actTypeVO.setActTypeNo(rs.getString("ACT_TYPE_NO"));
				actTypeVO.setActTypeName(rs.getString("ACT_TYPE_NAME"));
				list.add(actTypeVO);
		       		
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
