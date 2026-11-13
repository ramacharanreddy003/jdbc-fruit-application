package Fruit_Application;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Scanner scn = new Scanner(System.in);
		Bag b1 = new Bag(scn);
		boolean flag = true;
		while (flag) {
			System.out.println("...Welcome...");
			System.out.println("1.Add fruit\n2.Search fruit\n3.remove fruit\n4.list of fruits\n5.Exit");
			System.out.println("Choose an option");
			int opt = scn.nextInt();
			scn.nextLine();
			switch (opt) {

			case 1: {
				b1.addFruit();
			}
				break;

			case 2: {
				b1.searchFruit();
				break;
			}

			case 3: {
				b1.removeFruit();
				break;
			}
			case 4: {
				b1.listFruits();
				break;

			}
			case 5: {
				flag = false;
				System.out.println("Thank you");
				break;
			}
			default:
				System.out.println("Invalid option");
			}
		}
		scn.close();

	}
}
