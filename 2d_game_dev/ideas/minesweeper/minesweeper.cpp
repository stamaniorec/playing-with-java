#include <iostream>
#include <queue>

#define SIZE 7
#define OUTLINE 5

bool valid(std::pair<int,int> pair)
{
	return pair.first >= 0 && pair.first < SIZE && pair.second >= 0 && pair.second < SIZE;
}

void bfs(int grid[][SIZE], int row, int col)
{
	// I'm guaranteed to start at a cell that's 0
	// "Expand" the "field" of zeroes, i.e. visit all of them, and mark the outline

	bool visited[SIZE][SIZE] = { false };
	std::queue< std::pair<int,int> > q;
	q.push(std::make_pair(row, col));
	while(!q.empty())
	{
		std::pair<int,int> cur = q.front();
		q.pop();

		visited[cur.first][cur.second] = true;

		// If I reach a non-empty cell
		if(grid[cur.first][cur.second])
		{
			// Mark it as outline
			grid[cur.first][cur.second] = OUTLINE;
		}
		else
		{
			// BFS in every direction that's not visited
 
			std::pair<int,int> top(cur.first-1, cur.second);
			std::pair<int,int> top_right(cur.first-1, cur.second+1);
			std::pair<int,int> right(cur.first, cur.second+1);
			std::pair<int,int> bottom_right(cur.first+1, cur.second+1);
			std::pair<int,int> bottom(cur.first+1, cur.second);
			std::pair<int,int> bottom_left(cur.first+1, cur.second-1);
			std::pair<int,int> left(cur.first, cur.second-1);
			std::pair<int,int> top_left(cur.first-1, cur.second-1);

			if(valid(top) && !visited[top.first][top.second])
				q.push(top);
			if(valid(top_right) && !visited[top_right.first][top_right.second])
				q.push(top_right);
			if(valid(right) && !visited[right.first][right.second])
				q.push(right);
			if(valid(bottom_right) && !visited[bottom_right.first][bottom_right.second])
				q.push(bottom_right);
			if(valid(bottom) && !visited[bottom.first][bottom.second])
				q.push(bottom);
			if(valid(bottom_left) && !visited[bottom_left.first][bottom_left.second])
				q.push(bottom_left);
			if(valid(left) && !visited[left.first][left.second])
				q.push(left);
			if(valid(top_left) && !visited[top_left.first][top_left.second])
				q.push(top_left);
		}
	}
}

void print_grid(int grid[][SIZE])
{
	for(int i = 0; i < SIZE; ++i)
	{
		for(int j = 0; j < SIZE; ++j)
		{
			if(grid[i][j] == OUTLINE)
				std::cout << "+ ";
			else if(grid[i][j] == -1)
				std::cout << "* ";
			else
				std::cout << grid[i][j] << " ";
		}
		std::cout << std::endl;
	}
}

int main(int argc, char const *argv[])
{
	int grid[][SIZE] = {
		// {2,-1,1,0,0,0,0,0,0},
		// {0,2,1,1,1,1,0,0,0},
		// {1,1,0,1,-1,1,0,1,1},
		// {0,0,0,1,1,2,2,3,0},
		// {0,0,0,0,0,1,0,0,0},
		// {0,0,0,0,1,3,0,0,0},
		// {1,1,0,0,1,0,0,0,0},
		// {0,1,0,0,1,3,0,0,0},
		// {1,1,0,0,0,1,0,0,0}

		{0,2,1,1,2,0,0}, 
		{2,1,0,0,1,3,0},
		{1,0,0,0,0,1,0},
		{2,1,0,0,0,1,2},
		{0,2,1,1,0,0,1},
		{0,0,0,2,1,1,1},
		{0,0,0,0,0,0,0} 
	};

	bfs(grid, 4, 4);
	print_grid(grid);

	return 0;
}
