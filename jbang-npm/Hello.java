///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS com.intuit.karate:karate-core:1.4.1:all
//DEPS com.github.javafaker:javafaker:1.0.2

class Hello {

    public static void main(String[] args) throws Exception {
        com.intuit.karate.Main.main(args);
    }
}
