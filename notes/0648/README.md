## 648. 单词替换

### 题目描述
在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

你需要输出替换之后的句子。

示例:

```
输入: dict(词典) = ["cat", "bat", "rat"]
sentence(句子) = "the cattle was rattled by the battery"
输出: "the cat was rat by the bat"
```

注：
1. 输入只包含小写字母。
2. 1 <= 字典单词数 <=1000
3. 1 <=  句中词语数 <= 1000
4. 1 <= 词根长度 <= 100
5. 1 <= 句中词语长度 <= 1000

### 思路
把所有的词根放入前缀树中，在树上查找每个单词的最短词根。注意当继承词迭代完了都没找到词根的时候不要忘记添加本身。
```   
    public String replaceWords(List<String> dict, String sentence) {
        Node root = new Node();
        //所有词根添加到字典树中
        for (String word : dict) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new Node();
                }
                cur = cur.next[c - 'a'];
            }
            if (!cur.isWord) {
                cur.isWord = true;
            }
        }
        String[] successors = sentence.split(" ");
        StringBuilder sb = new StringBuilder("");
        for (String successor : successors) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            //找每个继承词 在字典树中的 最短词根
            List<Character> list = new ArrayList<>(); //记录每次的字符
            Node cur = root;
            for (int i = 0; i < successor.length(); i++) {
                char c = successor.charAt(i);
                if (cur.next[c - 'a'] == null) {
                    sb.append(successor);
                    break;
                } else {
                    //存在该字符
                    list.add(c);
                    if (cur.next[c -'a'].isWord) {
                        //已经找到了词根 最短词根到此结束
                        for (char character : list) {
                            sb.append(character);
                        }
                        break;
                    } else {
                        cur = cur.next[c - 'a'];
                        //这里需要注意：继承词 迭代完了 都没找到词根
                        if (i == successor.length() - 1) {
                            sb.append(successor);
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    class Node {
        boolean isWord;
        Node[] next;
        public Node() {
            this.isWord = false;
            next = new Node[26];
        }
    }
```

