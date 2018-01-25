package allPossibleWords;


public class AllPossibleWords {
	/*
	 * Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"}; boggle[][] =
	 * {{'G','I','Z'}, {'U','E','K'}, {'Q','S','E'}}; isWord(str): returns true
	 * if str is present in dictionary else false.
	 * 
	 * Output: Following words of dictionary are present GEEKS QUIZ
	 */
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
	public static class Treenode {
		Treenode[] child = new Treenode[26];
		boolean isleaf;

		public Treenode() {
			isleaf = false;
			for (int i = 0; i < 26; i++)
				child[i] = null;

		}
	}

		static public  void insert(Treenode root, String str) {
			int len = str.length();
			Treenode pchild = root;
			for (int i = 0; i < len; i++) {
				int index = str.charAt(i) - 'A';
				if (pchild.child[index] == null)
					pchild.child[index] = new Treenode();

				pchild = pchild.child[index];
			}
			pchild.isleaf = true;
		}
	
	
	public static void main(String[] agrs){
		String dictionary[] = {"GEEKS", "FOR", "QUIZ", "GEE"};
		 
	      
        // root Node of trie
		Treenode root = new Treenode();
		
      
        // insert all words of dictionary into trie
        int n = dictionary.length;
        for (int i=0; i<n; i++)
        	insert(root, dictionary[i]);
        
        print(root);
      
        char boggle[][] = {{'G','I','Z'},
                           {'U','E','K'},
                           {'Q','S','E'}
        };
	}


	private static void print(Treenode treenode) {
		for(int i=0 ;i<26;i++){
        	if(treenode.child[i]!=null){
        		System.out.print(alphabet[i]+" ");
        		print(treenode.child[i]);
        		
        	}
        		
        	
        	
        }
		
	}

}
