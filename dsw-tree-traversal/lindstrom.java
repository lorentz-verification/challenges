class Tree{
	Tree right;
	Tree left;
}

public class Lindstrom {	
	static Tree traverse(Tree root){
		if(root == null) return root;
		Tree sen = new Tree();		
		Tree prev = sen;
		Tree cur  = root;
		while(cur != sen){
			Tree next = cur.left;		
			cur.left = cur.right;
			cur.right = prev;
			prev = cur;
			cur  = next;
			if (cur == null){
				cur  = prev;
				prev = null;
			}
		}		
		return cur;
	}
	public static void main(String[] args) {
    }
}
