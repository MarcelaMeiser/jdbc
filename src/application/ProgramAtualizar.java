package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.Db;
import db.DbException;

public class ProgramAtualizar {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = Db.getConnection();
			st = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE (departmentId = ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setDouble(1, 200.0);
			st.setInt(2, 1);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}

	}

}
