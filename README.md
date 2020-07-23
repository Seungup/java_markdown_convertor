
# HEADER
---
```java
header("#안녕하세요 #반갑습니다 #다시뵙네요", 2);
```
## #안녕하세요 #반갑습니다 #다시뵙네요

```java
header("### #안녕하세요 #반갑습니다 #다시뵙네요", 6);
```
###### #안녕하세요 #반갑습니다 #다시뵙네요

# STRONG
---
```java
strong("default text", TextStyle.DEFAULT);
```
default text

```java
strong("bold text", TextStyle.BOLD);
```
**bold text**

```java
strong("strike through text", TextStyle.STRIKETHROUGH);
```
~~strike through text~~

```java
strong("italic text", TextStyle.ITALIC);
```
*italic text*

# Horizon
---
```java
horizon()
```

# Checkbox
---
```java
checkbox("열심히 일하기", true, TextStyle.DEFAULT);
```
- [x] 열심히 일하기
```java
checkbox("열심히 놀기", true, TextStyle.BOLD);
```
- [x] **열심히 놀기**
```java
checkbox("열심히 공부하기", true, TextStyle.ITALIC);
```
- [x] *열심히 공부하기*
```java
checkbox("열심히 먹기", false, TextStyle.STRIKETHROUGH);
```
- [ ] ~~열심히 먹기~~

# Hyperlink
---
```java
hyperlink("markdown", "https://heropy.blog/2017/09/30/markdown/");
```
[markdown](https://heropy.blog/2017/09/30/markdown/)
```java
hyperlink("markdown", "https://heropy.blog/2017/09/30/markdown/", "markdown 강의");
```
[markdown](https://heropy.blog/2017/09/30/markdown/ "markdown 강의")

# Image
---
```java
img("http://www.gstatic.com/webp/gallery/5.jpg");
```
![image](http://www.gstatic.com/webp/gallery/5.jpg)

```java
img("대체 텍스트", "http://www.gstatic.com/webp/gallery/5.jpg");
```
![대체 텍스트](http://www.gstatic.com/webp/gallery/5.jpg)

```java
img("대체 텍스트", "http://www.gstatic.com/webp/gallery/5.jpg", "이미지 설명");
```
![대체 텍스트](http://www.gstatic.com/webp/gallery/5.jpg "이미지 설명")

# Code
---
```java
code("print(code);");
```
`print(code);`

```java
code("print(code);", CodeLanguage.PYTHON);
```
```python
print(code);
```

```java
code("ls -la", CodeLanguage.NONE);
```
```
ls -la
```

# BlackQuote
---
```java
blackQuote(code("System.out.println(\"Hello World!\");", CodeLanguage.JAVA), 1)
```
> ```java
> System.out.println("Hello World!");
> ```

# orderedList
```java
orderedList(0, "제로");
```
0. 제로

```java
String[] list = {"하나", "둘", "셋"};
orderedList(list, false);
```
1. 하나
2. 둘
3. 셋

```java
String[] list = {"하나", "둘", "셋"};
orderedList(list, true);
```
1. 하나

    2. 둘

        3. 셋


# unorderedList
```java
unorderedList("제로");
```
* 제로
```java
String[] list = {"하나", "둘", "셋"};
unorderedList(list, false);
```
* 하나
* 둘
* 셋

```java
String[] list = {"하나", "둘", "셋"};
unorderedList(list, true);
```
* 하나
    * 둘
        * 셋
        
        
* 하나
    * 둘
        * 셋
       
