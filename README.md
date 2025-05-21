# Expense Manager Java Application

A simple desktop application to track and manage expenses using Java Swing and MySQL.

## 📌 Features

* Add expense with amount, category, date, and description
* View all expenses in a scrollable UI
* Persistent data storage using MySQL

## 🛠️ Tech Stack

* **Java** (Core + Swing for GUI)
* **MySQL** (Database)
* **JDBC** (Java Database Connectivity)

## 📁 Project Structure

```
ExpenseManagerProject/
│
├── src/
│   └── ExpenseManagerApp.java  // Main Java file with GUI and logic
│
├── README.md
└── documentation.docx         // Project documentation
```

## ⚙️ Setup Instructions

1. **Clone or Download** this repository and extract the ZIP.
2. Navigate to the `src/` directory.
3. Open `ExpenseManagerApp.java` in your IDE.
4. Make sure MySQL is installed and running.
5. Create the database and table using the following SQL:

```sql
CREATE DATABASE expense_db;
USE expense_db;

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    category VARCHAR(100),
    date DATE,
    description TEXT
);
```

6. Update the database credentials in `ExpenseManagerApp.java`:

```java
static final String DB_URL = "jdbc:mysql://localhost:3306/expense_db";
static final String USER = "root";
static final String PASS = "your_password";
```

7. Compile and run the application.

## 🖼️ UI Preview

![image](https://github.com/user-attachments/assets/bed25167-a717-43ba-9cd7-29d1d1f809df)


## ✅ Future Improvements

* Edit and delete expense entries
* Filter/search by category/date
* Export reports to Excel (Apache POI)
* Import/export JSON or XML data

## 📄 License

This project is licensed under the MIT License.

✉️ Contact
For feedback or suggestions, please contact: ektachandak.edu@gmail.com
