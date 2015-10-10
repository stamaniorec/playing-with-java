public class Enum {

	public enum FRUITS {	

		GUAVA("exotic"), 
		APPLES("ordinary but good"), 
		PINEAPPLE("awesome");

		private String desc;

		private FRUITS(String desc){
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc){
			this.desc = desc;
		}
	}

	public static void main(String[] args) {
		FRUITS.GUAVA.setDesc("never tasted");
		System.out.println(FRUITS.GUAVA.getDesc());	
	}

}
