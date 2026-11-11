package se.lexicon;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    List<Person> people;

    //Basic rules
    Predicate<Person> isActive = person -> person.isActive();
    Predicate<Person> isAdult = person -> person.getAge() >= 18;
    Predicate<Person> livesInStockholm = person -> person.getCity().equalsIgnoreCase("Stockholm");

    //Combined rules
    Predicate<Person> isActiveAndAdult = isActive.and(isAdult);
    Predicate<Person> isAdultOrFromStockholm = isAdult.or(livesInStockholm);
    Predicate<Person> isNotActive = isActive.negate();

    Consumer<Person> sendMailToPerson = person -> IO.println("Send mail to " + person.getName());

    void main() {
        people = fillList();

        sendMailToPerson.accept(people.get(0));

        ruleComposition();
        reusableProcessor();
        
        streamApi();
        multipleFilters();
        distinctAndSorting();
        mappingAndFormatting();
    }

    private void mappingAndFormatting() {
        List<String> nameAgeCityList = people.stream()
                .map(person -> person.getName() + " (" + person.getAge() + ") - " + person.getCity())
                .toList();

        List<String> emailList = people.stream()
                .map(person -> person.getName().toLowerCase() + "@example.com")
                .toList();

        IO.println("--------------");
        IO.println("Name, Age, City: " + nameAgeCityList);
        IO.println("Email list: " + emailList);
    }

    private void distinctAndSorting() {

        List<String> uniqueCities = people.stream()
                .map(person -> person.getCity())
                .distinct()
                .sorted()
                .toList();

        List<String> uniqueFirstLetters = people.stream()
                .map(person -> person.getName().substring(0, 1))
                .distinct()
                .sorted()
                .toList();

            IO.println("--------------");
            IO.println("Unique cities: " + uniqueCities);
            IO.println("Unique first letters: " + uniqueFirstLetters);
    }

    private void multipleFilters() {
        List<Person> isActiveInStockholm = people.stream()
                .filter(isActive)
                .filter(livesInStockholm)
                .toList();

        List<Person> isActiveAndAdultList = people.stream()
                .filter(isActive)
                .filter(isAdult)
                .toList();
        List<Person> inActiveOver30 = people.stream()
                .filter(isNotActive)
                .filter(person -> person.getAge() > 30)
                .toList();
          
          IO.println("--------------");
          IO.println("Active in Stockholm: " + isActiveInStockholm);
          IO.println("Active and adult: " + isActiveAndAdultList);
          IO.println("InActive over 30: " + inActiveOver30);
    }

    private void streamApi() {

        List<Person> activePeopleList = people.stream()
                .filter(isActive)
                .toList();

        List<String> mappedNames = people.stream()
                .map(person -> person.getName())
                .toList();

        List<Person> adults = people.stream()
                .filter(isAdult)
                .toList();

        List<Person> sortByAge = people.stream()
                .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .toList();

        List<Person> activeInStockholm = people.stream()
                .filter(isActive.and(livesInStockholm))
                .toList();

          IO.println("--------------");
          IO.println("Active people: " + activePeopleList);
          IO.println("Mapped names: " + mappedNames);
          IO.println("Adults: " + adults);
          IO.println("Sorted by age: " + sortByAge);
          IO.println("Active in Stockholm: " + activeInStockholm);
    }

    private void reusableProcessor() {
        IO.println("--------------");
        PersonProcessor.findPeople(people, isActiveAndAdult).forEach(sendMailToPerson::accept);
        PersonProcessor.applyToMatching(people, isActiveAndAdult, sendMailToPerson);
    }

    private void ruleComposition() {
        List<Person> activePeople = filterPerson(people, isActive);
        List<Person> adultPeople = filterPerson(people, isAdult);
        List<Person> stockholmPeople = filterPerson(people, livesInStockholm);

        IO.println("--------------");
        IO.println("Active people: " + activePeople);
        IO.println("Adult people: " + adultPeople);
        IO.println("Stockholm people: " + stockholmPeople);
    }

    private List<Person> filterPerson(List<Person> people, Predicate<Person> rule) {
        List<Person> filteredList = new java.util.ArrayList<>();
        for (Person person : people) {
            if (rule.test(person)) {
                filteredList.add(person);
            }
        }
        return filteredList;
    }

    private List<Person> fillList() {
        return List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true)
        );
    }
}
