# Friends
Friends is a program that connects a bunch of different users

-------------------CODED IN JAVA----------------

This program makes use of an undirected and unweighted graph structure that is stored in an adjacency linked list to make a network of friends.

This program also hase a driver implemented that is available for use in a file called "FriendsTester.java"

Create a text file with the names of people followed by a "y" or "n" for yes or no if they attend college.  If yes enter the college name.

For example this is how a name should be entered with information:

(If college)

sam|y|"MIT"

(No college)

kate|n

Only after you have entered the last name and their college status, enter the connections in the format as follows:

sam|kate (shows sam is friends with kate)

This program has a bunch of different applications to use.

1st is Shortest Path:
The shortest path function accepts two parameters "friend1" and "friend2" that finds the shortest possible path from friend1 to friend2
This alogrithm makes use of breadth-first search traversal to get the desired path

2nd is cliques:
This function finds a group of friends that are connected to each other by a specific college that is entered by the user using the depth-first search traversal.  For example if 4 friends are connected to each other through and go to the same college then they are part of a clique.  This function returns the names of students who are part of a specific clique.

3rd is connectors:
This function finds all the articulation points in the network of friends using depth-first search and returns a list of all articulation points in the graph.  These articulation points represent connector friends who can connect other friends.  If these connector friends are removed then some friends won't be able to reach the other friends.  This algorithm returns a list of names of these connector friends.


