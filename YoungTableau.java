package yt;
import java.util.Scanner;


public class YoungTableau {
	public static int extract_min(int [][]youngT)
	{
		int x = youngT[0][0];
		youngT[0][0] = Integer.MAX_VALUE;
		youngify(youngT, 0, 0);		
		return x;	
	}
	
	public static void youngify(int [][] young_t, int i, int j)
	{
		int x, y,m=4,n=4;    
		x = i;
		y = j;
		int temp;
		if (i + 1 < m)
		{
		    if (young_t[i][j] > young_t[i+1][j])
		    {
		         x = i + 1;
		         y = j;
		    }                                          
		}
		if (j+1 < n)
		{
		    if (young_t[x][y] > young_t[i][j+1]) 
		    {
		        x = i;
		        y = j+1;
		    }
		}
		if(x != i || y != j){
		   temp = young_t[x][y];
		   young_t[x][y] = young_t[i][j];
		   young_t[i][j] = temp;
		   youngify(young_t, x, y);
		}
		
		
     }
	public static void insert(int [][]young_t, int i, int j, int key)
	{
		int x, y, max, temp;
		if (young_t[i][j] < key)
		{
		  System.out.println("The Young Tableau is full,insertion of new elements is not possible");
		  return;
		}
		young_t[i][j] = key;
		x = i;
		y = j;
		max = Integer.MAX_VALUE;
		while( (i > 0 || j > 0) && (max > young_t[i][j]) )
		{
		     temp = young_t[x][y];
		     young_t[x][y] = young_t[i][j];
		     young_t[i][j] = temp;
		     i = x;
		     j = y;
		     if (i - 1 >= 0 && young_t[i][j] < young_t[i-1][j])
		     {
		         x = i -1;
		         y = j;
		     }
		     if(j -1 >= 0 && young_t[x][y] < young_t[i][j-1])
		     {
		        x = i;
		        y = j - 1;
		     }
		     max = young_t[x][y];
		}
	}
	
	public static boolean search(int [][]young_t, int i, int j, int key)
	{
			if (i >=0 && j < 4)
			{
		    if (young_t[i][j] == key)
		    	return true;
		    else if (young_t[i][j] < key) 
		    	 return search(young_t,i,j+1,key);
		   
		    else
		    	return search(young_t,i-1,j,key);   
		    }
		else return false;	
	}
	
	public static void main(String [] args) 
	{
        int A[] = {9,16,3,2,4,8,5,14,12};
		int young_t[][]=new int[4][4];
		int m=4,n=4;
		int [] sortedarray = new int[m*n];
		for (int i=0; i<m; i++) { 
			for(int j=0; j<n;j++) {
		     young_t[i][j]=Integer.MAX_VALUE;
			}
		}
		
		//INSERTING elements of array A[] in to the tableau matrix.
          for (int i=0;i<A.length;i++)
	      insert(young_t,m-1,n-1,A[i]);  
	    	    System.out.println("Q1 and Q4 : After the elements are inserted ,the Young Tableau matrix is:");
	    for (int i=0; i<m; i++)                             
	    { 
	    	for(int j=0;j<n;j++)
	    	{
		     System.out.print(young_t[i][j]+"      ");
	    	}
	    	System.out.println("\n");
		} 

	    //SEARCH FOR AN ELEMENT FROM THE YOUNG TABLEAU
	    System.out.println("Q6.searching the element");
	    System.out.println("Enter the number to search :");
	    int item;
	    Scanner input = new Scanner(System.in);
	    item = input.nextInt();
	    
	    boolean val=search(young_t,m-1,0,item);
	    if(val)
	    	System.out.println("The number you are searching for is found in the Young Tableau:");
	    else
	    	System.out.println("The number you are searching for is not found in the Young Tableau");
	    System.out.println(" ");
	    
	   int minimum_element=extract_min(young_t) ;
	   System.out.println("Q3 .The minimum element in the above Young Tableau is :" + minimum_element);
	   //System.out.println(extract_min(young_t));	
	   
	   System.out.println("The Young tableau matrix after the minimum element is extracted is :");
	   
	   for (int i=0; i<m; i++)                             
	    { 
	    	for(int j=0;j<n;j++)
	    	{
		     System.out.print(young_t[i][j]+"      ");
	    	}
	    	System.out.println("\n");
		} 
	   
	   
	   System.out.println("Q5. Sorted elements are ");
	      
	    //SORTING ELEMENTS
	    for (int i=0; i<m*n; i++)
	    	sortedarray[i] = extract_min(young_t);
	    for (int i=0; i<m*n; i++)
		     System.out.print(sortedarray[i]+" ");
		}
}


