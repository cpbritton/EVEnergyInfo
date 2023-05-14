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
    }
    useLibrary( "android.car")
    buildToolsVersion = "34.0.0 rc4"
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation(project(":simple-gauge-android"))
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.car.app:app-automotive:1.4.0-alpha01")
    implementation("androidx.car.app:app:1.2.0")

    implementation("com.diogobernardino:williamchart:3.11.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

