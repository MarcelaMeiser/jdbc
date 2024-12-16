package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;

import db.Db;
import db.DbException;

public class ProgramInserir {

	public static void main(String[] args) {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			/*ADICIONANDO UM SELLER:
			conn = Db.getConnection();
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)"
									 + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Geraldo");
			st.setString(2, "geraldo@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("20/03/1945").getTime()));
			st.setDouble(4, 3600.0);
			st.setInt(5, 4);
			*/
			
			//ADICIONANDO DOIS DEPARTAMENTOS:
			conn = Db.getConnection();
			st = conn.prepareStatement("INSERT INTO department (Name)"
									 + "VALUES ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);
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
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} /*catch (ParseException e1) {
			throw new DbException(e1.getMessage());
		} */finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}

	}

}
