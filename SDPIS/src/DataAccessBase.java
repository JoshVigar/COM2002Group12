import java.util.*;
import java.sql.*;

public class DataAccessBase {

	private String _connectionString;
	private Statement _statement;
	
	public DataAccessBase(String connectionString){
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		_connectionString = connectionString;
		
	}

	private Connection getConnection(string connectionString)
	{   
		Connection SqlCon = DriverManager.getConnection(connectionString);
		SqlCon.Open();
		return SqlCon;
	}

	protected String getProcedure(String fstPart, String[] sndPart){
		String procedure = fstPart;
		for(i=0;i<sndPart.length();i++){
			if(i != 0)
				procedure += ", " + sndPart[i];
			else
				procedure += sndPart[i];
		}
		return procedure
	}
	
	protected void updateData(String update){
		try(Connection sqlCon = GetConnection(_connectionString)){
			_statement = sqlCon.createStatement();		
			ResultSet rs = stmt.executeUpdate(update);
			return rs;
		}
		catch (SQLException ex) {
			ex.printStackTrace(); }
		finally {
			if (con != null){
				con.close();
			}
	}
	
	protected List<T> getData(String query){
		try(Connection sqlCon = GetConnection(_connectionString)){
			_statement = sqlCon.createStatement();		
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch (SQLException ex) {
			ex.printStackTrace(); }
		finally {
			if (con != null){
				con.close();
				return rs;
			}
		}		
	}
	
}