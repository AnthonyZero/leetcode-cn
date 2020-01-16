## 211. 添加与搜索单词 - 数据结构设计

设计一个支持以下两种操作的数据结构：

```   
void addWord(word)
bool search(word)
```   
search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

示例:
```   
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

```   

说明：

你可以假设所有单词都是由小写字母 a-z 组成的。

### 思路
通过字典树Trie来实现，在搜索的方法上可以采用递归的思路。从当前节点出发递归查看每一个字符看是否匹配（在分叉的路径上）。在遇到.这种通配符的时候，相当于忽略跨过当前树的一层，看下一层的匹配结果，只要存在一个分叉匹配成功，那么整体结果就是返回true的。

```   
    class WordDictionary {
    
        private Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            if (!cur.isWord) {
                cur.isWord = true;
            }
        }

        //字符串匹配搜索 （是否是单词）
        public boolean search(String word) {
            return match(root, word, 0);
        }

        private boolean match(Node node, String word, int index) {
            if (index == word.length()) {
                return node.isWord; //不能直接返回true 这不是前缀
            }
            char c = word.charAt(index); //查看当前此字段是否匹配
            if (c != '.') {
                //普通字符
                if (node.next.get(c) != null) {
                    return match(node.next.get(c), word, index + 1);
                } else {
                    return false;
                }
            } else {
                //通配符
                for(char nextChar : node.next.keySet()) {
                    //每一个分叉 只要存在一个匹配成功 就行
                    if (match(node.next.get(nextChar), word, index+1)) {
                        return true;
                    }
                }
                //一个分叉 都没匹配成功
                return false;
            }
        }

        class Node {
            private boolean isWord; //是否是单词
            private TreeMap<Character, Node> next;
            public Node() {
                isWord = false;
                next = new TreeMap<>();
            }
        }
    }
```