plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.brittonvehicles.evenergyinfo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.brittonvehicles.evenergyinfo"
        minSdk = 32
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
    useLibrary( "android.car")
    buildToolsVersion = "34.0.0 rc4"
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation(project(":simple-gauge-android"))
    implementation("com.google.android.material:material:1.9.0")

    implementation("androidx.car.app:app-automotive:1.2.0")
    implementation("androidx.car.app:app:1.2.0")

    implementation("com.diogobernardino:williamchart:3.11.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Kotlin
    implementation("androidx.activity:activity-ktx:1.7.1")
    implementation ("androidx.fragment:fragment-ktx:1.5.7")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

}

