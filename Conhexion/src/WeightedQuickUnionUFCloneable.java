// DO NOT MODIFY!!!

import java.util.Arrays;


/**
 * 	<tt>WeightedQuickUnionUFCloneable</tt> is adopted from the <tt>WeightedQuickUnionUF</tt> class
 *  written by  Robert Sedgewick and Kevin Wayne, with an additional overloaded constructor
 *	that takes another instance of this class as parameter to allow cloning.   
 * 	<p>
 *  The <tt>WeightedQuickUnionUF</tt> class represents a union-find data structure.
 *  It supports the <em>union</em> and <em>find</em> operations, along with
 *  methods for determining whether two objects are in the same component
 *  and the total number of components.
 *  <p>
 *  This implementation uses weighted quick union by size (without path compression).
 *  Initializing a data structure with <em>N</em> objects takes linear time.
 *  Afterwards, <em>union</em>, <em>find</em>, and <em>connected</em> take
 *  logarithmic time (in the worst case) and <em>count</em> takes constant
 *  time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *     
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class WeightedQuickUnionUFCloneable {
	private int[] id;    // id[i] = parent of i
	private int[] sz;    // sz[i] = number of objects in subtree rooted at i
	private int count;   // number of components

	/**
	 * Initializes an empty union-find data structure with N isolated components 0 through N-1.
	 * @throws java.lang.IllegalArgumentException if N < 0
	 * @param N the number of objects
	 */
	public WeightedQuickUnionUFCloneable(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	/**
	 * Initializes a new union-find data structure with the same state as
	 * the specified union-find data structure.  This constructor is used
	 * to clone a given union-find data structure.
	 * @param original The original WeightedQuickUnionUFCloneable instance
	 * whose state will be copied (cloned) into the newly created
	 * WeightedQuickUnionUFCloneable object.
	 */
	public WeightedQuickUnionUFCloneable(WeightedQuickUnionUFCloneable original)
	{
		count = original.count;
		id = Arrays.copyOf(original.id, original.id.length);
		sz = Arrays.copyOf(original.sz, original.sz.length);
	}

	/**
	 * Returns the number of components.
	 * @return the number of components (between 1 and N)
	 */
	public int count() {
		return count;
	}

	/**
	 * Returns the component identifier for the component containing site <tt>p</tt>.
	 * @param p the integer representing one site
	 * @return the component identifier for the component containing site <tt>p</tt>
	 * @throws java.lang.IndexOutOfBoundsException unless 0 <= p < N
	 */
	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}

	/**
	 * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
	 * @param p the integer representing one site
	 * @param q the integer representing the other site
	 * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt>
	 *    are in the same component, and <tt>false</tt> otherwise
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
	 */
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}


	/**
	 * Merges the component containing site<tt>p</tt> with the component
	 * containing site <tt>q</tt>.
	 * @param p the integer representing one site
	 * @param q the integer representing the other site
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= p < N and 0 <= q < N
	 */
	public void union(int p, int q)
	{
		int i = find(p);
		int j = find(q);
		if (i == j) return;

		// make smaller root point to larger one
		if   (sz[i] < sz[j]) 
		{
			id[i] = j; 
			sz[j] += sz[i]; 
		}
		else                
		{
			id[j] = i; 
			sz[i] += sz[j]; 
		}
		count--;
	}
}

