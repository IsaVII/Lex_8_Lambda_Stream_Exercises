package se.lexicon;

import se.lexicon.interfaces.PersonAction;
import se.lexicon.interfaces.PersonRule;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
      void main() {
          List<Person> people = fillList();

          //Basic rules
          Predicate<Person> isActive = person -> person.isActive();
          Predicate<Person> isAdult = person -> person.getAge() >= 18;
          Predicate<Person> livesInStockholm = person -> person.getCity().equalsIgnoreCase("Stockholm");

          //Combined rules
          Predicate<Person> isActiveAndAdult =  isActive.and(isAdult);  
          Predicate<Person> isAdultOrFromStockholm = isAdult.or(livesInStockholm);
          Predicate<Person> isNotActive = isActive.negate();
          

          List<Person> activePeople = filterPerson(people, isActive);
          List<Person> adultPeople = filterPerson(people, isAdult);
          List<Person> stockholmPeople = filterPerson(people, livesInStockholm);
          
       /*   
          IO.println("Active people: " + activePeople);
          IO.println("--------------");
          IO.println("Adult people: " + adultPeople);
          IO.println("--------------");
          IO.println("Stockholm people: " + stockholmPeople);
          IO.println("--------------");
          IO.println("--------------");
          */
          
          Consumer<Person> sendMailToPerson = person -> IO.println("Send mail to " + person.getName());
          //sendMailToPerson.execute(people.get(0));  
          
          PersonProcessor.findPeople(people, isActiveAndAdult).forEach(sendMailToPerson::accept);
          IO.println("--------------");
          PersonProcessor.applyToMatching(people, isActiveAndAdult, sendMailToPerson);
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
