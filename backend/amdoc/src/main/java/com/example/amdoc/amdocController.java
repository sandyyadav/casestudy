package com.example.amdoc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.springframework.web.bind.annotation.*;




@RestController
public class amdocController {
	@CrossOrigin
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	
	public void insert(@RequestBody book b)
	{
		Connect conn=new Connect();
		try {
			String query="insert into book ( BOOK_ID, BOOK_TITLE,BOOK_AUTHOR,PRICE)"+"values(?,?,?,?)";
			PreparedStatement ps=conn.con.prepareStatement(query);
			ps.setString(1,b.getBook_id());
			ps.setString(2,b.getBook_Name());
			ps.setString(3,b.getAuthor());
			ps.setInt(4,b.getPrice());
			ps.execute();	
			conn.con.close();			}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	@CrossOrigin
	@RequestMapping(value="/getdata",method=RequestMethod.GET)
	
	public ArrayList<book> getdata()
	{
		ArrayList<book> arr=new ArrayList<book>();
		try {
		Connect conn=new Connect();
		Statement stmt=conn.con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from book");
		while(rs.next())
		{
			book b=new book();
			b.setBook_id(rs.getString(1));
			b.setBook_Name(rs.getString(2));
			b.setAuthor(rs.getString(3));
			b.setPrice(rs.getInt(4));
			arr.add(b);
		}
		conn.con.close();		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return arr ;
	}
	@CrossOrigin
	@PutMapping(value="/update/{id}")
	
	public String updatedata(@PathVariable String id,@RequestBody book b)
	{
		
		try
		{  
			int flag=0;
			Connect conn=new Connect();
			Statement stmt=conn.con.createStatement();
					if(!b.getBook_Name().isEmpty())
					{
						String query="update book set book_title='"+b.getBook_Name()+"' where book_id='"+id+"'";
						int res=stmt.executeUpdate(query);
						System.out.println(query);
						
						flag=1;
					}
					if(!b.getAuthor().isEmpty())
					{
						String query="update book set book_author='"+b.getAuthor()+"' where book_id='"+id+"'";
						int res=stmt.executeUpdate(query);
						System.out.println(query);
						
						flag=1;
					}
					if(b.getPrice()!=0)
					{
						
						String query="update book set PRICE="+b.getPrice()+"where BOOK_ID='"+b.getBook_id()+"'";
						int res=stmt.executeUpdate(query);
						flag=1;
					}
					if(flag==1)
					{
						conn.con.close();
						return "1 row updated";
					}
					else
					{
						conn.con.close();
						return "No such book id  found";
					}	
					}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return "";
	}
	@CrossOrigin
	@DeleteMapping(value="/remove/{id}")
	
	public String removedata(@PathVariable String id)
	{
		try
		{
		Connect conn=new Connect();
		Statement stmt=conn.con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from book");
		int flag=0;
		while(rs.next())
		{
			if(rs.getString(1).equals(id)) {
				flag=1;
			}
		}
		if(flag==1)
		{
		int res=stmt.executeUpdate("delete from book where book_id='"+id+"'");
		conn.con.close();
		return "1 record deleted";
		}
		else
		{
			conn.con.close();
			return "No such record found";
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
		
	}
}
