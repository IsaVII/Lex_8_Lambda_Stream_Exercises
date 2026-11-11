package se.lexicon;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PersonProcessor {
    
    public static List<Person> findPeople(List<Person> people, Predicate<Person> rule) {
        List<Person> filteredList = new java.util.ArrayList<>();
        for (Person person : people) {
            if (rule.test(person)) {
                filteredList.add(person);
            }
        }
        return filteredList;
    }
    
    public static void applyToMatching(List<Person> people, Predicate<Person> rule,  Consumer<Person> action) {
        for (Person person : people) {
            if (rule.test(person)) {
                action.accept(person);
            }
        }
    }
    
    
}
