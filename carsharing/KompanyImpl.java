package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KompanyImpl implements Kompany {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";
    static final String USER = "";
    static final String PASS = "";

    private Connection conn;

    public KompanyImpl() {
        conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(true);

            stmt = conn.createStatement();
            String sql = "CREATE TABLE company " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR UNIQUE NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM company ORDER BY id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    @Override
    public void createCompany(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO company (name) VALUES (?)");
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exit() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}