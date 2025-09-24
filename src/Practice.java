import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {
        if (nums == null) return 0;
        int sum = 0;

        for (int num : nums)
        {
            if (num % 2 != 0)
            {
                sum += num;
            }
        }

        return sum;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) {

        if (words == null) throw new NullPointerException();
        if (words.size() == 0) throw new IllegalArgumentException();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(words);
        Collections.sort(list);

        String shortestWord = list.get(0);

        for (String word : words)
        {
            if (word.length() < shortestWord.length())
            {
                shortestWord = word;
            }
        }

        return shortestWord;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {

        if (ages == null) throw new NullPointerException();

        Set<String> mySet = new HashSet<>();

        for (String name : ages.keySet())
        {
            if (ages.get(name) >= 18)
            {
                mySet.add(name);
            }
        }

        return mySet;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {

        if (head == null) throw new IllegalArgumentException();

        ListNode<Integer> current = head;
        int biggest = current.data;
        
        while (current.next != null)
        {
            if (current.data > biggest)
            {
                biggest = current.data;
            }
            current = current.next;
        }
        return biggest;
    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y: 1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {

        if (head == null) return new HashMap<>();

        Map<T, Integer> valuesMap = new HashMap<>();
        ListNode<T> current = head;

        while (current != null)
        {
            if (!valuesMap.containsKey(current.data))
            {
                valuesMap.put(current.data, 1);
            } else {
                int temp = valuesMap.get(current.data) + 1;
                valuesMap.put(current.data, temp);
            }
            current = current.next;
        }

        return valuesMap;
    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {

        int levels = 0;

        if (root == null) return 0;

        if (root.left == null && root.right == null)
        {
            return 1;
        }

        int leftLevels = levelCount(root.left);
        int rightLevels = levelCount(root.right);

        levels = Math.max(leftLevels, rightLevels);

        return 1 + levels;
    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {

        if (root == null) return 0;

        if (level == 1) return root.data;

        int sum  = 0;

        int leftSum = sumAtLevel(root.left, level-1);
        int rightSum = sumAtLevel(root.right, level-1);
        
        sum = leftSum + rightSum;

        return sum;
    }

    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {

        if (sumLinkedList(head) == sumTree(root)) return true;

        return false;
    }

    public static int sumLinkedList(ListNode<Integer> head)
    {
        int total = 0;
        ListNode<Integer> current = head;

        while (current != null)
        {
            total += current.data;
            current = current.next;
        }

        return total;
    }

    public static int sumTree(BinaryTreeNode<Integer> root)
    {
        if (root == null) return 0;

        int sumLeft = sumTree(root.left);
        int sumRight = sumTree(root.right);

        int total = root.data + sumLeft + sumRight;

        return total;
    }

    /**
     * Returns the sum of all the vertices in a graph that are reachable from a given
     * starting vertex.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the starting vertex
     * @return the sum of all the vertices
     */
    public static int graphSum(Vertex<Integer> start) {
        HashSet<Vertex<Integer>> myVisited = new HashSet<>();

        return graphSumHelper(start, myVisited);
    }

    public static int graphSumHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited)
    {
        if (vertex == null || visited.contains(vertex)) return 0;

        visited.add(vertex);
        int total = vertex.data;

        for (Vertex<Integer> neighbor : vertex.neighbors)
        {
            total += graphSumHelper(neighbor, visited);
        }

        return total;
    }

    /**
     * Returns the count of vertices in a graph that have an outdegree of 0.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the entrypoint to the graph
     * @return the count of vertices with outdegree 0
     */
    public static int sinkCount(Vertex<Integer> start) {
        HashSet<Vertex<Integer>> myVisited = new HashSet<>();

        return sinkCountHelper(start, myVisited);
    }

    public static int sinkCountHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {

        if (vertex == null || visited.contains(vertex)) return 0;
        
        int sinkCount = 0;
        visited.add(vertex);

        if (vertex.neighbors.size() == 0)
        {
            sinkCount += 1;
        }

        for (Vertex<Integer> neighbor : vertex.neighbors)
        {
            sinkCount += sinkCountHelper(neighbor, visited);
        }

        return sinkCount;
    }
}