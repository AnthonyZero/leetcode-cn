## 676. 实现一个魔法字典

### 题目描述
实现一个带有buildDict, 以及 search方法的魔法字典。

对于buildDict方法，你将被给定一串不重复的单词来构建一个字典。

对于search方法，你将被给定一个单词，并且判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。

示例:

```
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
```

注意:
1. 你可以假设所有输入都是小写字母 a-z。
2. 为了便于竞赛，测试所用的数据量很小。你可以在竞赛结束后，考虑更高效的算法。
3. 请记住重置MagicDictionary类中声明的类变量，因为静态/类变量会在多个测试用例中保留。

### 思路1
构建一个字典树+递归搜索（递归返回的时候只要是单词且替换次数使用了一次即可）
```   
    class MagicDictionary {
        private Node root;
        public MagicDictionary() {
            root = new Node();
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                Node cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (cur.next[c - 'a'] == null) {
                        cur.next[c - 'a'] = new Node();
                    }
                    cur = cur.next[c - 'a'];
                }
                cur.isWord = true;
            }
        }


        public boolean search(String word) {
            return search(root, word, 0, 1);
        }

        private boolean search(Node node, String word, int index, int num) {
            if (num < 0) { //替换次数已经超过了1次
                return false;
            }
            if (index == word.length()) { //所有字符匹配结束 要求只能使用一次
                return num == 0 && node.isWord;
            }
            char c = word.charAt(index);
            for (int j = 0; j < 26; j++) {
                // 只要存在一个分支 就行
                if (node.next[j] != null) {
                    if (c - 'a' == j) {
                        if (search(node.next[j], word, index + 1, num)) {
                            return true;
                        }
                    } else if (search(node.next[j], word, index + 1, num - 1)) { //使用一次替换机会
                        return true;
                    }
                }
            }
            return false;
        }



        class Node {
            private boolean isWord;
            private Node[] next;
            public Node() {
                this.isWord = false;
                next = new Node[26];
            }
        }
    }
```


### 思路2
要"判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。"，两个词必须是等长的，所以创建一个Map，key为单词的长度，value为对应长度的单词List，根据输入的单词长度获取等长的集合，然后逐个比较,只要其中存在一个 不相等的字符数只为1的单词即返回true。
```
    class MagicDictionary {
        private Map<Integer, List<String>> map;
        public MagicDictionary() {
            map = new HashMap<>();
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                int length = word.length();
                if (map.containsKey(length)) {
                    List<String> words = map.get(length);
                    words.add(word);
                    map.put(length, words);
                } else {
                    map.put(length, new ArrayList<>(Arrays.asList(word)));
                }
            }
        }


        public boolean search(String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            List<String> list = map.get(word.length());
            if (list == null || list.size() == 0) {
                return false;
            }
            for (String pattern : list) {
                int count = 0;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != pattern.charAt(i)) {
                        count++;
                    }
                }
                if(count == 1) {
                    return true;
                }
            }
            return false;
        }
    }

```

