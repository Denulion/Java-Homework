package lt.techin.stream;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPraktiniai {

    public static List<Integer> returnSquareRoot(List<Integer> numbers){
        return numbers.stream().map(n -> (int) Math.sqrt(n)).collect(Collectors.toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> users){
        return users.stream().map(User::getAge).toList();
    }

    public static List<Integer> getDistinctAges(List<User> users){
        return users.stream().map(User::getAge).distinct().toList();
    }

    public static List<User> getLimitedUserList(List<User> users, int limit){
        return users.stream().limit(limit).toList();
    }

    public static Integer countUsersOlderThen25(List<User> users){
        return (int) users.stream().filter(u -> u.getAge() > 25).count();
    }

    public static List<String> mapToUpperCase(List<String> strings){
        return strings.stream().map(String::toUpperCase).toList();
    }

    public static Integer sum(List<Integer> integers){
        return integers.stream().reduce(0, Integer::sum);
//        return integers.stream().reduce(0, (a, b) -> a + b);
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip){
        return integers.stream().filter(i -> i > toSkip).toList();
    }

    public static List<String> getFirstNames(List<String> names){
        return names.stream().map(i -> i.split(" ")[0]).toList();
    }

    public static List<String> getDistinctLetters(List<String> names){
        return names.stream().flatMap(i -> Arrays.stream(i.split(""))).distinct().toList();
    }

    public static String separateNamesByComma(List<User> users){
        return users.stream().map(i -> i.getName().split(" ")[0]).collect(Collectors.joining(", "));
    }

    public static double getAverageAge(List<User> users){
        return users.stream().mapToInt(User::getAge).average().orElse(0.0);
    }

    public static Integer getMaxAge(List<User> users){
        return users.stream().mapToInt(User::getAge).max().orElse(0);
    }

    public static Integer getMinAge(List<User> users) {
        return users.stream().mapToInt(User::getAge).min().orElse(0);
    }

    public static boolean anyMatch(List<User> users, int age){
        return users.stream().anyMatch(i -> i.getAge() == age);
    }

    public static boolean noneMatch(List<User> users, int age){
        return users.stream().noneMatch(i -> i.getAge() == age);
    }

    public static Optional<User> findAny(List<User> users, String name){
        return users.stream().filter(user -> user.getName().equals(name)).findAny();
    }

    public static List<User> sortByAge(List<User> users){
        return users.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream){
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers(){
        return IntStream.rangeClosed(2, 29).filter(StreamPraktiniai::isPrime).boxed().toList();
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
    }

    public static List<Integer> generate10RandomNumbers(){
        return IntStream.rangeClosed(1, 10).map(i -> ThreadLocalRandom.current().nextInt(1, 101)).boxed().toList();
    }

    public static User findOldest(List<User> users){
        return users.stream().max(Comparator.comparing(User::getAge)).orElse(null);
    }

    public static int sumAge(List<User> users){
        return users.stream().map(User::getAge).reduce(0, Integer::sum);
    }

    //Pvz.:
    //Java 8 Streams API: Grouping and Partitioning a Stream
    //https://www.javacodegeeks.com/2015/11/java-8-streams-api-grouping-partitioning-stream.html

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users){
        return users.stream().collect(Collectors.partitioningBy(User::isMale));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users){
        return users.stream().collect(Collectors.groupingBy(User::getAge));
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users){
        return users.stream().collect(Collectors.groupingBy(User::isMale, Collectors.groupingBy(User::getAge)));
    }

    public static Map<Boolean, Long> countGender(List<User> users){
        return users.stream().collect(Collectors.groupingBy(User::isMale, Collectors.counting()));
    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users){
        return users.stream().collect(Collectors.summarizingInt(User::getAge));
    }

}
