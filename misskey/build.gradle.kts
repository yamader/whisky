plugins {
  alias(libs.plugins.android.kotlin.multiplatform.library)
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.kotlin.serialization)
}

kotlin {
  androidLibrary {
    namespace = "net.dyama.misskey"
    compileSdk = 36
  }

  compilerOptions {
    optIn.addAll(
      "kotlin.time.ExperimentalTime",
    )
  }

  sourceSets.commonMain.dependencies {
    implementation(project(":lib"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.websockets)
    implementation(libs.ktor.serialization.kotlinx.json)
  }
}
