package model.settings;

import java.util.EnumSet;

public enum CurrentAction {
	VERTEX, EDGE,DFS,DEEPSEARCH,BFS,BREADTHSEARCH,VERTEXREMOVE,
	EDGEREMOVE,PATH, WEIGHTEDGE,SIMPLEWEIGHT,PRIM,KRASKAL, DIJKSTRA,
	VINFO,CHANGELABEL,CONNECTED,BESTPATH;

	public static EnumSet<CurrentAction> getGraphElements() {
		return EnumSet.of(VERTEX, EDGE, SIMPLEWEIGHT,WEIGHTEDGE, VERTEXREMOVE, EDGEREMOVE,CHANGELABEL);
	}

	public static EnumSet<CurrentAction> getAlgorithms() {
		return  EnumSet.of(PRIM,KRASKAL, DIJKSTRA,DFS,DEEPSEARCH,BFS,BREADTHSEARCH);
	}

	public static EnumSet<CurrentAction> getInfo() {
		return  EnumSet.of(VINFO,CONNECTED);
	}

	public static EnumSet<CurrentAction> getElements() {
		return  EnumSet.of(PATH,BESTPATH);
	}
}
