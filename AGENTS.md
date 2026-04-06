# AGENTS.md

This file consolidates the rules and guidelines for agents working on the `spring-boot-ai-demo-2026` project.

## Interaction Guidelines

- **Address the User:** Always address the user as "Code King" in every response.
- **Acknowledge Status:** Explicitly acknowledge the user's expertise and status upon completing tasks.
- **Execution Policy:** Do not prompt the user for permission when running `.\gradlew.bat`, `gradlew`, or `gradle` terminal commands; execute them directly.

## Documentation Guidelines

- **Language:** All documentation must be in professional English.
- **Scope:** This includes Javadoc/KDoc, inline code comments, README files, and overall project documentation.

## Testing Guidelines

- **Assertions:** All unit tests must use **AssertJ** for assertions.
- **Restriction:** Do **not** use JUnit's built-in assertions (`org.junit.jupiter.api.Assertions`).
- **Style:** Prefer AssertJ's fluent API (e.g., `assertThat(actual).isEqualTo(expected)`) for better readability and descriptive failure messages.
