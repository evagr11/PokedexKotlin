plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.dokka") version "2.0.0"
}

android {
    namespace = "com.example.pokedexkotlin"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.pokedexkotlin"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.volley)
    implementation("io.coil-kt:coil-compose:2.4.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

// Documentación del código (KDoc) en HTML con Dokka: .\gradlew :app:dokkaGenerate
// Se genera en docs/kdoc/ y se publica con GitHub Pages.
dokka {
    moduleName.set("Pokédex Kotlin")
    dokkaPublications.html {
        outputDirectory.set(rootProject.layout.projectDirectory.dir("docs/kdoc"))
        // Oculta lo heredado de Android (ComponentActivity...) para que se vea solo el código propio
        suppressInheritedMembers.set(true)
    }
    dokkaSourceSets.configureEach {
        documentedVisibilities.set(
            setOf(
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Public,
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Protected,
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Internal,
                org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier.Private
            )
        )
    }
}
