package se.lexicon.interfaces;

import se.lexicon.Person;

@FunctionalInterface
public interface PersonRule {
    boolean check(Person person);
}
