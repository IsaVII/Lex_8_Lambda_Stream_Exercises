package se.lexicon;

import se.lexicon.interfaces.PersonAction;
import se.lexicon.interfaces.PersonRule;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

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



          List<Person> activePeopleList =  people.stream()
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


/*         
          IO.println("--------------");
          IO.println("Active people: " + activePeopleList);
          IO.println("--------------");
          IO.println("Mapped names: " + mappedNames);
          IO.println("--------------");
          IO.println("Adults: " + adults);
          IO.println("--------------");
          IO.println("Sorted by age: " + sortByAge);
          IO.println("--------------");
          IO.println("Active in Stockholm: " + activeInStockholm);  
          */

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
          
/*          
          IO.println("--------------");
          IO.println("Active in Stockholm: " + isActiveInStockholm);
          IO.println("--------------");
          IO.println("Active and adult: " + isActiveAndAdultList);
          IO.println("--------------");
          IO.println("InActive over 30: " + inActiveOver30);
          */
          
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
            IO.println("--------------");
            IO.println("Unique first letters: " + uniqueFirstLetters);
             
            
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
