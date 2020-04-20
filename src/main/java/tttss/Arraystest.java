package tttss;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
//src/main/java/life/majiang/community/service/QuestionService.java
public class Arraystest {
    public static void main(String[] args) {
        String search = "? + *阿 +";
        String[] tags = StringUtils.split(search, " ");
        search = Arrays
                .stream(tags)
//                .filter(StringUtils::isNotBlank)
                .map(t -> t.replace("+", "").replace("*", "").replace("?", ""))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("|"));
        System.out.println(search);
    }
}
