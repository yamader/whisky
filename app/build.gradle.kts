import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.dagger.hilt.android)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.ksp)
}

android {
  namespace = "net.dyama.whisky"
  compileSdk = 36

  defaultConfig {
    applicationId = "net.dyama.whisky"
    minSdk = 21
    targetSdk = 36
    versionCode = 1
    versionName = "0.1"
  }

  signingConfigs {
    create("release") {
      val keystorePropsFile = rootProject.file("keystore.properties")
      if (keystorePropsFile.exists()) {
        val keystoreProps = Properties().apply { load(keystorePropsFile.inputStream()) }
        storeFile = file(keystoreProps["storeFile"] as String)
        storePassword = keystoreProps["storePassword"] as String
        keyAlias = keystoreProps["keyAlias"] as String
        keyPassword = keystoreProps["keyPassword"] as String
      }
    }
  }
  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      signingConfig = signingConfigs.getByName("release")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlin.compilerOptions {
    jvmTarget = JvmTarget.JVM_11
    optIn.addAll(
      "androidx.compose.foundation.layout.ExperimentalLayoutApi",
      "androidx.compose.material3.ExperimentalMaterial3Api",
      "kotlin.time.ExperimentalTime",
    )
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(project(":lib"))
  implementation(project(":misskey"))

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.browser)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.datastore.preferences)
  implementation(libs.androidx.hilt.navigation.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.dagger.hilt.android)
  implementation(libs.kotlinx.datetime)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.ktor.client.cio)
  implementation(libs.ktor.client.content.negotiation)
  implementation(libs.ktor.client.core)
  implementation(libs.ktor.serialization.kotlinx.json)
  ksp(libs.dagger.hilt.android.compiler)
}
