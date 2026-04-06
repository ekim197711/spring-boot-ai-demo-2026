### Testing Guidelines

- All unit tests must use **AssertJ** for assertions.
- Do **not** use JUnit's built-in assertions (`org.junit.jupiter.api.Assertions`).
- Prefer AssertJ's fluent API (e.g., `assertThat(actual).isEqualTo(expected)`) for better readability and more descriptive failure messages.
