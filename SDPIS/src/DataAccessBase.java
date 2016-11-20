import java.sql.*;

public class DataAccessBase {

	private String _connectionString;
	private Statement _statement;
	private int _result;
	private ResultSet _rs;

	public DataAccessBase(String connectionString)throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
		_connectionString = connectionString;

	}

	//create a connection
	private Connection getConnection()throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection SqlCon = DriverManager.getConnection(_connectionString);
		return SqlCon;
	}

	/*protected String getProcedure(String fstPart, String[] sndPart){
		String procedure = fstPart;
		for(int i=0;i<sndPart.length;i++){
			if(i != 0)
				procedure += ", " + sndPart[i];
			else
				procedure += sndPart[i];
		}
		return procedure;
	}*/

	//updates the database returns a 0 if failed and a 1 if succeeded
	protected int updateData(String update){
		try{
			Connection sqlCon = getConnection();
			_statement = sqlCon.createStatement();
			_result = _statement.executeUpdate(update);
			sqlCon.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			return _result;
		}
	}

	//gets the data from the database
	protected ResultSet getData(String query) {
		try{
			Connection sqlCon = getConnection();
			_statement = sqlCon.createStatement();
			_rs = _statement.executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (_rs != null) {
				return _rs;
			}
			return null;
		}
	}

	//closes the connection
	protected void closeConnection()throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
		Connection sqlCon = getConnection();
		sqlCon.close();
	}
}