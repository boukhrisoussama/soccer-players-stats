---
applyTo: "**/*.java"
---
# GitHub Copilot Instructions for Java

Please follow these guidelines when generating Java code:
- Adhere to clean code programming principles described by Robert C. Martin summarized in the files under ../instructions/*.instructions.md.
- Use standard Java conventions and best practices.
- Ensure code is well-structured and modular.
- Include appropriate error handling and input validation.
- Write clear and concise comments to explain complex logic.
- Use Java 17 or later features where applicable (e.g., streams, lambdas).
- When generating classes, include constructors, getters, setters, and toString methods as needed.
- For collections, prefer using the Java Collections Framework (e.g., List, Set, Map).
- When working with dates and times, use the java.time package.
- Ensure compatibility with popular Java frameworks (e.g., Spring, Hibernate) if relevant to the context.
- Write unit tests using JUnit 5 or later, and use Mockito for mocking dependencies.
- Follow SOLID principles to ensure code maintainability and scalability.
- When generating code for web applications, follow RESTful API design principles.
- Ensure proper use of access modifiers (public, private, protected) to encapsulate data.
- Avoid using deprecated methods and classes; prefer modern alternatives.
