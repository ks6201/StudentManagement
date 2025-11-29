# Concept Answers

## 1. Can you explain the difference between JIT and Interpreter?

→ JIT (Just-in-Time) in JVM is used for compiling the bytecode instructions to machine code, especially this happens when JVM identifies certain logic being called very often and considered hot-spots, so those logic gets compiled to native machine code by JIT.

Interpreter in JVM executes bytecode instruction by instruction and It is slower than JIT because instruction must be understood and then it performs the correct operation.

---

## 2. When would you use abstract classes vs interfaces?

→ Abstract classes and interfaces are used to hide complex implementation from user.

The big difference b/w abstract class and interface is an Abstract Class can have data members, methods with body and methods without body (abstract method) and If your classes need to share common data or state, then you should use an abstract class.

Interface is used for defining behaviours only, In Java, interfaces can have methods marked as default and static and if marked as one of them then you need to provide a method body to it.

---

## 3. How does method overriding differ from overloading?

→ Overriding refers to when child class re-write its own method body of a already existing method having same signatures (method name, method params) from parent class.

Whereas Overloading happens when method having same name but either different param data types or different number of params.

---

## 4. What is the significance of constructor chaining?

→ So constructor chaining helps us to avoid writing same instance variable initialization statement in all the constructor we are declaring, and we can call one main constructor we decide on, and pass some default values to it, from other declared constructors.