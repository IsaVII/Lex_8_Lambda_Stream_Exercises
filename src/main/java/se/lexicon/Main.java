package se.lexicon;

import se.lexicon.interfaces.PersonAction;
import se.lexicon.interfaces.PersonRule;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
      void main() {
          List<Person> people = fillList();

          PersonRule isActive = person -> person.isActive();
          PersonRule isAdult = person -> person.getAge() >= 18;
          PersonRule isFromStockholm = person -> person.getCity().equalsIgnoreCase("Stockholm");


          List<Person> activePeople = filterPerson(people, isActive);
          List<Person> adultPeople = filterPerson(people, isAdult);
          List<Person> stockholmPeople = filterPerson(people, isFromStockholm);
          
          IO.println("Active people: " + activePeople);
          IO.println("--------------");
          IO.println("Adult people: " + adultPeople);
          IO.println("--------------");
          IO.println("Stockholm people: " + stockholmPeople);
          IO.println("--------------");
          IO.println("--------------");
          
          
          PersonAction sendMailToPerson = person -> IO.println("Send mail to " + person.getName());
          sendMailToPerson.execute(people.get(0));  
      }
      
      private List<Person> filterPerson(List<Person> people, PersonRule rule) {
          List<Person> filteredList = new java.util.ArrayList<>();
          for (Person person : people) {
              if (rule.check(person)) {
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
