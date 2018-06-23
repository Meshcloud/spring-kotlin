---?image=assets/image/background.png
# From Java to Kotlin 
The first year in practice
---
## Agenda
### What to expect today?
* My Background
* Why Kotlin?
* Code Code Code
* Demo Application
---
## My Background
@ul
- \>10 years Java
- almost 1 year @ Meshcloud
- solution-oriented developer
@ulend
---
## Why Kotlin?
![Syntax](assets/image/why.jpg)
+++
### 100% Java interoperability
@ul
- all Java frameworks can be used without big problems
- step-by-step conversion of existing Java app
- same development paradigm as Java
- it's Java without the flaws + useful candy
@ulend
+++
### Productivity
@ul
- Practical, not academic
- Expressive
- Developed by JetBrains, Open Source -> great IDE support
- Strictly-Typed -> Null-Safety
@ulend
---
## Integration into existing application
![Syntax](assets/image/build.jpg)
+++
```groovy
buildscript {
  ext {
    kotlinVersion = '1.2.41'
    kotlinPlugin = 'org.jetbrains.kotlin:kotlin-gradle-plugin'
  }
  dependencies {
    classpath "$kotlinPlugin:$kotlinVersion"
  }
}
```
+++
```groovy
apply plugin: "java"
apply plugin: "kotlin"

sourceSets {
  main.kotlin.srcDirs += 'src/main/java'
  main.java.srcDirs += 'src/main/java'
}

dependencies {
  compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
}
```
---
## Context of Code Snippets
@ul
- Billing of Cloud Platform
- Resources (VMs, Apps, etc)
- Projects are container of resources
- Service to calculate costs per project
@ulend
---
## Basic Syntax
![Syntax](assets/image/syntax.jpg)
+++
Variables
```kotlin
val x = "value" // immutable & type inference
var y: Boolean // mutable
```
+++
Type Inference
```java
// Java
Map<ResourceType, List<Resource>> resourceMapByType = 
    service.findResourceMapByType();
```
```kotlin
// Kotlin
val resourceMapByType = service.findResourceMapByType()
```
+++
Functions
```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}

// expression body & type inference
fun sum(a: Int, b: Int) = a + b 
```
+++
### Everything is an expression
```kotlin
fun formatText() = if (projects.isEmpty()) "n/a" else projects
```
---
## Null Safety
![Syntax](assets/image/null.jpg)
+++
Null checks during compilation
```kotlin
var x = "value"
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
```kotlin
tryFindProjectList() ?: emptyList()
```
---
## Scoping Functions
![Syntax](assets/image/scope.jpg)
+++
Null check in Java
```java
public Date convertToDatabaseColumn(Instant instant) {
    return instant != null ? Date.from(instant) : null;
}
```
+++
let in Kotlin
```kotlin
// let: return result & use "it" for current object
fun convertToDatabaseColumn(instant: Instant?): Date? {
  return instant?.let { Date.from(it) }
}
```
+++
run
```kotlin
// run: return result & use "this" for current object
fun convertToEntityAttribute(value: Date?): Instant? {
  return value?.run { toInstant() }
}
```
+++
apply
```kotlin
// apply: return self & use "this" for current object
val headers = HttpHeaders().apply {
  contentType = MediaType.APPLICATION_JSON
  add("ProjectId", project.id)
}
```
+++
also
```kotlin
// also: return self & use "it" for current object
fun createAndLogProject() {
  return generateProject().also { log(it) }
}

```
---
## Mighty little helpers
![Syntax](assets/image/helper.jpg)
+++
String templates
```kotlin
val text = "Project ${project.name}: $details"
```
+++
Structural equality with ==
```kotlin
project == otherProject // calls null-safe equals
project === otherProject // referential equality
```
+++
When expressions
```kotlin
when {
  "Bitcoin" in projectNames -> println("Bad guy!")
  projectNames is empty -> println("No Projects")
  else -> println("Just normal projects!")
}
```
+++
Collection creation
```kotlin
var list = listOf('a', 'b', 'c')
var set = setOf(1, 2)
var map = mapOf('a' to 5, 'b' to 10)

```
+++
Ranges
```kotlin
for (i in 1..100)
for (i in 10 downTo 1)
```
+++
Destructuring
```kotlin
for ((key, value) in map) {
    print("Key: $key, Value: $value")
}
```
---
## Concise Streaming API
![Syntax](assets/image/stream.jpg)
+++
Java
```java
projectRepository
  .findAll()
  .stream()
  .map(p -> new ProjectListDTO(p))
  .collect(Collectors.toList());
```
+++
Kotlin
```kotlin
projectRepository
  .findAll()
  .map { ProjectListDTO(it)}
```
---
## Avoid boilerplate
![Syntax](assets/image/straight.jpg)
+++
Implicit Getters/Setters & property access:
```kotlin
class Project {
    var name: String
}
val pName = Project().name
```
+++
Data classes (with equals, hashcode, toString & copy):
```kotlin
data class Project(
  val name: String,
  val description: String
) {
  val fullText: String
    get() = "$name - $description"
}
```
---
## Expressive Arguments
![Syntax](assets/image/arguments.jpg)
+++
Default Arguments
```kotlin
fun create(
  name: String,
  description: String = "default"
)
```
+++
Named Arguments
```kotlin
create(
  name = "My Project",
  description = "Some details"
)
```
---
## Higher Order Functions
![Syntax](assets/image/high.jpg)
+++
Java (Function, Consumer, Supplier, BiConsumer, …)
```java
public String myFunction(
  BiFunction<String, String, String> fn
) {
    fn.apply("1", "2") // .get() on Supplier, ...
}
```
+++
Kotlin
```kotlin
fun myFunction(
  fn: (String, String) -> String
) {
  fn("1", "2")
}

```
---
## Extension Functions
![Syntax](assets/image/extender.jpg)
+++
```kotlin
val customFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

fun Date.formatted(): String {
  return customFormat.format(this)
}

val dateAsText = Date().formatted()

```
@[1-5]
@[6-7]
+++
```kotlin
fun ProjectRepository.findOneOrThrow(id: Long): Project {
  return findOne(id) 
    ?: throw NotFoundException("Could not find project with id $id")
}
```
---
## Generics
![Syntax](assets/image/generic.jpg)
+++
Reified Generics
```kotlin
inline fun <reified T : Any> createEmptyWrappedResource()
    : PagedResources<EmbeddedWrapper> {
  val wrappers = EmbeddedWrappers(false)
  val emptyWrapper = wrappers.emptyCollectionOf(T::class.java)
  val embedded = listOf(emptyWrapper)
  return PagedResources(embedded)
}
```
---
## Demo Application
![Syntax](assets/image/cloud.jpg)
+++
LoC

|               |               |
| ------------- |:-------------:|
| Java          | 460           |
| Kotlin        | 252           |
| Reduction     | 46%           |
[https://github.com/Meshcloud/spring-kotlin-example](https://github.com/Meshcloud/spring-kotlin-example)
+++
LoC without data classes

|               |               |
| ------------- |:-------------:|
| Java          | 232           |
| Kotlin        | 185           |
| Reduction     | 21%           |
[https://github.com/Meshcloud/spring-kotlin-example](https://github.com/Meshcloud/spring-kotlin-example)
---
### Questions?

<br>

@fa[twitter gp-contact](@meshcloud)

@fa[github gp-contact](meshcloud)

[https://github.com/Meshcloud/spring-kotlin-example @fa[git-repo]](https://github.com/Meshcloud/spring-kotlin-example)
