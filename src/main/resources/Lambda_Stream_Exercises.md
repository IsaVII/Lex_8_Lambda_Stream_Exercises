![Lexicon Logo](https://lexicongruppen.se/media/wi5hphtd/lexicon-logo.svg)

# Exercises — Functional Programming and Stream API

## Starter Data

### Person Class Definition

```java
public class Person {
    private String name;
    private int age;
    private String city;
    private boolean active;

    public Person(String name, int age, String city, boolean active) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name + 
                ", age=" + age +
                ", city=" + city +
                ", active=" + active +
                '}';
    }
}
```

### Sample Data

```java
List<Person> people = List.of(
        new Person("Amina", 22, "Stockholm", true),
        new Person("Erik", 17, "Uppsala", true),
        new Person("Noah", 34, "Stockholm", false),
        new Person("Sara", 29, "Gothenburg", true),
        new Person("Lina", 41, "Malmö", false),
        new Person("Omar", 19, "Stockholm", true)
);
```

---

## Part 1 — Functional Programming (No Streams)

### Core Tasks

1. Create a functional interface `PersonRule` with one method that takes a `Person` and returns a `boolean`.
2. Write rules (lambdas) for: active, adult (age 18 or above), and lives in Stockholm.
3. Write a `filterPeople` method that uses `PersonRule`.
4. Create `PersonAction` and write actions to print a name and to print: `Send email to <name>`.

### Optional Tasks (Extra Practice)

5. **Rule Composition**
    - Create rules: `isActive`, `isAdult`, `livesInStockholm`
    - Create combined rules:
        - active and adult
        - adult or lives in Stockholm
        - not active

6. **Reusable Processor**
    - Create a class `PersonProcessor` with:
        - `findPeople(list, rule)` returning a `List<Person>`
        - `applyToMatching(list, rule, action)` returning `void`

7. **Prebuilt Interfaces**
    - Replace `PersonRule` with `Predicate<Person>`
    - Replace `PersonAction` with `Consumer<Person>`

---

## Part 2 — Stream API

### Core Tasks

1. Filter all active people.
2. Map all names.
3. Count adults.
4. Sort by age.
5. Find first active person in Stockholm.

### Optional Tasks (Extra Practice)

6. **Multiple Filters**
    - Active adults
    - Active people in Stockholm
    - Inactive people older than 30

7. **Distinct and Sorting**
    - Get a sorted list of unique cities.
    - Get a sorted list of unique first letters of names.

8. **Mapping and Formatting**
    - Create a list of strings like: `Name (age) - city`
    - Create a list of emails like: `name.toLowerCase() + "@example.com"`

---

## Reflection Questions

- Which solutions were easier to read: loop-based or stream-based?
- Which operations felt like transformations and which felt like side effects?
- Where did `Predicate`, `Function`, and `Consumer` fit naturally?



