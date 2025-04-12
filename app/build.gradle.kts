import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
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
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.navigation.compose)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
}
