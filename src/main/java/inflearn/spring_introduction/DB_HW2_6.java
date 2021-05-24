package inflearn.spring_introduction;

import java.sql.*;

public class DB_HW2_6 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn;
            conn = DriverManager.getConnection("jdbc:sqlite:/Users/jin/Desktop/2021-Spring/DB/HW2/db_hw2.db", "root", "root");
            System.out.println("Connection to SQLite has been established.");
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select P.ID as 제품번호, P.name as 제품명, P.stock as 재고량 from product P;");

            System.out.printf("%4s %5s %5s\n", "제품번호", "제품명", "재고량");
            System.out.println("-----------------------");
            while (rs.next()) {
                System.out.printf("%4s %10s %3s",
                        rs.getString("제품번호"), rs.getString("제품명"), rs.getString("재고량"));
                System.out.println();
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}