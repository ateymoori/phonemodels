
## PhoneModels, a simple Android app to explain the clean architecture
PhoneModels is a sample project that presents a modern approach to Android app development.

<img src="https://amirteymoori.ir/storage/screenshots/screen.jpg" alt="Android Architecture " width=600 />   

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

<br>  


#### I've separated it into 3 major layers.

<img src="https://amirteymoori.ir/storage/screenshots/Screen%20Shot%202021-12-20%20at%2001.27.36.png" alt="Android Architecture " width=450 />  

#### 1- Domain layer
* Entities as the core of the project (most preserved part), represent the objects.
* UseCases as the system logic. These classes will be injected in ViewModels.
* Repositories as the data layer rules and a bridge between use-cases and repositories in the data layer.
#### 2- Data layer
* APIs or databases connections and implementations.
* JSON response DTOs ( will be mapped to the domain layer's entities)
#### 3- Presentation
* System UI, implemented by JetPack compose(better performance and mobility, easier dark/light theme switch)
* Screens (pages), components (custom views), ViewModels and ViewContracts
* **State** - data class that holds the state content of the corresponding screen e.g. list of `FoodItem`, loading status, etc. The state is exposed as a Compose runtime `MutableState` object from that perfectly matches the use-case of receiving continuous updates with the initial value.

* **Event** - plain object that is sent through callbacks from the UI to the presentation layer. Events should reflect UI events caused by the user. Event updates are exposed as a `MutableSharedFlow` type which is similar to `StateFlow` and that behaves as in the absence of a subscriber, any posted event will be immediately dropped.

* **Effect** - plain object that signals one-time side-effect actions that should impact the UI e.g. triggering a navigation action, showing a Toast, SnackBar etc. Effects are exposed as `ChannelFlow` which behave as in each event is delivered to a single subscriber. An attempt to post an event without subscribers will suspend as soon as the channel buffer becomes full, waiting for a subscriber to appear.



## Tests
I've covered the most important layers as much as possible. But still needs to write more Unit/Ui tests.

<img src="https://amirteymoori.ir/storage/screenshots/Screen%20Shot%202021-12-20%20at%2001.40.15.png" alt="Android Architecture " width=520 />  

### CI/CD Pipeline
I'm using GitHub Actions as CI/CD. As I defined before, If I pushed the codes into any branch except master/release, All tests will be run, and if they were be successful, an APK will be built and uploaded into the Github actions artifact.



> .github/workflows/android-feature.yml


<img src="https://parkup.app/website/screens/8.png" alt="Android Architecture " width=530 /> <img src="https://amirteymoori.ir/storage/screenshots/ci-cd.png" alt="Android Architecture " width=500 />  


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