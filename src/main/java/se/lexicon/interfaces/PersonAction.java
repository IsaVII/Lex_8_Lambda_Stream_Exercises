package se.lexicon.interfaces;

import se.lexicon.Person;

@FunctionalInterface
public interface PersonAction {
    void execute(Person person);
}
