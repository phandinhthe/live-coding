package org.terry;

import java.util.stream.Stream;

public class LiveMain {
    public static void main(String[] args) {

        String s = Stream
            .of("1", "2", "3")
            .reduce("Hello ", (str, b) -> str.concat(" ").concat(b));

        System.err.println(s);
    }

}