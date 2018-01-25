package tries;

public class Trie {

	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	static class Node {
		Node[] childNode = new Node[26];
		boolean endofword;

		public Node() {
			endofword = false;
			for (int i = 0; i < 26; i++)
				childNode[i] = null;
		}
	}

	static Node root;

	public static void insert(String str) {
		Node crawl;
		crawl = root;
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i) - 'a';
			if (crawl.childNode[index] == null)
				crawl.childNode[index] = new Node();
			crawl = crawl.childNode[index];
		}
		crawl.endofword = true;
	}

	public static boolean search(String str) {
		Node crawl;
		crawl = root;
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i) - 'a';
			if (crawl.childNode[index] == null)
				return false;
			crawl = crawl.childNode[index];
		}

		return (crawl != null && crawl.endofword);

	}

	public static void print(Node crawl) {
		if (crawl == null)
			return;

		for (int i = 0; i < 26; i++) {
			if (crawl.childNode[i] != null) {
				System.out.print(alphabet[i]);
				if (crawl.childNode[i].endofword == true)
					System.out.println("--");

				print(crawl.childNode[i]);
			}
		}

	}

	public static void startsWith(Node root, String str) {
		Node crawl= root;
		if (crawl == null)
			return ;
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i) - 'a';
			if (crawl.childNode[index] != null) 
				crawl = crawl.childNode[index];
			else{
				System.out.println("String not found");
				return;
			}
			
		}
		print(crawl);
		

	}

	public static void main(String[] args) {

		String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

		// String output[] = { "Not present in trie", "Present in trie" };

		root = new Node();

		// Construct trie
		int i;
		for (i = 0; i < keys.length; i++)
			insert(keys[i]);

		//print(root);
		startsWith(root,"th");
	}

}
