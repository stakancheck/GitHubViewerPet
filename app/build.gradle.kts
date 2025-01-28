import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.compose.compiler)
    id("androidx.navigation.safeargs")
}

val secretsPropertiesFile = rootProject.file("secrets.properties")
val secretProperties = Properties()
secretProperties.load(FileInputStream(secretsPropertiesFile))

// Android configuration ---------------------------------------------------------------------------
android {
    namespace = "io.github.stakancheck.githubviewer"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = "io.github.stakancheck.githubviewer"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        // Add buildConfigField for secrets
        buildConfigField(
            "String",
            "GITHUB_API_TOKEN",
            "\"${secretProperties.getProperty("GITHUB_API_TOKEN")}\"",
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xwhen-guards")
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Spotless configuration --------------------------------------------------------------------------
spotless {
    kotlin {
        ktfmt("0.51").googleStyle().configure {
            it.setMaxWidth(80)
            it.setBlockIndent(4)
            it.setContinuationIndent(4)
            it.setRemoveUnusedImports(false)
            it.setManageTrailingCommas(false)
        }
        ktlint("1.0.0")
            .editorConfigOverride(
                mapOf(
                    "indent_size" to 4,
                    // intellij_idea is the default style we preset in Spotless, you can override it referring to https://pinterest.github.io/ktlint/latest/rules/code-styles.
                    "ktlint_code_style" to "intellij_idea",
                ),
            ).customRuleSets(
                listOf(
                    "io.nlopez.compose.rules:ktlint:0.4.16",
                ),
            )
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

tasks.register("spotlessApplyAll") {
    group = "spotless"
    description = "Apply spotless to all supported files"
    dependsOn("spotlessApply")
}

// Dependencies ------------------------------------------------------------------------------------
dependencies {

    // Android X dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom)) // Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // API usage
    implementation(libs.retrofit2)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.logging.interceptor)

    // KotlinX libraries
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)

    // DI
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    // Navigation (Jetpack Compose)
    implementation(libs.androidx.navigation.compose)

    // Image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    // Lottie animations
    implementation(libs.lottie.compose)

    // Testing dependencies
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockk)
    testImplementation(libs.mockwebserver)

    // Debugging
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
