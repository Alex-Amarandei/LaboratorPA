/*
 * @author Alex Amarandei
 */

package randomRootTree;

public class Tree {
    private int vertexNumber;
    private int[][] adjacencyList;

    public Tree() {
        int i, j, currentVertex, numberOfChildren, remainingVertices;

        this.vertexNumber = (((int) (Math.random() * 100)) % 10) + 1;
        this.adjacencyList = new int[vertexNumber][];

        currentVertex = 0;
        remainingVertices = vertexNumber - 1;

        for (i = 0; i < this.vertexNumber && remainingVertices > 0; i++) {
            numberOfChildren = (int) (Math.random() * remainingVertices) + 1;
            remainingVertices -= numberOfChildren;
            adjacencyList[i] = new int[numberOfChildren];

            for (j = 0; j < numberOfChildren; j++) {
                adjacencyList[i][j] = ++currentVertex;
            }
        }

        String spaces = "";

        printTree(0, 0, spaces, adjacencyList);
    }

    private int printTree(int vertex, int number, String spaces, int[][] adjacencyList) {
        int i;

        System.out.printf("\n%s node_%d", spaces, number++);

        if(adjacencyList[vertex] == null) {
            System.out.print(" 🌿");
            return number;
        }

        for (i = 0; i < adjacencyList[vertex].length; i++) {
            System.out.printf("\n%s node_%d", spaces + "_", number++);
            number = printTree(adjacencyList[vertex][i], number, spaces + "__", adjacencyList);
        }

        return number;
    }
}
