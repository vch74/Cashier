import java.sql.*;

public class DB 
{
	private String namedb;
	Connection c = null;
	Statement st = null;
	ResultSet rs;
	
	public DB(String namedb)
	{
		this.namedb = namedb;
	}
	
	public int modify(String cmd) 
	{
		Connection c = null;
		Statement st = null;
		int rc = 0;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			// Note: /test.db is the test.db in the *current* working directory
			c = DriverManager.getConnection("jdbc:sqlite:"+namedb,"","");
			st = c.createStatement();
			rc = st.executeUpdate( cmd );
			//st.close();
			//c.close();
			return rc;
		} catch ( Exception e ) 
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			e.printStackTrace();
		}
		finally
		{	try 
			{
				c.close();
				st.close();
			} catch ( Exception e ) 
			{ 
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@SuppressWarnings("finally")
	public ResultSet query(String cmd)
	{
		c = null;
		st = null;
		rs = null;
		try 
		{
			Class.forName("org.sqlite.JDBC");
			// Note: /test.db is the test.db in the *current* working directory
			c = DriverManager.getConnection("jdbc:sqlite:"+namedb,"","");
			st = c.createStatement();
			rs = st.executeQuery( cmd );

		} catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			return rs;
		}
		
	}
	
	public void closeQuery()
	{
		try
		{
			rs.close();
			st.close();
			c.close();
		} catch ( Exception e ) 
		{ 
			e.printStackTrace();
		}
	}
	
}

