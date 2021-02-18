/*
 * @author Alex Amarandei
 */ 

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int dimension;
    private Integer[][] adjacencyMatrix;

    public Graph() {
        this.dimension = 1;
        this.adjacencyMatrix = new Integer[1][1];
        this.adjacencyMatrix[0][0] = 1;
    }

    public Graph(int dimension) {
        this.dimension = dimension;
        this.adjacencyMatrix = new Integer[this.dimension][this.dimension];

        int i, j;
        for(i = 0; i < this.dimension; i++) {
            for (j = i; j < this.dimension; j++) {
                if (i == j)
                    this.adjacencyMatrix[i][j] = 0;
                else {
                    this.adjacencyMatrix[i][j] = this.adjacencyMatrix[j][i] = ((int) (Math.random() * 10)) % 2;
                }
            }
        }
    }

    public void printGraph() {
        int i, j;

        for(i = 0; i < this.dimension; i++) {
            for (j = 0; j < this.dimension; j++) {
                if(this.adjacencyMatrix[i][j] == 0) System.out.print("⬜️");
                else System.out.print("🔸");
            }
            System.out.print('\n');
        }
    }

    public boolean isConnected() {
        int i, j, components = 1, lastChecked = 0;
        int[] isVisited = new int[this.dimension];
        Arrays.fill(isVisited, 0);

        isVisited[0] = 1;
        DFS(0, isVisited, components);

        if(containsZero(isVisited) == false) return true;

        while(containsZero(isVisited)) {
            printComponent(isVisited, components);

            for(i = lastChecked; i < this.dimension; i++) {
                if (isVisited[i] == 0) {
                    components++; lastChecked = i; isVisited[i] = components;
                    DFS(i, isVisited, components); break;
                }
            }
        }

        printComponent(isVisited, components);

        return false;
    }

    private void DFS(int start, int[] isVisited, int component) {
        int i;

        for(i = 0; i < isVisited.length; i++) {
            if (this.adjacencyMatrix[start][i] == 1 && isVisited[i] == 0) {
                isVisited[i] = component;
                DFS(i, isVisited, component);
            }
        }
    }

    private boolean containsZero(int[] array) {
        int i;

        for(i = 0; i < array.length; i++) if(array[i] == 0) return true;

        return false;
    }

    private boolean containsFalse(boolean[] array) {
        int i;

        for(i = 0; i < array.length; i++) if(array[i] == false) return true;

        return false;
    }

    private void printComponent(int[] isVisited, int component) {
        int i;

        System.out.printf("Component number #%d: ", component);

        for(i = 0; i < isVisited.length; i++) {
            if (isVisited[i] == component) System.out.printf(i + " ");
        }

        System.out.print('\n');
    }

    public void generatePartialTree() {
        int i, j, currentVertex;
        boolean[] isVisited = new boolean[this.dimension];
        Integer[][] treeMatrix = new Integer[this.dimension][this.dimension];
        Queue<Integer> queue = new LinkedList<>();

        Arrays.fill(isVisited, false);
        for(i = 0; i < this.dimension; i++)
            Arrays.fill(treeMatrix[i], 0);

        currentVertex = ((int) (Math.random() * 10)) % this.dimension;

        System.out.println("\nRoot of the partial tree: " + currentVertex + "\n");

        isVisited[currentVertex] = true; queue.add(currentVertex);

        while(!queue.isEmpty()) {
            currentVertex = queue.remove();

            for(i = 0; i < this.dimension; i++) {
                if(!isVisited[i] && this.adjacencyMatrix[currentVertex][i] == 1) {
                    isVisited[i] = true; queue.add(i);
                    treeMatrix[currentVertex][i] = 1; treeMatrix[i][currentVertex] = 1;
                }
            }
        }

        if(this.dimension <= 20) {
            for (i = 0; i < this.dimension; i++) {
                for (j = 0; j < this.dimension; j++) {
                    if (treeMatrix[i][j] == 0) System.out.print("⬜️");
                    else System.out.print("🔸");
                }
                System.out.print('\n');
            }
        }
    }
}
