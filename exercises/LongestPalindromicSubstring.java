
public class LongestPalindromicSubstring {

	public LongestPalindromicSubstring(String s) {
		System.out.println(longestPalindrome(s));
	}
	
	public String longestPalindrome(String s) {
		String longest = "";
		for(int i = 0; i < s.length(); ++i) {
			for(int j = i + 1; j <= s.length(); ++j) {
				String cur = s.substring(i, j);
				if(isPalindrome(cur) && cur.length() > 1) {
					if(cur.length() > longest.length()) {
						longest = cur;
					}
				}
			}
		}
		return longest;
	}
	
	public boolean isPalindrome(String s) {
		for(int i = 0; i < s.length() / 2; ++i) {
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		new LongestPalindromicSubstring("fookirilelirikbarabbafoobarlolkapak");
	}
	
}
