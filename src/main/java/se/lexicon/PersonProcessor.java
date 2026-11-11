package se.lexicon;

import se.lexicon.interfaces.PersonAction;
import se.lexicon.interfaces.PersonRule;

import java.util.List;

public class PersonProcessor {
    
    public static List<Person> findPeople(List<Person> people, PersonRule rule) {
        List<Person> filteredList = new java.util.ArrayList<>();
        for (Person person : people) {
            if (rule.check(person)) {
                filteredList.add(person);
            }
        }
        return filteredList;
    }
    
    public static void applyToMatching(List<Person> people, PersonRule rule,  PersonAction action) {
        for (Person person : people) {
            if (rule.check(person)) {
                action.execute(person);
            }
        }
    }
    
    
}
