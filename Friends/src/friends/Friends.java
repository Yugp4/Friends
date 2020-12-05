package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		
		/** COMPLETE THIS METHOD **/
		if(p1 == null || p2 == null || g == null) {
			ArrayList<String> same = new ArrayList<String>();
			return same;
		}
		
		if(!(g.map.containsKey(p1)) || !(g.map.containsKey(p2))) {
			ArrayList<String> same = new ArrayList<String>();
			return same;
		}
		
		if(p1.equals(p2)) {
			ArrayList<String> same = new ArrayList<String>();
			return same;
		}
		
		ArrayList<String> shortPath = new ArrayList<String>();
		int[] parent = new int[g.members.length];
		boolean[] visited = new boolean[g.members.length];
		for(int b = 0; b < visited.length; b++) {
			visited[b] = false;
		}
		Queue<Integer> track = new Queue<Integer>();
		int sInd = g.map.get(p1);
		int eInd = g.map.get(p2);
		track.enqueue(sInd);
		visited[sInd] = true;
		parent[sInd] = -1;
		while(!(track.isEmpty())) {
			int curr = track.dequeue();
			for(Friend fPtr = g.members[curr].first; fPtr != null; fPtr = fPtr.next) {
				if(fPtr.fnum == eInd) {
					parent[fPtr.fnum] = curr;
					visited[fPtr.fnum] = true;
					ArrayList<Integer> rPath = new ArrayList<Integer>();
					rPath.add(0, fPtr.fnum);
					chainHelper(g, parent, fPtr.fnum, rPath);
					for(int i = 0; i < rPath.size(); i++) {
						shortPath.add(g.members[rPath.get(i)].name);
					}
					return shortPath;
				}
				if(visited[fPtr.fnum] == false) {
					track.enqueue(fPtr.fnum);
					visited[fPtr.fnum] = true;
					parent[fPtr.fnum] = curr;
				}
			}
	}
		return shortPath;
	}
	public static void chainHelper(Graph g, int[] parents, int pToFound, ArrayList<Integer> rPath) {
		int pa = parents[pToFound];
		if(pa == -1) {
			return;
		}
		rPath.add(0, pa);
		chainHelper(g, parents, pa, rPath);	
	}

	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		
		/** COMPLETE THIS METHOD **/
		ArrayList<ArrayList<String>> allCliques = new ArrayList<ArrayList<String>>();
		if(school == null || g == null) {
			return allCliques;
		}
		boolean[] visited = new boolean[g.members.length];
		for(int b = 0; b < visited.length; b++) {
			visited[b] = false;
		}
		for(int v = 0; v < visited.length; v++) {
			if(visited[v] == false) {
				ArrayList<String> added = new ArrayList<String>();
				cliqueHelper(visited, g, school, g.members[v], added);
				if(!(added.isEmpty())) {
				allCliques.add(added);
				}
			}
		}
		
		return allCliques;
		
	}
	
	private static void cliqueHelper(boolean[] v, Graph g, String s, Person p, ArrayList<String> arr){
		int ind = g.map.get(p.name);
		v[ind] = true;
		if(p.student == true && p.school.equals(s)) {
			arr.add(p.name);
		}else {
			return;
		}
		for(Friend ptr = p.first; ptr != null; ptr = ptr.next) {
			int fInd = ptr.fnum;
			if(v[fInd] == false) {
				cliqueHelper(v, g, s, g.members[fInd], arr);
			}
		}
	}
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		ArrayList<String> connectors = new ArrayList<String>();
		if(g == null) {
			return connectors;
		}
		boolean[] visited = new boolean[g.members.length];
		int[] dfsNums = new int[g.members.length];
		int[] backNums = new int[g.members.length];
		int[] dfscount = new int[1];
		for(int b = 0; b < visited.length; b++) {
			visited[b] = false;
		}
		for(int i = 0; i < dfsNums.length; i++) {
			dfsNums[i] = 0;
			backNums[i] = 0;
		}
		
		for(int v = 1; v < visited.length; v++) {
			if(visited[v] == false) {
				connectHelper(g, visited, null, g.members[v], dfsNums, backNums, connectors, dfscount);
			}
		}
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return connectors;
		
	}
	
	private static void connectHelper(Graph g, boolean[] visited, Person parent, Person friend, int[] dfs, int[] back, ArrayList<String> c, int[] dfscount) {
		boolean arti = false;
		int ch = 0;
		int friendInt = g.map.get(friend.name);
		int no = dfscount[0] + 1;
		dfscount[0] = no;
		dfs[friendInt] = no;
		back[friendInt] = no;
		visited[friendInt] = true;
		for(Friend ptr = friend.first; ptr != null; ptr = ptr.next) {
			if(parent != null && ptr.fnum == g.map.get(parent.name)) {
				continue;
			}
			if(visited[ptr.fnum] == false) {
				ch++;
				connectHelper(g, visited, friend, g.members[ptr.fnum], dfs, back, c, dfscount);
				if(dfs[friendInt] <= back[ptr.fnum]) {
					arti = true;
				}else {
					if(parent != null) {
					back[friendInt] = Math.min(back[ptr.fnum], back[friendInt]);
					}
				}
				
			}else {
				back[friendInt] = Math.min(back[friendInt], dfs[ptr.fnum]);
				
			}
		}
		if(parent == null && ch >= 2 || parent != null && arti == true) {
			c.add(g.members[friendInt].name);
		}
		return;
	}
}

