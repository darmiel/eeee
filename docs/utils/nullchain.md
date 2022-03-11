# Null-Chain

Emulates Optional Chaining (`?.`) for Java.

Example (JavaScript):
```javascript
const val = obj?.owner?.name;
```

Example (NullChain):
```java
final String name = NullChain.of(obj).then(Obj::owner).then(Person::name).orNull();
```

If the value is `null` in any `then`-step, `null` is returned.
So you don't have to worry about whether an NPE could be thrown.

Psychopaths can also use the yolo() method, for a shorter syntax, but at what cost...

```java
final String name = NullChain.yolo(() -> obj.owner.name);
```