package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.Db;
import db.DbIntegrityException;

public class ProgramDeletar {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = Db.getConnection();
			st = conn.prepareStatement("DELETE FROM department WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, 5);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}

	}

}
