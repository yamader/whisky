plugins {
  alias(libs.plugins.android.kotlin.multiplatform.library)
  alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
  androidLibrary {
    namespace = "net.dyama.lib"
    compileSdk = 36
  }

  sourceSets.commonMain.dependencies {
    implementation(libs.kotlinx.serialization.json)
  }
}
