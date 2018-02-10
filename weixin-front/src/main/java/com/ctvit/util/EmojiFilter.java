package com.ctvit.util;

import org.apache.commons.lang.StringUtils;

public class EmojiFilter {
	/**
     * 检测是否有emoji字符
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }

    }
    
    /**
     * 测试emoji表情
     */
    public static void main(String[] args) throws Exception {
        String s = " 🈺🍀🍀";
        String c = filterEmoji(s);
    
        String expected = "<body>213这是一个有各种内容的消息,  Hia Hia Hia !!!! xxxx@@@...*)" +
                "!(@*$&@(&#!)@*)!&$!)@^%@(!&#. ], ";
        
        System.out.println(c);
//        assertSame(c, expected);
//        assertSame(expected, "<body>213这是一个有各种内容的消息,  Hia Hia Hia !!!! xxxx@@@...*)" +
//                "!(@*$&@(&#!)@*)!&$!)@^%@(!&#. ], ");
//        assertSame(c, Utils.filterEmoji(c));
    }

}
