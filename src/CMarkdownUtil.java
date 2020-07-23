import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class CMarkdownUtil {
    private static final int TAB_KEY_SPACE = 4;
    private static final String SPACE = " ";
    private static final String NEW_LINE = SPACE.repeat(2);

    public enum TextStyle
    {
        DEFAULT,
        ITALIC,
        BOLD,
        STRIKETHROUGH
    }

    public enum CodeLanguage
    {
        JAVA,
        JAVASCRIPT,
        PYTHON,
        GO,
        CPP,
        C,
        C_SHARP,
        SWIFT,
        PHP,
        SQL,
        RUBY,
        VISUAL_BASIC,
        BASH,
        CMD,
        HTML,
        CSS,
        R,
        MATLAB,
        VISUAL_BASIC_DOT_NET,
        PERI,
        OBJECT_C,
        ASSEMBLY_LANGUAGE,
        SAS,
        DART,
        GROOVY,
        SCRATCH,
        RUST,
        COBOL,
        SCALA,
        FORTRAN,
        KOTLIN,
        F_SHARP,
        NONE
    }

    // header
    public static @NotNull
    String header(@NotNull String target, int size) throws Exception
    {
        if (target.isBlank() || target.isEmpty()) { throw new Exception("빈 문자열 값 입니다."); }
        if (size <= 0 || size > 6) { throw new Exception("잘못된 크기입니다. 크기는 1 ~ 6이어야 합니다."); }

        String[] sp = target.split("");

        for (int i = 1; i < sp.length; i++)
        {
            if(sp[i].equals(" ") && sp[i - 1].equals("#")) { return "#".repeat(size) + target.substring(i); }
        }
        return "#".repeat(size) + " " + target;
    }


    // strong
    @Contract(pure = true)
    public static @NotNull
    String strong(String target, @NotNull TextStyle type) throws Exception
    {
        if (type.equals(TextStyle.BOLD)) { return "**" + target + "**"; }

        if (type.equals(TextStyle.STRIKETHROUGH)) { return "~~" + target + "~~"; }

        if (type.equals(TextStyle.ITALIC)) { return "*" + target + "*"; }

        if (type.equals(TextStyle.DEFAULT)) { return target; }

        throw new Exception("");
    }


    // horizon
    @Contract(pure = true)
    public static @NotNull
    String horizon() { return "---"; }


    // checkbox
    @Contract(pure = true)
    public static @NotNull
    String checkbox(@NotNull String target, Boolean boxCondition, @NotNull TextStyle style) throws Exception
    {
            return boxCondition ?
                    ("- [x] " + strong(target, style)) :
                    ("- [ ] " + strong(target, style));
    }


    // hyperlink
    @Contract(pure = true)
    public static @NotNull String hyperlink(String alt, String path) { return "[" + alt + "](" + path + ")"; }

    @Contract(pure = true)
    public static @NotNull
    String hyperlink(String alt, String path, String description)
    {
        return "[" + alt + "](" + path + " \"" + description + "\")";
    }


    // image
    @Contract(pure = true)
    public static @NotNull String img(String alt, String path) { return "!" + hyperlink(alt, path); }

    @Contract(pure = true)
    public static @NotNull
    String img(String alt, String path, String title) { return "!" + hyperlink(alt, path, title); }

    @Contract(pure = true)
    public static @NotNull String img(String path) { return "![image](" + path + ")"; }


    // code
    @Contract(pure = true)
    public static @NotNull String code(String code) { return "`" + code + "`"; }

    @Contract(pure = true)
    public static @NotNull String code(String code, @NotNull CodeLanguage codeLanguage)
    {
        if (codeLanguage.equals(CodeLanguage.NONE))
        {
            return "```\n" + code + "\n```";
        }
        return "```" + codeLanguage.toString().toLowerCase() + "\n" + code + "\n```";
    }


    // black quote
    public static @NotNull String blackQuote(String target, int size) throws Exception
    {
        if(size <= 0) { throw new Exception("크기가 0보다 작거나 같습니다."); }

        String[] temp_array = target.split("\n");
        StringBuilder return_ = new StringBuilder();

        for (int i = 0; i < temp_array.length; i++)
        {
            if (i == temp_array.length - 1)
            {
                return_.append(">".repeat(size)).append(" ").append(temp_array[i]);
            }
            else
            {
                return_.append(">".repeat(size)).append(" ").append(temp_array[i]).append("\n");
            }
        }
        return return_.toString();
    }


    // orderedList
    public static
    String orderedList(int number, String target)
    {
        return String.format("%d. %s", number, target);
    }

    public static @NotNull
    String orderedList(int number ,String target, int enter)
    {
        return " ".repeat(enter * TAB_KEY_SPACE) + orderedList(number, target);
    }

    @NotNull
    private static StringBuilder getOrderedList(String @NotNull [] list, boolean enter)
    {
        StringBuilder return_ = new StringBuilder();

        for (int i = 0; i < list.length; i++)
        {
            if (i == list.length - 1) {
                if (enter) {
                    return_.append(orderedList(i + 1, list[i], i));
                } else {
                    return_.append(orderedList(i + 1, list[i]));
                }
            } else {
                if (enter) {
                    return_.append(orderedList(i + 1, list[i], i)).append("\n\n");
                } else {
                    return_.append(orderedList(i + 1, list[i])).append("\n\n");
                }
            }
        }
        return return_;
    }

    public static @NotNull
    String orderedList(String @NotNull [] list, boolean enter) { return getOrderedList(list, enter).toString(); }


    // unorderedList
    public static @NotNull
    String unorderedList(String target) { return "* " + target; }

    public static @NotNull
    String unorderedList(String target, int enter) { return " ".repeat(enter * TAB_KEY_SPACE) + unorderedList(target); }

    private static @NotNull
    StringBuilder getUnorderedList(String @NotNull [] list, boolean enter)
    {

        StringBuilder return_ = new StringBuilder();
        for (int i = 0; i < list.length; i++)
        {
            if (enter) {
                return_
                        .append(unorderedList(list[i], i))
                        .append("\n");
            } else {
                return_.append(unorderedList(list[i])).append("\n");
            }
        }
        return return_;
    }

    public static @NotNull
    String unorderedList(String @NotNull [] list, boolean enter) { return getUnorderedList(list, enter).toString(); }

    public static
    void print(String s) { System.out.println(s); }

    public static void main(String[] args) throws Exception
    {
        // Header
        print("Header");
        print(header("#안녕하세요 #반갑습니다 #다시뵙네요", 2));
        print(header("### #안녕하세요 #반갑습니다 #다시뵙네요", 6));
        print("");


        // strong
        print("Strong");
        print(strong("default text", TextStyle.DEFAULT));
        print(strong("bold text", TextStyle.BOLD));
        print(strong("strike through text", TextStyle.STRIKETHROUGH));
        print(strong("italic text", TextStyle.ITALIC));
        print("");

        // horizon
        print("Horizon");
        print(horizon());
        print("");


        // checkbox
        print("Checkbox");
        print(checkbox("열심히 일하기", true, TextStyle.DEFAULT));
        print(checkbox("열심히 놀기", true, TextStyle.BOLD));
        print(checkbox("열심히 공부하기", true, TextStyle.ITALIC));
        print(checkbox("열심히 먹기", false, TextStyle.STRIKETHROUGH));
        print("");

        // hyperlink
        print("Hyperlink");
        print(hyperlink("markdown", "https://heropy.blog/2017/09/30/markdown/"));
        print(hyperlink("markdown", "https://heropy.blog/2017/09/30/markdown/", "markdown 강의"));
        print("");

        // img
        print("Image");
        print(img("http://www.gstatic.com/webp/gallery/5.jpg"));
        print(img("대체 텍스트", "http://www.gstatic.com/webp/gallery/5.jpg"));
        print(img("대체 텍스트", "http://www.gstatic.com/webp/gallery/5.jpg", "이미지 설명"));
        print("");


        // code
        print("Code");
        print(code("print(code);"));
        print(code("print(code);", CodeLanguage.PYTHON));
        print(code("ls -la", CodeLanguage.NONE));
        print("");

        // blackQuote
        print("BlackQuote");
        print(blackQuote(code("System.out.println(\"Hello World!\");", CodeLanguage.JAVA), 1));
        print("");


        String[] list = {"하나", "둘", "셋"};
        // orderedList
        print("orderedList");
        print(orderedList(0, "제로"));
        print(orderedList(list, false));
        print(orderedList(list, true));
        print("");


        // unorderedList
        print("unorderedList");
        print(unorderedList("제로"));
        print(unorderedList(list, false));
        print(unorderedList(list, true));
        print("");
    }
}