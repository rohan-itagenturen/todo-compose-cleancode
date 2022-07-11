import org.gradle.api.artifacts.dsl.DependencyHandler

object AppConfig {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Versions {
    const val junitVersion = "4.13.2"
    const val extJunitVersion = "1.1.3"
    const val espressoVersion = "3.4.0"

    const val xCoreKtxVersion = "1.7.0"
    const val lifecycleRuntimeKtxVersion = "2.4.1"
    const val viewModelComposeVersion = "2.4.1"
    const val composeVersion = "1.1.1"
    const val composeCompilerVersion = "1.2.0-beta01"
    const val activityComposeVersion = "1.4.0"
    const val retrofitVersion = "2.9.0"
    const val gsonVersion = "2.9.0"
    const val gsonConverterVersion = "2.9.0"
    const val loggingVersion = "4.10.0"
    const val coroutineVersion = "1.6.3"
    const val navComposeVersion = "2.4.2"
    const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltAndroidVersion = "2.42"
    const val hiltComposeVersion = "1.0.0"
    const val timberVersion = "5.0.1"
    const val roomVersion = "2.4.2"
    const val dataStoreVersion = "1.0.0"
}

object ClassPath {
    const val hiltGradleClasspath = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroidVersion}"
}

object Dependencies {
    //Core
    const val xCoreKtx = "androidx.core:core-ktx:${Versions.xCoreKtxVersion}"
    private const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtxVersion}"
    private const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelComposeVersion}"

    //Compose
    private const val uiCompose = "androidx.compose.ui:ui:${Versions.composeVersion}"
    private const val materialCompose = "androidx.compose.material:material:${Versions.composeVersion}"
    private const val uiComposeTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    private const val uiComposeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    private const val activityCompose = "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
    private const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navComposeVersion}"
    private const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"

    //Test
    private const val junit = "junit:junit:${Versions.junitVersion}"
    private const val extJunit = "androidx.test.ext:junit:${Versions.extJunitVersion}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    private const val junit4Compose = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingVersion}"

    //Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}"

    //Gson
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}"

    //Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidVersion}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeVersion}"

    //Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"

    // logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    //Caching
    const val dataStorePreference = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"
    const val dataStoreProto = "androidx.datastore:datastore:${Versions.dataStoreVersion}"

    //Only meant for implementation("...")
    val appLibs = arrayListOf<String>().apply {
        add(xCoreKtx)
        add(lifeCycleRuntime)
        add(viewModelCompose)
        add(uiCompose)
        add(materialCompose)
        add(uiComposeToolingPreview)
        add(activityCompose)
        add(navigationCompose)
        add(hiltNavigationCompose)

        add(timber)
    }

    //Only meant for debugImplementation("...")
    val debugAppLibs = arrayListOf<String>().apply {
        add(uiComposeTooling)
    }

    //Only meant for androidTestImplementation("...")
    val androidTestLibs = arrayListOf<String>().apply {
        add(extJunit)
        add(espressoCore)
        add(junit4Compose)
    }

    //Only meant for testImplementation("...")
    val testLibs = arrayListOf<String>().apply {
        add(junit)
    }


}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.hilt(){
    implementation(listOf(Dependencies.hiltAndroid))
    kapt(listOf(Dependencies.hiltAndroidCompiler))
}

fun DependencyHandler.room(){
    implementation(listOf(Dependencies.roomRuntime, Dependencies.roomKtx))
    kapt(listOf(Dependencies.roomCompiler))
//    add("annotationProcessor", Dependencies.roomCompiler)
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.api(list: List<String>) {
    list.forEach { dependency ->
        add("api", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}