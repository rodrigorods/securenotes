# SecureNotes - Android 
This Repository is a study case for Clean Architecture, Modularization, Biometric Auth, Koin, MVVM and ROOM to persist data.

## Architecture ##
Architecture layers are subdivided by feature. Following the name pattern {**arquitecture_layer**}/{**feature_name**}
as shown bellow.
![image](https://github.com/rodrigorods/securenotes/assets/1116255/88976833-f944-4ef2-a509-0c8f05a5d3df)

Modules are renamed on [settings.gradle.kts](https://github.com/rodrigorods/securenotes/blob/main/settings.gradle.kts) to facilitate dependency management. Ex: notes-ui, notes-data, notes-domain.

Injector module is an utility module to facilitate injecting features, concentrating all koin modules.

## Features ##
Biometric Authentication | List of Notes | List of Notes(Obfuscated) | Edit Notes
--- | --- | --- | --- | 
![image](https://github.com/rodrigorods/securenotes/assets/1116255/074cfc16-c25e-494a-a6f8-97ce7d180a48) | ![image](https://github.com/rodrigorods/securenotes/assets/1116255/fc851375-4294-4a2b-932a-9e1135f147ae) | ![image](https://github.com/rodrigorods/securenotes/assets/1116255/6b49d2b2-e1ab-4c95-a47d-534ef8fbe7a4) | ![image](https://github.com/rodrigorods/securenotes/assets/1116255/f6ecb595-b4a2-47f1-b630-5f427fe3ee85) 
At the start of the application the biometric pront will show, if authentication fails or gets dismissed, the list of notes will be displayed obfuscated|Default list of notes - clicking at any list entry will open that Note detail. This screen also contain a [FAB](https://m2.material.io/components/buttons-floating-action-button) Button that adds a new Note to the list, every new Note is empty by default.|Obfuscated list of notes will not display notes information due to user failing to authenticate biometric validation, and any interaction shall display biometric prompt.|Details of Note contains two [FAB](https://m2.material.io/components/buttons-floating-action-button) buttons - Edit & Delete.|

## How To Test ##
Make sure your emulator has secutiry validations turned ON - PIN & Fingerprint.
Every Fingerprint interaction can be mocked usign the following command on your terminal:
```./adb -e emu finger touch {fingerID}```
just make sure to replace {fingerID} with your own ID, if when testing the App a different ID is used the authorization will fail.

## Tech Stack ##
- Kotlin
- Koin
- Mockk
- MVVM (ViewModel & Livedata)
- Data access with Room
- Testing with Espresso & jUnit
- Robot Pattern (instrumented testing)
- Biometric validation
