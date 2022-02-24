package example.chain;

import io.d2a.eeee.chain.NullChain;

public class NullChainExample {

    private static class Person {

        private final Person next;
        private final String name;

        public Person(final Person next, final String name) {
            this.next = next;
            this.name = name;
        }
    }

    private static String getNthNameNoChain(Person top, int level) {
        while (level > 0) {
            top = top.next;
            level--;
        }
        return top.name;
    }

    private static String getNethNameWithChain(Person top, int level) {
        NullChain<Person> next = NullChain.of(top);
        while (level > 0) {
            next = next.then(p -> p.next);
            level--;
        }
        return next.then(p -> p.name).get();
    }

    public static void main(String[] args) {
        final Person person = new Person(
            new Person(
                new Person(null, "Simon"),
                "Klaus"),
            "Thorsten"
        );
        System.out.println("With Chain:");
        System.out.println(getNethNameWithChain(person, 0)); // Thorsten
        System.out.println(getNethNameWithChain(person, 1)); // Klaus
        System.out.println(getNethNameWithChain(person, 2)); // Simon
        System.out.println(getNethNameWithChain(person, 3)); // null
        System.out.println();

        System.out.println("NullChain Of");
        System.out.println(NullChain.of((Person) null).then(p -> p.name).get()); // null
        System.out.println(NullChain.of(person)
            .then(p -> p.next)
            .then(p -> p.next)
            .then(p -> p.name)
            .get()); // Simon

        System.out.println(NullChain.of(person)
            .then(p -> p.next)
            .then(p -> p.next)
            .then(p -> p.next)
            .then(p -> p.name)
            .get()); // null
        System.out.println();

        System.out.println("Yolo:");
        System.out.println(NullChain.yolo(() -> person.next.next.name)); // Simon
        System.out.println(NullChain.yolo(() -> person.next.next.next.name)); // null
    }

}
