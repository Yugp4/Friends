//Yug Patel's Friends code

import java.io.*;
import java.util.*;
import friends.Friends;
import friends.Graph;

public class FriendsTester {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter File Name: ");
		String fileName = in.nextLine();
		Graph gr = new Graph(new Scanner(new File(fileName)));
		System.out.println("What do you want to do:  \n 1. Find the shortest path between two friends \n 2. Find cliques based on schools \n 3. Find connectors \n 4. Quit ");
		int choice = in.nextInt();
		do {
			if(choice == 1) {
				System.out.println("Enter name of the first friend: ");
				String f1 = in.next();
				System.out.println("Enter the name of the second friend: ");
				String f2 = in.next();
				ArrayList<String> shortPath = Friends.shortestChain(gr, f1, f2);
				if(shortPath.isEmpty()) {
					System.out.println("No path exisits from " + f1 + " to " + f2);
				}else {
					for(int i = 0; i < shortPath.size()-1; i++) {
						System.out.print(shortPath.get(i) + " --> ");
					}
					System.out.println(shortPath.get(shortPath.size()-1));
				}
				System.out.println();
			}else if(choice == 2) {
				System.out.println("Enter the name of the school: ");
				String school = in.next();
				ArrayList<ArrayList<String>> clique = Friends.cliques(gr, school);
				if(clique.isEmpty()) {
					System.out.println("No cliques exist with this school");
				}else {
				for(int i = 0; i < clique.size(); i++) {
					System.out.println("Clique " + (i+1) + ": ");
					for(int j = 0; j < clique.get(i).size()-1; j++) {
						System.out.print(clique.get(i).get(j) + ", ");
					}
					System.out.println(clique.get(i).get(clique.get(i).size()-1));
				}
				}
				System.out.println();
			}else if(choice == 3) {
				ArrayList<String> connect = Friends.connectors(gr);
				if(connect.isEmpty()) {
					System.out.println("No connectors exist, all the friends will always be connected");
				}else {
					for(int i = 0; i < connect.size()-1; i++) {
						System.out.print(connect.get(i) + ", ");
					}
					System.out.println(connect.get(connect.size()-1));
				}
				System.out.println();
			}
			System.out.println("What do you want to do:  \n 1. Find the shortest path between two friends \n 2. Find cliques based on schools \n 3. Find connectors \n 4. Quit ");
			choice = in.nextInt();
		}while(choice != 4);
		in.close();
	}
}
