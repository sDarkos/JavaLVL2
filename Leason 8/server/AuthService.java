package server;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthService {

    private static final List<Entry> entries;

    static {
        entries = List.of(
                new Entry("name1", "login1", "pass1"),
                new Entry("name2", "login2", "pass2"),
                new Entry("name3", "login3", "pass3")
        );
    }

    public Optional<Entry> findUserByLoginAndPassword(String login, String pass){
        return entries.stream()
                .filter(entry -> entry.Login.equals(login) && entry.pass.equals(pass))
                .findFirst();
    }

    public boolean isCreated(String nick){
        return entries.stream().anyMatch(entry -> entry.name.equals(nick));
    }

    static class Entry{
        String name;
        String Login;
        String pass;

        public String getName() {
            return name;
        }

        public String getLogin() {
            return Login;
        }

        public String getPass() {
            return pass;
        }

        Entry(String name, String login, String pass) {
            this.name = name;
            this.Login = login;
            this.pass = pass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(name, entry.name) && Objects.equals(Login, entry.Login) && Objects.equals(pass, entry.pass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, Login, pass);
        }
    }
}
