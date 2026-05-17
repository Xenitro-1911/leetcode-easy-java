# leetcode-easy-java

A structured collection of LeetCode Easy problem solutions written in Java, built as part of active DSA practice for CV and interview preparation.

---

## Structure

```
src/
├── main/java/problems/
│   └── p001_two_sum/
│       ├── Solution.java   # Clean Java solution
│       └── Notes.md        # Approach, complexity, alternatives
└── test/java/problems/
    └── p001_two_sum/
        └── SolutionTest.java  # JUnit 5 unit tests
```

Each problem folder contains:
- **Solution.java** — clean, commented solution with time/space complexity in Javadoc
- **Notes.md** — written breakdown of the approach, complexity analysis, and alternatives considered
- **SolutionTest.java** — JUnit 5 tests covering standard and edge cases

---

## Problems Solved (59)

| # | Problem | Topic |
|---|---------|-------|
| 001 | Two Sum | HashMap |
| 009 | Palindrome Number | Math |
| 013 | Roman to Integer | String/HashMap |
| 014 | Longest Common Prefix | String |
| 020 | Valid Parentheses | Stack |
| 021 | Merge Two Sorted Lists | Linked List |
| 026 | Remove Duplicates from Sorted Array | Two Pointers |
| 027 | Remove Element | Two Pointers |
| 028 | Find the Index of First Occurrence in a String | String |
| 035 | Search Insert Position | Binary Search |
| 058 | Length of Last Word | String |
| 066 | Plus One | Array |
| 067 | Add Binary | String/Math |
| 069 | Sqrt(x) | Binary Search |
| 070 | Climbing Stairs | Dynamic Programming |
| 083 | Remove Duplicates from Sorted List | Linked List |
| 088 | Merge Sorted Array | Two Pointers |
| 094 | Binary Tree Inorder Traversal | Tree/DFS |
| 100 | Same Tree | Tree/DFS |
| 101 | Symmetric Tree | Tree/BFS |
| 104 | Maximum Depth of Binary Tree | Tree/DFS |
| 108 | Convert Sorted Array to BST | Tree/Recursion |
| 110 | Balanced Binary Tree | Tree/DFS |
| 111 | Minimum Depth of Binary Tree | Tree/BFS |
| 112 | Path Sum | Tree/DFS |
| 118 | Pascal's Triangle | Array/DP |
| 119 | Pascal's Triangle II | Array/DP |
| 121 | Best Time to Buy and Sell Stock | Greedy |
| 125 | Valid Palindrome | Two Pointers |
| 136 | Single Number | Bit Manipulation |
| 141 | Linked List Cycle | Fast & Slow Pointers |
| 144 | Binary Tree Preorder Traversal | Tree/DFS |
| 145 | Binary Tree Postorder Traversal | Tree/DFS |
| 160 | Intersection of Two Linked Lists | Linked List |
| 168 | Excel Sheet Column Title | Math/String |
| 169 | Majority Element | Boyer-Moore Voting |
| 171 | Excel Sheet Column Number | Math/String |
| 190 | Reverse Bits | Bit Manipulation |
| 191 | Number of 1 Bits | Bit Manipulation |
| 202 | Happy Number | Fast & Slow Pointers |
| 203 | Remove Linked List Elements | Linked List |
| 205 | Isomorphic Strings | HashMap |
| 206 | Reverse Linked List | Linked List |
| 217 | Contains Duplicate | HashSet |
| 219 | Contains Duplicate II | Sliding Window/HashSet |
| 222 | Count Complete Tree Nodes | Binary Search/Tree |
| 225 | Implement Stack using Queues | Queue/Stack |
| 226 | Invert Binary Tree | Tree/DFS |
| 228 | Summary Ranges | Two Pointers |
| 231 | Power of Two | Bit Manipulation |
| 232 | Implement Queue using Stacks | Stack/Queue |
| 234 | Palindrome Linked List | Fast & Slow Pointers |
| 242 | Valid Anagram | Frequency Array |
| 257 | Binary Tree Paths | Tree/DFS/Backtracking |
| 258 | Add Digits | Math/Digital Root |
| 263 | Ugly Number | Math |
| 268 | Missing Number | Math/Gauss Formula |
| 278 | First Bad Version | Binary Search |
| 283 | Move Zeroes | Two Pointers |

---

## How to Run

**Requirements:** Java 17+, Maven 3.x

```bash
# Run all tests
mvn test

# Run a specific problem's tests
mvn test -Dtest=SolutionTest#p001
```

Or open in IntelliJ IDEA and right-click any test → Run.

---

## Tech Stack

- **Language:** Java
- **Testing:** JUnit Jupiter (JUnit 5)
- **Build:** Maven
- **IDE:** IntelliJ IDEA