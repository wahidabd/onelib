
# onelib

## Documentation
**[docs.onelib.site](https://docs.onelib.site/)**

## Onelib: A Comprehensive Overview

**Onelib** is a third-party library designed to streamline and enhance Android development. It  
encapsulates various features to simplify and accelerate the development process by reducing  
boilerplate code and providing easy-to-use functionalities. Here's a detailed explanation of Onelib

# Setup Project

### Usage:&#x20;

#### Kotlin

Add dependency to <mark style="background-color:blue;">build.gradle.kts (app module)</mark>

<pre class="language-gradle"><code class="lang-gradle">implementation("com.github.wahidabd:onelib:<a data-footnote-ref href="#user-content-fn-1">latest-version</a>")  
</code></pre>  

Add to settings.gradle

```gradle  
dependencyResolutionManagement {  
 repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) 
 repositories { 
 google()
  mavenCentral()
   maven { url = uri("https://jitpack.io") } 
  }
}  
```  

#### Groovy

Add dependency to <mark style="background-color:blue;">build.gradle (app module)</mark>

<pre class="language-gradle"><code class="lang-gradle">implementation "com.github.wahidabd:onelib:<a data-footnote-ref href="#user-content-fn-2">latest-version</a>"  
</code></pre>  

Add to settings.gradle

```gradle  
dependencyResolutionManagement {  
 repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) 
 repositories {
  google()
   mavenCentral()
    maven { url "https://jitpack.io" } 
   }
}  
```  

# Data Flow

Data flow in Android development refers to the movement and management of data within an application, from user interaction to data presentation. Understanding data flow is crucial for building efficient, responsive, and maintainable applications. Let's break down the typical data flow in an Android application, especially within the context of using architectures like MVVM (Model-View-ViewModel) or Clean Architecture.

### **Key Components in Data Flow**

1. **User Interface (UI) Layer**: This includes Activities, Fragments, and Views where user interaction occurs.
2. **ViewModel**: Manages UI-related data and handles user input.
3. **Use Cases (Interactors)**: Encapsulate business logic and coordinate data retrieval/manipulation.
4. **Repositories**: Abstract data sources and manage data operations.
5. **Data Sources**: Includes network services (API calls) and local databases.

### **Typical Data Flow Steps**

1. **User Interaction**:
    * The user interacts with the UI by clicking a button, entering text, etc.
    * The UI component (Activity/Fragment) notifies the ViewModel about the interaction.
2. **ViewModel Processes Input**:
    * The ViewModel receives the input and may perform some validation or preparation of the data.
    * It then invokes a use case (or directly a repository method) to perform the required operation.
3. **Use Case Execution**:
    * The use case orchestrates the necessary business logic.
    * It interacts with the repository to fetch or update data.
4. **Repository Data Management**:
    * The repository manages data retrieval from various sources (network, database).
    * It abstracts the data sources, providing a unified interface for data operations.
5. **Data Source Interaction**:
    * The repository calls the appropriate data source to fetch or persist data.
    * This could involve making network requests, querying a database, or reading from local storage.
6. **Data Return Path**:
    * The data retrieved by the repository is passed back to the use case.
    * The use case processes the data (if needed) and returns it to the ViewModel.
7. **ViewModel Updates UI**:
    * The ViewModel updates LiveData or StateFlow with the new data.
    * The UI observes these data holders and updates itself accordingly.

### Example

Here is a illustratingthis flow using MVVM and Clean Architecture in onelib.

<figure><img src="../.gitbook/assets/Gambar 3.1.png" alt=""><figcaption></figcaption></figure>

## Remote Data

### Response

```kotlin
data class MovieDetailResultResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genres")
    val genres: List<GenreResultResponse>?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("title")
    val title: String?,
)

data class GenreResultResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
```

### Api Client

```kotlin
interface MovieApi {
    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int
    ): Response<MovieDetailResultResponse>
}
```

### Repository

```kotlin
interface MovieRepository {
    suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetailResultResponse>>
}
```

### Data Store

```kotlin
class MovieDataStore(
    private val api: MovieApi,
    private val errorParser: ErrorParser,
) : MovieRepository {

    override suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetailResultResponse>> = flow {
        OneCall.enqueue(
            id,
            errorParser::convertGenericError,
            api::getDetailMovie,
            onEmit = { emit(it) }
        )
    }.flowOn(Dispatchers.IO)

}
```

## Domain Data

### Model

```kotlin
data class MovieDetail(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
)
```

### Mapping

```kotlin
fun MovieDetailResultResponse.toDomain(): MovieDetail =
    MovieDetail(
        id = id,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,
        title = title,
        releaseDate = releaseDate
    )
```

### UseCase

```kotlin
interface MovieUseCase {
    suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>>
}
```

### Interactor

```kotlin
class MovieInteractor(private val repository: MovieRepository) : MovieUseCase {

    override suspend fun getDetailMovie(id: Int): Flow<Resource<MovieDetail>> {
        return repository.getDetailMovie(id).map { resource ->
            resource.oneMap { response -> response.toDomain() }
        }
    }

}
```

## Presentation

### ViewMode

```kotlin
// Live Data
class MovieViewModel(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _detail = MutableLiveData<Resource<MovieDetail>>()
    val detail: LiveData<Resource<MovieDetail>> get() = _detail

    fun detail(id: Int) {
        viewModelScope.launch {
            movieUseCase.getDetailMovie(id).collectLatest {
                _detail.value = it
            }
        }
    }
}

// State Flow
class MovieViewModel(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {

    private val _detail = MutableStateFlow<Resource<MovieDetail>>()
    val detail: StateFlow<Resource<MovieDetail>> get() = _detail

    fun detail(id: Int) {
        viewModelScope.launch {
            movieUseCase.getDetailMovie(id).collectLatest {
                _detail.value = it
            }
        }
    }
}
```

### Activity/Fragment

```kotlin
class MovieActivity : BaseActivity<ActivityMainBinding>() {

    private val movieViewModel: MovieViewModel by viewModel()

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {}

    override fun initProcess() {
        movieViewModel.detail(676)
    }

    override fun initObservers() {
        movieViewModel.detail.observerLiveData(this,
            onLoading = {
                // TODO(): Loading
            },
            onEmpty = {
                // TODO(): Empty
            },
            onFailure = { message ->
                // TODO(): Failure
            },
            onSuccess = {
                // TODO(): Success
            }
        )
    }

}
```

### Composable

```kotlin
@Composable
fun MovieScreen(
    viewModel: MovieViewModel = viewModel()
){
    LaunchedEffect(Unit) {
        viewModel.detail(666)
    }
    
    viewModel.result.collectStateFlow(
        onLoading = {
            // TODO(): Loading
        },
        onEmpty = {
            // TODO(): Empty
        },
        onFailure = { message ->
            // TODO(): Failure
        },
        onSuccess = {
            // TODO(): Success
        }
    )
}
```

Understanding and implementing proper data flow is key to building responsive and maintainable Android applications. The above example and diagram provide a foundational understanding of how data moves through an Android application using Clean Architecture principles.

[^1]: [https://github.com/wahidabd/onelib/releases](https://github.com/wahidabd/onelib/releases)

[^2]: [](https://github.com/wahidabd/onelib/releases)
