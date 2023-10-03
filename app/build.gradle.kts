@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.apollographql.apollo3)
}

android {
    namespace = "com.example.rickandmortywiki"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.rickandmortywiki"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    apollo {
        service("service") {
            packageName.set("com.example.rickandmortywiki")
        }
        generateKotlinModels.set(true)
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.apollo.graphql.runtime)
    implementation(libs.apollo.graphql.compose)
    implementation(libs.apollo.graphql.cache)
    implementation(libs.kodein.di)

    implementation(libs.kodein.android.core)
    implementation(libs.kodein.android.x)
    implementation(libs.kodein.android.compose)
    implementation(libs.kodein.android.viewmodel)

    implementation(libs.serialization)
    implementation(libs.navigation)
    implementation(libs.coil)
    implementation(libs.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.apollo.graphql.test)
    testImplementation(libs.arch.core.test)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}