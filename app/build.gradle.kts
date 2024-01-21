plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("de.mannodermaus.android-junit5") version "1.10.0.0"
}

android {
    namespace = "com.ibrakor.superheroes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ibrakor.superheroes"
        minSdk = 26
        targetSdk = 34
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
    //ViewBinding
    buildFeatures{
        viewBinding=true
    }
}

dependencies {
    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //Skeleton
    implementation("com.faltenreich:skeletonlayout:5.0.0")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    //Gson
    implementation("com.google.code.gson:gson:2.10.1")
    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    //Fragment
    val fragmentVersion = "1.6.2"
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")
    //Navigation Component
    val navVersion = "2.7.6"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    //Hilt
    val hiltVersion = "2.48.1"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")


    val mockkVersion = "1.13.9"
    testImplementation("io.mockk:mockk-android:${mockkVersion}")
    testImplementation("io.mockk:mockk-agent:${mockkVersion}")
    //junit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    //refreshlayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")


    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
kapt {
    correctErrorTypes = true
}