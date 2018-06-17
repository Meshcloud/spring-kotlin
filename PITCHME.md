---?image=assets/image/background.png
# From Java to Kotlin 
The first year in practice
---
## Agenda
### What to expect today?
* My Background
* Why Kotlin?
* Code Code Code
---
## My Background
* \>10 years Java
* almost 1 year @ Meshcloud
* solution-oriented developer
---
## Why Kotlin?
### 100% Java interoperability
* all Java frameworks can be used without big problems
* step-by-step conversion of existing Java app
* same development paradigm as Java
* it's Java without the flaws + useful candy
+++
## Why Kotlin?
### Productivity
* Practical, not academic
* Expressive
* Developed by JetBrains, Open Source -> great IDE support
* Strictly-Typed -> Null-Safety
---
## Basic Syntax
### Variables
```kotlin
val x = "value" // immutable & type inference
var y: Boolean // mutable
```
### Functions
```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum(a: Int, b: Int) = a + b // expression body & type inference
```
+++
## Basic Syntax
### Everything is an expression
```kotlin
fun formatText() = if (users.isEmpty()) "n/a" else users
```
---
## Null Safety
Null checks during compilation
```kotlin
var x = “value”
x = null // compile error
```
+++
Safe calls
```kotlin
var x: MyClass?
val child = x?.child // if x == null -> null
```
+++
Intelligent compiler
```kotlin
if (x != null) {
  val child = x.child // knows that x != null
}
```
+++
Smart casts
```kotlin
if (x is String) {
    print(x.length) // x is automatically cast to String
}
```
+++
Elvis Operator
```kotlin
// findOne or throw exception if result == null
projectRepository.findOne(id) ?: throw NotFoundException()
```
---
## Scoping Functions
Java
```java
public Date convertToDatabaseColumn(Instant instant) {
    return instant != null ? Date.from(instant) : null;
}
```
Kotlin
```kotlin
fun convertToDatabaseColumn(instant: Instant?): Date? {
  return instant?.let { Date.from(it) }
}
```
+++
```kotlin
val headers = HttpHeaders().apply {
  contentType = MediaType.APPLICATION_JSON
  add("ProjectId", project.id)
}
```
+++
```kotlin
fun convertToEntityAttribute(value: Date?): Instant? {
  return value?.run { toInstant() }
}
```
---

### Questions?

<br>

@fa[twitter gp-contact](@meshcloud)

@fa[github gp-contact](meshcloud)

[https://github.com/Meshcloud/spring-kotlin-example @fa[git-repo]](https://github.com/Meshcloud/spring-kotlin-example)
