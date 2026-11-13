package Fruit_Application;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Bag {

	private static String url = "jdbc:postgresql://localhost:5432/College";
	private static String usn = "postgres";
	private static String pwd = "123";
//	static Scanner scn = new Scanner(System.in);

	private Scanner scn;

	public Bag(Scanner scn) {
		this.scn = scn;
	}

	
	public void addFruit() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(url, usn, pwd);
		System.out.println("Enter name of the fruit");
		String name = scn.nextLine();
		System.out.println("Enter taste of the fruit");
		String taste = scn.nextLine();
		String sql = "Insert into Bag (name,taste) values(?,?)";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, taste);
//
//		System.out.println("Name: " + name);
//		System.out.println("Taste: " + taste);

		int n = ps.executeUpdate();

		if (n > 0) {
			System.out.println("Fruit added successfully!!");
		} else {
			System.out.println("Fruit not added");
		}
		ps.close();
		conn.close();
	}

	
	public void searchFruit() throws SQLException {

		Connection conn = DriverManager.getConnection(url, usn, pwd);

		System.out.println("Enter name of the fruit");
		String name = scn.next();

		String sql = "SELECT * FROM Bag WHERE name=?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, name);

		ResultSet res = ps.executeQuery();

		if (res.next()) {
			System.out.println("Fruit is found");
			System.out.println("Name: " + res.getString("name"));
			System.out.println("Taste: " + res.getString("taste"));
		} else {
			System.out.println("Fruit is not found");
		}

		res.close();
		ps.close();
		conn.close();
	}

	
	
	public void removeFruit() throws SQLException {
		Connection conn = DriverManager.getConnection(url, usn, pwd);
		System.out.println("Enter fruit name to remove ");
		String name = scn.next();
		String sql = "DELETE FROM Bag WHERE name=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		// ResultSet res = ps.executeQuery();
		int i = ps.executeUpdate();
		if (i > 0) {
			System.out.println("Fruit removed successfully");
		} else {
			System.out.println("Fruit not found");
		}
		ps.close();
		conn.close();
	}

	
	public void listFruits() throws SQLException {
		Connection conn = DriverManager.getConnection(url, usn, pwd);

		String sql = "SELECT * FROM Bag";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		boolean found = false;

		while (res.next()) {
			found = true;
			System.out.println("Fruit name: " + res.getString("name"));
			System.out.println("Fruit taste: " + res.getString("taste"));

		}
		if (!found) {
			System.out.println("Bag is empty!");
		}
		res.close();
		ps.close();
		conn.close();
	}
}
