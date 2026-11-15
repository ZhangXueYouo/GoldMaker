# GoldMaker

GoldMaker is a simple personal-finance Android app (Java) built as a course project.

## Features

- **Login / Sign up / Reset password** – accounts are stored locally in SQLite (`UserManager.db`); passwords are saved as SHA-256 hashes.
- **Home** – menu of finance tools (Reminder, Income, Savings, Expenses, Budget, Transfer).
- **Reminders (Dashboard)** – add a reminder with a name, description and date; a notification is shown a few seconds later.
- **Transfer** – set a savings amount and transfer money out of it; shows remaining savings and total transferred.

> Income, Savings, Expenses and Budget are placeholders in the UI and are not implemented yet.

## Project structure

```
app/src/main/java/com/example/user_authentication/
├── App.java              # Application class, creates the notification channel
├── MainActivity.java     # Login screen (launcher)
├── Signup.java           # Registration
├── Forgot_Password.java  # Reset password
├── DatabaseHelper.java   # SQLite user table
├── Home.java             # Main menu
├── Dashboard.java        # Reminders list + notifications
├── CustomAdapter.java    # ListView adapter for reminders
├── NotificationActivity.java
└── Transfer.java         # Savings / transfer calculator
```

## Build & run

Requirements (the project uses an older toolchain):

- Android Studio with **JDK 8 or 11** for Gradle
- Android SDK 30 (compileSdk / targetSdk 30, minSdk 23)
- Gradle 6.5 / Android Gradle Plugin 4.1.3 (via the included wrapper)

Steps:

1. Open the project folder in Android Studio and let Gradle sync.
2. Run the `app` configuration on an emulator or device (Android 6.0+).

Or from the command line:

```bash
./gradlew assembleDebug
```

## Usage

1. Tap **Register** and create an account (email + password).
2. Log in with the same username and password.
3. From Home, open **Reminder** to add reminders or **Transfer** to manage savings. **Log Out** returns to the login screen.
