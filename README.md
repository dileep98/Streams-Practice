# Streams Practice 🚀

A comprehensive collection of Java Stream API examples ranging from beginner to advanced concepts.

## 📌 Project Structure
- **Beginner**: Basic filtering, mapping, and collecting.
- **Intermediate**: `flatMap`, `groupingBy`, and custom collectors.
- **Advanced**: Parallel streams, `Stream.iterate` (Fibonacci), and large dataset processing.
- **Utilities**: Custom `StackWalker` logger for method-level tracing.

## 🛠 Features Implemented
- [x] **Basic Implementations**: Custom examples for understanding concepts like boxing/unboxing, map/flatMap, method references, and parallel streams.
- [x] **Palindrome Finder**: Logic to find the shortest palindrome in a list.
- [x] **Frequency Analysis**: Finding the most frequent word in a text file.
- [x] **Math Operations**: Fibonacci sequence generation and large number summation.
- [x] **Data Processing**: Grouping employees by department and calculating average salaries from CSV.
- [x] **And Lot more....** explore and findout

## 📂 Resources
- `data.csv`: Sample employee data for testing grouping logic.
- `sample.txt`: Large text file for word count and character analysis.

## 🚀 How to Run
1. Clone the repo:
   ```bash
   git clone https://github.com/dileep98/Streams-Practice.git
   
2. This project is configured with the `exec-maven-plugin`. You can run the `Main` class directly from the terminal:

   ```bash
   mvn exec:java