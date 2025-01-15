package lt.vtmc.praktiniai.users;

import java.util.List;

public class Praktiniai {

    public static Integer countUsersOlderThen25(List<User> users) {
        if (users == null) {
            throw new UnsupportedOperationException("Null");
        }
        int usersOlderThan25 = 0;
        for (User i : users) {
            if (i.getAge() > 25) usersOlderThan25++;
        }
        return usersOlderThan25;
    }

    public static double getAverageAge(List<User> users) {
        if (users == null) {
            throw new UnsupportedOperationException("Null");
        }
        return (double) sumAge(users) / users.size();
    }

    public static Integer getMinAge(List<User> users) {
        if (users == null) {
            throw new UnsupportedOperationException("Null");
        }
        int min = users.get(0).getAge();
        for(User i : users){
            if(min > i.getAge()) min = i.getAge();
        }
        return min;
    }

    public static User findByName(List<User> users, String name) {
        if (users == null) {
            throw new UnsupportedOperationException("Null");
        }
        for (User i : users){
            if(i.getName().contains(name))
        }
        return
    }

    public static List<User> sortByAge(List<User> users) {
        if (users == null) {
            throw new UnsupportedOperationException("Null");
        }
        return 0;
    }

    public static User findOldest(List<User> users) {
        if (users == null) {
            throw new UnsupportedOperationException("Null");
        }
        User oldest = users.get(0);
        for(User i : users){
            if(oldest.getAge() < i.getAge()) oldest = i;
        }
        return oldest;
    }

    public static int sumAge(List<User> users) {
        if (users == null) {
            throw new UnsupportedOperationException("Null");
        }
        int totalAge = 0;
        for (User i : users){
            totalAge += i.getAge();
        }
        return totalAge;
    }

}
