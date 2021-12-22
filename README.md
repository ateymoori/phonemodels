

## PhoneModels, a simple Android app to explain the clean architecture and Jetpack Compose
PhoneModels is a sample project that presents a modern approach to Android app development.

<img src="https://amirteymoori.ir/storage/screenshots/screen.jpg" alt="Android Architecture " width=650 />   

## Technologies

* UI
    * [Compose](https://developer.android.com/jetpack/compose) declarative UI framework
    * [Material design](https://material.io/design)

* Tech/Tools
    * [Kotlin](https://kotlinlang.org/) 100% coverage
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow) for async operations
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
    * [Jetpack](https://developer.android.com/jetpack)
        * [Compose](https://developer.android.com/jetpack/compose)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) for navigation between composables
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state
    * [Retrofit](https://square.github.io/retrofit/) for networking
    * [Coil](https://github.com/coil-kt/coil) for image loading

* Modern Architecture
    * Single activity architecture (with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)) that defines navigation graphs
    * MVVM
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))





#### I've separated it into three primary layers.

<img src="https://amirteymoori.ir/storage/screenshots/Screen%20Shot%202021-12-20%20at%2001.27.36.png" alt="Android Architecture " width=420 />  

#### 1- Domain layer
* Entities, as the core of the project (most preserved part), represent the objects.
* UseCases as the system logic. These classes will be injected into ViewModels.
* Repositories as the data layer rules and a bridge between use-cases and repositories in the data layer.
#### 2- Data layer
* APIs or databases connections and implementations.
* JSON response DTOs ( will be mapped to the domain layer's entities)
#### 3- Presentation
* System UI, implemented by JetPack compose(better performance and mobility, more effortless dark/light theme switch)
* Screens (pages), components (custom views), ViewModels and ViewContracts
* **State** - data class that holds the state content of the corresponding screen, e.g. list of `PhoneEnity`, loading status, etc. The state is exposed as a Compose runtime `MutableState` object that perfectly matches the use-case of receiving continuous updates with the initial value.

* **Event** - plain object sent through callbacks from the UI to the presentation layer. Events should reflect UI events caused by the user. Event updates are exposed as a `MutableSharedFlow` type similar to `Stateflow,` and that behaves as in the absence of a subscriber, any posted event will be immediately dropped.

* **Effect** - plain object that signals one-time side-effect actions that should impact the UI, e.g. triggering a navigation action, showing a Toast, SnackBar etc. Effects are exposed as `ChannelFlow,` which behave as if each event is delivered to a single subscriber. An attempt to post an event without subscribers will suspend when the channel buffer becomes full, waiting for a subscriber to appear.



## Tests
I've covered the essential layers as much as possible. But still needs to write more Unit/Ui tests.

<img src="https://amirteymoori.ir/storage/screenshots/Screen%20Shot%202021-12-20%20at%2001.40.15.png" alt="Android Architecture " width=520 />  



### CI/CD Pipeline by Github Actions

1. Run Unit tests on each git push, except master/release branches.

2. Export an APK and upload it in Github artifact.

3. Upload the APK to Firebase distribute system automatically.



**.github/workflows/android-feature.yml**



```
name: Project Features CI

on:
  push:
    branches:
      - '*'
      - '!master'
jobs:
  test:
    name: Run Unit Tests
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v1
      - name: set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: '11'
      - name: Run Unit tests
        run: bash ./gradlew test
  apk:
    name: Generate Debug APK and Release by Firebase
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v1
      - name: set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: '11'
      - name: Build debug APK
        run: bash ./gradlew assembleDebug
      - name: Upload APK
        uses: actions/upload-artifact@v1
        with:
          name: app
          path: app/build/outputs/apk/debug/app-debug.apk
      - name: Firebase App Distribution
        uses: wzieba/Firebase-Distribution-Github-Action@v1.3.2
        with:
          appId: ${{secrets.FIREBASE_APP_ID}}
          token: ${{secrets.FIREBASE_TOKEN}}
          groups: testers
          file: app/build/outputs/apk/debug/app-debug.apk
```

 <img src="https://amirteymoori.ir/storage/screenshots/ci-cd.png" alt="Android Architecture " width=530 />  


## APIs
I have implemented the APIs by PHP/MySQL.
The Postman document is available :
https://documenter.getpostman.com/view/6268446/UVRAJn64

<img src="https://amirteymoori.ir/storage/screenshots/Screen%20Shot%202021-12-20%20at%2001.53.11.png" alt="Android Architecture " width=480 />
----------------------------  


Feel free to fork or improve.

AmirHossein Teymoori

teymoori.net@gmail.com

My Resume :

https://amirteymoori.ir/AmirHosseinTeymooriAndroidDeveloperCV.pdf

