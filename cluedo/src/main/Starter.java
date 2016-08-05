package main;

import java.util.Scanner;

public class Starter {
	public static void main(String[] args) {
		System.out.print("  ,o888888o.      8 8888         8 8888      88 8 8888888888   8 888888888o.          ,o888888o. \n");
		System.out.print("  8888     `88.   8 8888         8 8888      88 8 8888         8 8888    `^888.    . 8888     `88.\n");
		System.out.print(",8 8888      `8.  8 8888         8 8888      88 8 8888         8 8888        `88. ,8 8888       `8b\n");
		System.out.print("88 8888           8 8888         8 8888      88 8 8888         8 8888         `88 88 8888        `8b\n");
		System.out.print("88 8888           8 8888         8 8888      88 8 888888888888 8 8888          88 88 8888         88\n");
		System.out.print("88 8888           8 8888         8 8888      88 8 8888         8 8888          88 88 8888         88\n");
		System.out.print("88 8888           8 8888         8 8888      88 8 8888         8 8888         ,88 88 8888        ,8P\n");
		System.out.print("`8 8888      .8'  8 8888         ` 8888     ,8P 8 8888         8 8888        ,88' `8 8888       ,8P\n");
		System.out.print("  8888     ,88'   8 8888           8888   ,d8P  8 8888         8 8888    ,o88P'    ` 8888     ,88'\n");
		System.out.print("  `8888888P'      8 888888888888    `Y88888P'   8 888888888888 8 888888888P'          `8888888P'\n");
		System.out.print("\n");
		System.out.print("Welcome to Cluedo\n");
		System.out.print("\n");
		System.out.print("How many people will be playing?\n");
		Scanner scan = new Scanner(System.in);
		int players = 0;
		while (players <= 1 || players > 6) {
			if (scan.hasNext()) {
				if (scan.hasNextInt()) {
					players = scan.nextInt();
					if (players <= 1 || players > 6) {
						System.out.println("please input a positive integer between 2 and 6");
					} else {
						new Main(players,scan);
						break;
					}
				} else {
					scan.nextLine();
					System.out.println("please input a positive integer");
				}
			}
		}


	}
}
