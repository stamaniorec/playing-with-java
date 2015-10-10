
public class SpiralTraversalMatrix {

	private int[][] matrix;
	private int size;
	
	public SpiralTraversalMatrix(int size) {
		matrix = new int[size][size];
		this.size = size;
	}
	
	public void spiralFill() {
		// A mask to check if the cell has been visited before
		boolean[][] mask = new boolean[size][size];
		
		int direction = 0; 
		// 0 -> DOWN, 1 -> LEFT, 2 -> UP, 3 -> RIGHT
		
		int curRow = -1;
		int curCol = 0;
		
		// The incremented number that goes inside each cell
		int num = 0;
		
		int emptyCellsInMatrix = size * size;
		
		// While there are empty cells, i.e. the traversal hasn't finished
		while(emptyCellsInMatrix > 0) {			
			// If we're going down
			if(direction == 0) {
				// Keep going down
				for(int i = curRow + 1; i < size; ++i) {
					// If we've NOT been here before
					if(mask[i][curCol] == false) {
						// Visit the cell, marking it with the num variable
						matrix[i][curCol] = ++num;
						// Set it as visited in the mask
						mask[i][curCol] = true;
						// Update the variable
						curRow = i;
						// Make sure you decrease the number of empty cells to avoid infinite loop
						--emptyCellsInMatrix;
					// If we have been here, stop and 
					} else {
						break;
					}
				}
				// Change the direction
				++direction;
			} 
			
			else if(direction == 1) {
				for(int i = curCol + 1; i < size; ++i) {
					if(mask[curRow][i] == false) {
						matrix[curRow][i] = ++num;
						mask[curRow][i] = true;
						curCol = i;
						--emptyCellsInMatrix;
					} else {
						break;
					}
				}
				++direction;
			} 
			
			else if(direction == 2) {
				for(int i = curRow-1; i >= 0; --i) {
					if(mask[i][curCol] == false) {
						matrix[i][curCol] = ++num;
						mask[i][curCol] = true;
						curRow = i;
						--emptyCellsInMatrix;
					} else {
						break;
					}
				}
				++direction;
			} 
			
			else if(direction == 3) {
				for(int i = curCol - 1; i >= 0; --i) {
					if(mask[curRow][i] == false) {
						matrix[curRow][i] = ++num;
						mask[curRow][i] = true;
						curCol = i;
						--emptyCellsInMatrix;
					} else {
						break;
					}
				}
				++direction;
			}
			if(direction > 3) {
				direction = 0;
			}
		}
	}
	
	public void printMatrix() {
		for(int i = 0; i < size; ++i) {
			for(int j = 0; j < size; ++j) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		SpiralTraversalMatrix m = new SpiralTraversalMatrix(6);
		m.spiralFill();
		m.printMatrix();
	}
	
}
