# SecureNotes - Android 

This Repository is a study case for Clean Architecture, Modularization, Koin, MVVM and ROOM to persist data.

## Architecture ##
Architecture layers are subdivided by feature. Following the name pattern {arquitecture_layer}/{feature_name}
as shown bellow.
Modules are renamed on settings.kts to facilitate dependency management. Ex: notes-ui, notes-data, notes-domain.
Injector module is an utility module to facilitate injecting features, concentrating all koin modules.

## Tech Stack ##
- Kotlin
- Koin
- Mockk
- MVVM (ViewModel & Livedata)
- Data access with Room
- Testing with Espresso & jUnit
- Robot Pattern (instrumented testing)
