plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.thanakornse.newlibrary"
    compileSdk = 33

    defaultConfig {
        minSdk = 29

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.thanakornse"
            artifactId = "newlibrary"
            version = "0.0.1"
            //artifact("$buildDir/outputs/aar/Mathlibrary-debug.aar")

            afterEvaluate {
                from(components["release"])
            }
        }

        repositories {
            maven {
                name = "newlibrary"
                url = uri("https://maven.pkg.github.com/ThanakornSe/TestingLibrary")
                //url = uri("$buildDir/outputs/repo")
                credentials {
                    username = "ThanakornSe"
                    password = "Olympusem5mk2"
                }
            }

        }
    }
}

//afterEvaluate {
//    android.libraryVariants.forEach { variant ->
//        publishing.publications.create<MavenPublication>(variant.name) {
//            groupId = "com.thanakornse"
//            artifactId = "newlibrary"
//            version = "0.0.1"
//            //artifact("$buildDir/outputs/aar/Mathlibrary-debug.aar")
//
//            from(components.findByName(variant.name))
//        }
//
//    }
//}

dependencies {
    val RETROFIT = "2.9.0"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    api("io.coil-kt:coil-compose:2.4.0")
    api("com.squareup.retrofit2:retrofit:$RETROFIT")
    api("com.squareup.retrofit2:converter-moshi:${RETROFIT}")
    api("com.squareup.retrofit2:converter-gson:${RETROFIT}")
    api("com.squareup.okhttp3:okhttp:4.11.0")
}