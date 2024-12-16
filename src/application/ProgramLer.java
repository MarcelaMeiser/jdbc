package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.Db;
import db.DbException;

public class ProgramLer {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = Db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM seller");
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", "
								 + rs.getString("Name") + ", "
								 + rs.getString("Email") + ", "
								 + rs.getDate("BirthDate") + ", "
								 + rs.getDouble("BaseSalary") + ", ");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeResultSet(rs);
			Db.closeStatement(st);
			Db.closeConnection();
		}
	}

}
