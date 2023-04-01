package playground;

public class JavaPlayground {
    public static void main(String args[]) {
        System.out.println(Person.N_EYES);
    }

    // C++ equivalent would be Person::N_EYES
}

class Person {
    public static final int N_EYES = 2; // Class-level functionality
}
