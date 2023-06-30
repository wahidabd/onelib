plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.gms.google-services")
}

val composeUiVersion = rootProject.extra.get("compose_ui_version") as String

android {
    namespace = "com.wahidabd.onelibrary"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.wahidabd.onelibrary"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", "\"24149183601d3608dcc2154306619711\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    packagingOptions {
        exclude("META-INF/rxjava.properties")
    }
}

dependencies {

    implementation(project(":library"))

//    implementation "com.github.wahidabd:one-library:1.1.1"

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.1.0"))
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx:24.6.1")

    // Koin
    implementation("io.insert-koin:koin-android:3.3.3")
    implementation("io.insert-koin:koin-androidx-workmanager:3.3.3")
    implementation("io.insert-koin:koin-androidx-navigation:3.3.3")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // room
    implementation("androidx.room:room-runtime:2.5.1")
    implementation("androidx.room:room-rxjava3:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")
    kapt("androidx.lifecycle:lifecycle-compiler:2.6.1")

    // rxjava
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

    // image picker
    implementation("com.github.esafirm:android-image-picker:3.0.0")

    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // rx permission
    implementation("com.github.tbruyelle:rxpermissions:0.12")

    // paging
    implementation("androidx.paging:paging-rxjava3:3.1.1")
    implementation("androidx.paging:paging-common-ktx:3.1.1")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    // image loader
    implementation("com.github.bumptech.glide:glide:4.15.0")
    kapt("com.github.bumptech.glide:compiler:4.15.0")

    // image picker
    implementation("com.github.esafirm:android-image-picker:2.4.5")

    // compress image and crop
    implementation("id.zelory:compressor:2.1.1")
    implementation("com.github.yalantis:ucrop:2.2.6")

    // lib phone number
    implementation("com.googlecode.libphonenumber:libphonenumber:8.13.7")

    // google play core
    implementation("com.google.android.play:app-update-ktx:2.0.1")

    // multi state view
    implementation("com.github.Kennyc1012:MultiStateView:2.2.0")
}