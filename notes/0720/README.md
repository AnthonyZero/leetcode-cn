## 720. 词典中最长的单词

### 题目描述
给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。

示例1：
```
输入: 
words = ["w","wo","wor","worl", "world"]
输出: "world"
解释: 
单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
```

示例2：
```
输入: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
输出: "apple"
解释: 
"apply"和"apple"都能由词典中的单词组成。但是"apple"得字典序小于"apply"。
```

### 思路
前缀树 + 深度优先搜索：由于涉及到字符串的前缀，通常可以使用 trie（前缀树）来解决。
将所有单词插入 trie，然后从 trie 进行深度优先搜索，每找到一个单词表示该单词的全部前缀均存在，我们选取长度最长的单词。
```   
    public String res = ""; //最大长度的单词（在前缀树中含有所有前缀）
    public String longestWord(String[] words) {
        //构建前缀树
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }
        //深度优先搜索
        dfs(trie.root, new StringBuilder());
        return res;
    }

    // sb在深度搜索的过程中 不断在变化，搜索完毕 res(最大长度单词)也就构造完成
    public void dfs(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        } else if (res.length() < sb.length()) {
            res = sb.toString(); //更新最大值（单词）
        } else if (res.length() == sb.length()) {
            if(res.compareTo(sb.toString()) > 0) {
                res = sb.toString(); //长度相同的情况 取字典序在前面的单词
            }
        }
        //深度搜索下去
        Set<Character> characters = node.next.keySet();
        for(Character c : characters) {
            //当前节点是个单词 才符合条件
            if (node.next.get(c).isWord) {
                //搜索到这 添加此字符 继续dfs（比较res长度）
                sb.append(c);
                dfs(node.next.get(c), sb);
                //这很重要 回溯（要删掉上一步选择的路径 也就是上一步添加的字符）
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    class Node {
        private boolean isWord; //代表是个单词
        private TreeMap<Character,Node> next;
        public Node() {
            isWord = false;
            next = new TreeMap<>();
        }
    }

    class Trie {
        private Node root;
        public Trie() {
            root = new Node();
        }
        //添加一个单词
        public void insert(String word) {
            Node cur = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!cur.next.containsKey(c)) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            cur.isWord = true;
        }
    }
```

