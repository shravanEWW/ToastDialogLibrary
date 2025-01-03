plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "com.example.toastdialoglibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing{
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.example.toastdialoglibrary"
                artifactId = "toastdialoglibrary" // Replace with your library name
                version = "1.0.1"

              /*  // Include the sources JAR
                artifact(androidSourcesJar.get())

//                artifact("$buildDir/outputs/aar/${project.name}-release.aar")
                  // Optional: Attach sources
                  artifact(tasks.create("androidSourcesJar", Jar::class) {
                      archiveClassifier.set("sources")
                      from(android.sourceSets["main"].java.srcDirs)
                  })*/
            }
        }
        repositories {
            maven {
                name = "jitpack"
                url = uri("https://jitpack.io")
            }
        }
    }
}