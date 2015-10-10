import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Graphics;
import java.awt.Color;

public class TileMap {

	int x;
	int y;

	private int[][] map;
	private int mapHeight;
	private int mapWidth;
	private int tileSize;

	public TileMap(String fileName, int tileSize) {
		x = y = 0;		

		this.tileSize = tileSize;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			mapWidth = Integer.parseInt(reader.readLine());
			mapHeight = Integer.parseInt(reader.readLine());

			map = new int[mapHeight][mapWidth];

			for(int row = 0; row < mapHeight; ++row) {

				String line = reader.readLine();
				String[] fields = line.split(" ");

				for(int col = 0; col < mapWidth; ++col) {
					map[row][col] = Integer.parseInt(fields[col]);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getWidth() { return mapWidth; }
	public int getHeight() { return mapHeight; }
	public int getTileSize() { return tileSize; }
	public int getX() { return x; }
	public int getY() { return y; }

	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }

	public int getColTile(int x) {
		return x / tileSize;	
	}

	public int getRowTile(int y) {
		return y / tileSize;
	}

	public int getTile(int row, int col) {
		return map[row][col];
	}

	public void update() {

	}

	public void draw(Graphics g) {
		for(int row = 0; row < mapHeight; ++row) {

			for(int col = 0; col < mapWidth; ++col) {

				if(map[row][col] == 0) {
					g.setColor(Color.WHITE);
				} else if(map[row][col] == 1) {
					g.setColor(Color.BLACK);
				}

				g.fillRect(x + col * tileSize, y + row * tileSize, 
					tileSize , tileSize);
			}
		}
	}

	public boolean isBlocked(int row, int col) {
		return map[row][col] == 1;
	}
}
