# MarsLens

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

**A Mars photo explorer, built step by step.**

Project for the **Semester 7 Android Development module assessment** of my engineering degree at **IMT Atlantique**, following classes guided by [Mr **Lebranchu**](https://github.com/rlebranchu).

<br>

## Understanding the mission (personal notes if you got low knowledge on the topic like me :D)

NASA missions send robotic explorers to study Mars:

- **Curiosity** is a rover-a mobile robot that explores the surface.
- **InSight** is a lander-a spacecraft that stays at its landing site.
- A **sol** is one Martian day. Sol 0 marks the mission’s landing day.

Also in case you don't also know what is a **Martian day** (just like I didn't xD) :
- A **Martian day**, called a **sol**, is a full day–night cycle on Mars. It lasts about **24 hours and 40 minutes**, slightly longer than an Earth day. In this app, sols are counted from each mission’s landing day, **Sol 0**.

The app lets you choose an explorer and a sol to find its photographs.

<br>

## Planned screens

- Select a mission and Martian day (sol).
- Browse the matching NASA photos.
- View a photo and its associated information.

<br>

## Run locally

1. Open the repository in **Android Studio**.
2. Install **SDK 37**, configure your local SDK path, and sync Gradle. The daemon uses **JetBrains JDK 25**.
3. Run `app` on an emulator or device with **Android 10+ (API 29)**.

Select Curiosity or InSight and enter a Martian day, validated against the selected mission’s range. Photo browsing will follow.

<br>

## Course attribution

Build setup adapted from [MovieRecap](https://github.com/DanerSharifi-FR/movierecap-android) and its [original course project](https://github.com/timotheejouffrieau/MovieRecap). Course documents and local settings remain outside this repository.
