import java.util.Scanner;

public class BinaryIndexTree {
	
	//Input format is 
	
	// n - number of element of the array
	// a[0], a[1],.....a[n-1] element of the array,
	//query - number of the queries,
	// To get Sum we use:
	// 1 x (x is the position of sum from 0 to x inclusive);
	//To update the value.
	//2 x y (x is the index at which the update take place ) and (y is the new updated value)
	//
	
	
	public static void main(String[] args) throws Exception {
		
		
		Scanner myobj = new Scanner(System.in);
		
		int n = myobj.nextInt();
		
		int a[] = new int[n];
		
		for(int i =0 ;i<n;i++) {
			
			a[i] = myobj.nextInt();
			
		}
		
		
		
		
		tree = new int[n+1];
		MakeTree(a,n);
		
		
		int query = myobj.nextInt();
		
		
		while(query-->0) {
			
			int q = myobj.nextInt();
			
			if(q==1) {
				int k = myobj.nextInt();
				System.out.println(getSum(k));
			}
			else {
				
				//Index is from 0 to n;
				int index = myobj.nextInt();
				int value = myobj.nextInt();
				
				int diff = value -a[index];
				
				
				updateTree(3,diff);
			}
		}
		
		myobj.close();
			
	}

	//Binary IndexTree, initialize in main function according to the value of n.
	static int[] tree;
	
	
	//Function to make The tree, here "a" is Array and n is the length of the array.
	public static void MakeTree(int a[],int n) 
	{
		
		
		
		for(int i =1;i<=n;i++) {
			
			tree[i]+=a[i-1];
			
			
			int temp = getNext(i);
			
			
			while(temp<n) {
				
				tree[temp]+=a[i-1];
				temp = getNext(temp);
				
				
			}
		}
		
		
		
	}
	
	//Function to get the Sum from 0 to n inclusive from Tree
	public static int getSum(int n) {
		int sum =0;
		n+=1;
		
		sum+=tree[n];
		
		
		int temp = getParent(n);
		while(temp!=0)
		{
			
			sum+=tree[temp];
			temp = getParent(temp);
			
			
		}
		
		
		return sum;
	}
	 
	//Function to update tree when there is update in the array, here n is the index at which the update take place
	//and x is the difference of the new value and a[n];
	
	//x = newValue at n - a[n];
	public static void updateTree(int n, int x) {
		printTree();
		
		
		tree[n]=x+tree[n];
		
		
		int temp = getNext(n);
		
		while(temp<tree.length) {
			
			tree[temp] = x+tree[temp];
			
			temp = getNext(temp);
		}
		
		printTree();
		
		
	}
	
	//Function to print the tree.
	public static void printTree() {
		
		
		for(int i =0 ;i<tree.length;i++) {
			System.out.print(tree[i] + " ");
		}
		System.out.println();
		
	}
	
	public static int getNext(int n) {
		
		String k = Integer.toBinaryString(n);
		
		String temp = TwosComplement(k);
		
		String ans = "";
		
		for(int i =0;i<k.length();i++) {
			
			if(k.charAt(i)==temp.charAt(i)){
				ans+=k.charAt(i)+"";
			}
			else {
				ans+="0";
			}
		}	
		
		
		int f = Integer.parseInt(ans, 2);
		
		
		int index = n+f;
		
		return index;
		
		
	}
	
	public static int getParent(int n)
	{
		
		String k = Integer.toBinaryString(n);
		
		String temp = TwosComplement(k);
		
		
		String ans = "";
		
		for(int i =0;i<k.length();i++) {
			
			if(k.charAt(i)==temp.charAt(i)){
				ans+=k.charAt(i)+"";
			}
			else {
				ans+="0";
			}
		}	
		
		
		int f = Integer.parseInt(ans, 2);
		
		int index = n-f;

		return index;
	}
	
	public static String TwosComplement(String k)
	{
		
		int n = k.length();
		int index = -1;
		for(int i=n-1;i>=0;i--) {
			if(k.charAt(i)=='1') {
				index=i;
				break;
			}
			
		}
		
		
		if(index==-1) {
			
			return "1" + k;
		}
		
		
		
		for(int i = index-1 ;i>=0;i--) {
			
			if(k.charAt(i)=='1') {
				k=k.substring(0,i) + "0" + k.substring(i+1,n);
			}
			else {
				k=k.substring(0,i) + "1" + k.substring(i+1,n);
				
			}
			
			
		}
		
		return k;
		
	
	}
	
}

