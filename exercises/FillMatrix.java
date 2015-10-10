
public class FillMatrix {

	private int[][] matrix;
	private int size;
	
	public FillMatrix(int size) {
		matrix = new int[size][size];
		this.size = size;
	}
	
	public void fillMatrix() {
		for(int i = 0; i < size; ++i) {
			matrix[i][i] = 0;
		}
		
		int num = 1;
		
		for(int col = 0; col < size - 1; ++col) {
			for(int row = col + 1; row < size; ++row) {
				matrix[row][col] = num++;
			}
		}
		
		for(int col = size - 1; col > 0; --col) {
			for(int row = col - 1; row >= 0; --row) {
				matrix[row][col] = num++;
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
		FillMatrix m = new FillMatrix(5);
		m.fillMatrix();
		m.printMatrix();
	}
	
}
