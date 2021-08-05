# Android State Test

This proof of concept app shows why static state (e.g. in Application or a Singleton) is bad.

# Demo
1. Clone and run App
2. Click on "Second"-Button to get to the Second Activity. You should now see "Hello World"
3. Open another app on the emulator and kill the app processes with `adb shell am kill de.maaxgr.statetest1`
4. Reopen the app on the emulator
5. You should now see "null" instead of "Hello World". That means the "globalState"-Variable was garbage collected