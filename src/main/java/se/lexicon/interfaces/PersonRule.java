package se.lexicon.interfaces;

import se.lexicon.Person;

@FunctionalInterface
public interface PersonRule {
    boolean check(Person person);
    
    default PersonRule and(PersonRule other) {
        return person -> check(person) && other.check(person);
    }
    
    default PersonRule or(PersonRule other) {
        return person -> check(person) || other.check(person);
    }
    
    default PersonRule negate() {
        return person -> !check(person);
    }
}
