package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.Db;
import db.DbException;

public class ProgramTransação {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
			
		try {
			conn = Db.getConnection();
			conn.setAutoCommit(false);	
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)"
											 + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Eliane");
			st.setString(2, "eliane@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("28/04/1963").getTime()));
			st.setDouble(4, 5400.0);
			st.setInt(5, 5);
			
			int rowsAffected = st.executeUpdate();
					
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
				int id = rs.getInt(1);
				System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affected!");
			}
			
			conn.commit();
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (ParseException e1) {
			throw new DbException(e1.getMessage());
		} finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}

	}

}
