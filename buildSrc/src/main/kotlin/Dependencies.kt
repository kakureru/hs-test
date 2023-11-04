import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    const val core = "androidx.core:core-ktx:1.10.1"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
    const val activityCompose = "androidx.activity:activity-compose:1.7.0"
    const val navigationCompose = "androidx.navigation:navigation-compose:2.6.0"
    const val constraintlayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

    const val coil = "io.coil-kt:coil-compose:2.5.0"

    object Compose {
        const val bom = "androidx.compose:compose-bom:2023.10.01"
        const val runtime = "androidx.compose.runtime:runtime"
        const val foundation = "androidx.compose.foundation:foundation"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout"
        const val material3 = "androidx.compose.material3:material3"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object OkHttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.converterGson)
    implementation(Dependencies.OkHttp.okhttp)
    implementation(Dependencies.OkHttp.loggingInterceptor)
}

fun DependencyHandler.room() {
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    ksp(Dependencies.Room.compiler)
}

fun DependencyHandler.koin() {
    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.compose)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.constraintlayoutCompose)
    implementation(Dependencies.navigationCompose)

    implementation(platform(Dependencies.Compose.bom))
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiGraphics)
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.foundationLayout)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.uiToolingPreview)
    debugImplementation(Dependencies.Compose.uiTooling)
    debugImplementation(Dependencies.Compose.uiTestManifest)
}