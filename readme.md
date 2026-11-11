
### Reflection Questions
- Which solutions were easier to read: loop-based or stream-based?
``` 
stream-based: because you can see at the first glance what the code is doing.
```
- Which operations felt like transformations and which felt like side effects?
``` 
PersonRule felt like tranformation because it was used to filter the list of people.
Nothing else beside of the filtering happened (e.g. no printing).

PersonAction had the side effect of printing the person to the console.
```

- Where did Predicate, Function, and Consumer fit naturally?
``` 
Predicate (returns a boolean): used for filtering (e.g., isActive, isAdult)
Function (returns a value): used for transforming (e.g., mapping person to their name or email)
Consumer (returns void): used for side effects (e.g., printing person information)
``` 