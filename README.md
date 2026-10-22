# Smart Pantry Manager — MAD700

**Module:** Mobile App Development 700  
**Student number:** 402110592  
**Language:** Java (Android Studio)  
**Repository:** https://github.com/njabulomaluleke21-lang/MAD700-SmartPantryManager

## Overview

Smart Pantry Manager is an Android app that helps reduce household food waste. Users keep a record of ingredients they already have and can view recipe suggestions based strictly on the ingredients and quantities available.

## Core functionality

- Add, view, edit and delete pantry ingredients, including name, quantity, unit and optional expiry date.
- Display pantry ingredients in a dynamic list.
- Display matching recipes and view their ingredient requirements and preparation steps.
- Include a Settings screen.
- Store pantry records locally using SQLite.
- Validate ingredient input before saving.

### Strict recipe-matching rule

A recipe must only appear in **Suggested Recipes** when every required ingredient exists in the pantry in a sufficient, compatible quantity. Missing or insufficient ingredients exclude a recipe; partial matches do not count.

## Database choice

The project uses **SQLite** with Android's `SQLiteOpenHelper`. SQLite runs on-device, works offline and supports persistent CRUD storage without a hosted server. The recipe collection is seeded on first database creation.

## Open and run in Android Studio

1. Download/clone this repository.
2. In Android Studio choose **Open** and select the project root containing `settings.gradle`.
3. Let Gradle sync, and ensure the configured Gradle distribution and JDK are compatible.
4. Select an Android emulator or connected Android device.
5. Choose the `app` run configuration and press **Run ▶**.
6. Test adding ingredients, editing and deleting them, navigating through recipes and settings, and restarting the app to check persistence.

**Environment tested locally by the student:** Android emulator Medium Phone API 37; Gradle 8.13 with Java 21. Build and runtime verification in a fresh clone still need to be performed.

## Suggested manual test cases

| Case | Expected behaviour |
| --- | --- |
| Add Tomato, quantity 5 pcs | Pantry list shows saved Tomato record |
| Edit Tomato quantity | Pantry list reflects updated value |
| Delete ingredient with confirmation | Record is removed |
| Leave ingredient name blank | Validation prevents invalid record |
| Remove one required recipe ingredient | Recipe is excluded from suggestions |
| Restore all required ingredients in sufficient quantity | Qualifying recipe reappears |
| Close and reopen app | Pantry data remains available |

## Video demonstration

The 5–7 minute narrated demonstration should show GitHub's real commit history, a live CRUD cycle, a strict-matching demonstration, and explanations of three Android concepts plus the database choice.

**Video:** To be uploaded after recording. If including the video would make the Moodle ZIP exceed 50 MB, follow the lecturer's revised instructions to upload the video to this assignment repository instead.

## Submission notes

- Submit the Android Studio source and completed report in the Moodle ZIP.
- Exclude local generated folders such as `build/`, `.gradle/` and IDE caches from version control.
- Capture screenshots from the running app, not mockups.
- The commit history should reflect real work; avoid artificial commits solely to reach a number.
- See the assignment report for system diagrams, explanations, test evidence and Harvard references.
