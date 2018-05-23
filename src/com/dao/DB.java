package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB
{
	private Connection con;

	private PreparedStatement pstm;

	private String user = "root";

	private String password = "zz123456";

	private String className = "com.mysql.jdbc.Driver";

	private String url = "jdbc:mysql://localhost:3306/db_wlgw?useUnicode=true&amp;amp;amp;amp;amp;amp;characterEncoding=utf-8";

	public DB()
	{
		try
		{
			Class.forName(className);
		} catch (ClassNotFoundException e)
		{
			System.out.println("锟斤拷锟斤拷锟斤拷菘锟斤拷锟绞э拷埽锟�");
			e.printStackTrace();
		}
	}

	/** 锟斤拷锟斤拷锟斤拷菘锟斤拷锟斤拷锟� */
	public Connection getCon()
	{
		try
		{
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e)
		{
			System.out.println("锟斤拷锟斤拷锟斤拷菘锟斤拷锟斤拷锟绞э拷埽锟�");
			con = null;
			e.printStackTrace();
		}
		return con;
	}

	public void doPstm(String sql, Object[] params)
	{
		if (sql != null && !sql.equals(""))
		{
			if (params == null)
				params = new Object[0];

			getCon();
			if (con != null)
			{
				try
				{
					System.out.println(sql);
					pstm = con.prepareStatement(sql,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					for (int i = 0; i < params.length; i++)
					{
						pstm.setObject(i + 1, params[i]);
					}
					pstm.execute();
				} catch (SQLException e)
				{
					System.out.println("doPstm()锟斤拷锟斤拷锟斤拷锟�?");
					e.printStackTrace();
				}
			}
		}
	}

	public ResultSet getRs() throws SQLException
	{
		return pstm.getResultSet();
	}

	public int getCount() throws SQLException
	{
		return pstm.getUpdateCount();
	}

	public void closed()
	{
		try
		{
			if (pstm != null)
				pstm.close();
		} catch (SQLException e)
		{
			System.out.println("锟截憋拷pstm锟斤拷锟斤拷失锟杰ｏ拷");
			e.printStackTrace();
		}
		try
		{
			if (con != null)
			{
				con.close();
			}
		} catch (SQLException e)
		{
			System.out.println("锟截憋拷con锟斤拷锟斤拷失锟杰ｏ拷");
			e.printStackTrace();
		}
	}
}
