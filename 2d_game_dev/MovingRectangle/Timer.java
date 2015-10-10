public class Timer {

	private long startTime;
	private int FPS;
	private int timer;

	private int count = 0;
	private long foo = System.nanoTime();

	public Timer(int FPS) {
		setFPS(FPS);
		startTime = System.nanoTime();
	}

	public boolean update() {
		boolean flag = false;	
		if(System.nanoTime() - startTime >= timer) {			
			startTime = System.nanoTime();			
			++count;
			flag = true;	
			
		}
		if(System.nanoTime() - foo >= 1000000000) {				
				System.out.println(count);
				count = 0;
				foo = System.nanoTime();
			}
		return flag;
	}

	public int getFPS() { return FPS; }
	public void setFPS(int FPS) {
		this.FPS = FPS;
		timer = 1000000000/FPS;
	}

}
