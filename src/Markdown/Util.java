package Markdown;

public class Util {
    private static final int TAB_KEY_SPACE = 4;
    private static final String SPACE = " ";
    private static final String NEW_LINE = SPACE.repeat(2);

    // header
    public static
    String header(String target, int size) throws Exception
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
    public static
    String strong(String target, TextStyle type) throws Exception
    {
        if (type.equals(TextStyle.BOLD)) { return "**" + target + "**"; }

        if (type.equals(TextStyle.STRIKETHROUGH)) { return "~~" + target + "~~"; }

        if (type.equals(TextStyle.ITALIC)) { return "*" + target + "*"; }

        if (type.equals(TextStyle.DEFAULT)) { return target; }

        throw new Exception("");
    }


    // horizon
    public static
    String horizon() { return "---"; }


    // checkbox
    public static
    String checkbox(String target, Boolean boxCondition, TextStyle style) throws Exception
    {
            return boxCondition ?
                    ("- [x] " + strong(target, style)) :
                    ("- [ ] " + strong(target, style));
    }


    // hyperlink
    public static String hyperlink(String alt, String path) { return "[" + alt + "](" + path + ")"; }

    public static
    String hyperlink(String alt, String path, String description)
    {
        return "[" + alt + "](" + path + " \"" + description + "\")";
    }


    // image
    public static String img(String alt, String path) { return "!" + hyperlink(alt, path); }

    public static
    String img(String alt, String path, String title) { return "!" + hyperlink(alt, path, title); }

    public static String img(String path) { return "![image](" + path + ")"; }


    // code
    public static String code(String code) { return "`" + code + "`"; }

    public static String code(String code, CodeLanguage codeLanguage)
    {
        if (codeLanguage.equals(CodeLanguage.NONE))
        {
            return "```\n" + code + "\n```";
        }
        return "```" + codeLanguage.toString().toLowerCase() + "\n" + code + "\n```";
    }


    // black quote
    public static String blackQuote(String target, int size) throws Exception
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

    public static
    String orderedList(int number ,String target, int enter)
    {
        return " ".repeat(enter * TAB_KEY_SPACE) + orderedList(number, target);
    }

    private static StringBuilder getOrderedList(String [] list, boolean enter)
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

    String orderedList(String [] list, boolean enter) { return getOrderedList(list, enter).toString(); }


    // unorderedList
    static String unorderedList(String target) { return "* " + target; }

    static String unorderedList(String target, int enter) { return " ".repeat(enter * TAB_KEY_SPACE) + unorderedList(target); }

    static StringBuilder getUnorderedList(String[] list, boolean enter)
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

    public static
    String unorderedList(String [] list, boolean enter) { return getUnorderedList(list, enter).toString(); }
}