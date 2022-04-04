package example.util.access;

import io.d2a.eeee.annotation.annotations.other.AccessGetSet;
import io.d2a.eeee.util.Target;

public class AccessExample {

    public static class Test {

        public String user;
        private String password;

        public Test() {
            this.user = "changeme";
            this.password = "ch4ng3m3";
        }

        @Override
        public String toString() {
            return "Test{" + "user='" + user + '\'' + ", password='" + password + '\'' + '}';
        }

    }

    public interface TestWiz {

        @Target("user")
        String getUser();

        @Target("user")
        void setUser(final String user);

        @Target("password")
        String getPassword();

        @Target("password")
        void setPassword(final String password);

    }

    public static void main(String[] args) {
        final Test test = new Test();
        System.out.println(test);

        final TestWiz wiz = AccessGetSet.create(test, TestWiz.class);
        System.out.println(wiz.getUser());
        System.out.println(wiz.getPassword());
        System.out.println(test);

        wiz.setUser("daniel");
        System.out.println(test);

        wiz.setPassword("helloworld");
        System.out.println(test);
    }

}
