plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("io.fabric")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(28)


    val fabricPropertiesFile = rootProject.file("app/fabric.properties")
    var fabricProperties: PropertiesFile? = null
    if (fabricPropertiesFile.exists()) {
        fabricProperties = PropertiesFile(fabricPropertiesFile, "fabric.properties")
    }

    defaultConfig {
        applicationId = "aykuttasil.com.modernapp"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ext["betaDistributionEmails"] = "aykuttasil@gmail.com"
        ext["betaDistributionNotifications"] = true
        resValue("string", "io.fabric.ApiKey", (fabricProperties?.getValue("apiKey") ?: "xyz") as String)
    }

    val keystorePropertiesFile = rootProject.file("keystore.properties")
    var keystoreProperties: PropertiesFile? = null
    if (keystorePropertiesFile.exists()) {
        keystoreProperties = PropertiesFile(keystorePropertiesFile, "keystore.properties")
    }
    signingConfigs {
        keystoreProperties?.let { propertiesFile ->
            getByName("debug") {
                storeFile = file(propertiesFile.getProperty("signingStoreFileDebug"))
                storePassword = propertiesFile.getProperty("signingStorePasswordDebug")
                keyAlias = propertiesFile.getProperty("signingKeyAliasDebug")
                keyPassword = propertiesFile.getProperty("signingKeyAliasPasswordDebug")
            }

            create("release") {
                storeFile = file(propertiesFile.getProperty("signingStoreFile"))
                storePassword = propertiesFile.getProperty("signingStorePassword")
                keyAlias = propertiesFile.getProperty("signingKeyAlias")
                keyPassword = propertiesFile.getProperty("signingKeyAliasPassword")
            }
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    flavorDimensions("default")
    productFlavors {
        create("prod") {
            setDimension("default")
            resValue("string", "app_name", "MAS")
        }

        create("dev") {
            setDimension("default")
            resValue("string", "app_name", "MAS Dev")
        }

        create("mock") {
            setDimension("default")
            resValue("string", "app_name", "MAS Mock")
        }
    }

    sourceSets {
        getByName("main").java.srcDir("src/main/kotlin")
        getByName("test").java.srcDir("src/test/kotlin")

        val commonTest = "src/commonTest/java"
        getByName("androidTest").java.srcDirs(commonTest)
        getByName("test").java.srcDirs(commonTest)
    }

    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    packagingOptions {
        exclude("META-INF/services/javax.annotation.processing.Processor")
        exclude("LICENSE.txt")
        exclude("META-INF/license/LICENSE.base64.txt")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/rxjava.properties")
        exclude("META-INF/MANIFEST.MF")
        exclude("META-INF/main.kotlin_module")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    //    configurations.all {
    //        resolutionStrategy {
    //            force 'com.google.code.findbugs:jsr305:3.0.2'
    //            force 'net.bytebuddy:byte-buddy:1.8.22'
    //            force 'net.bytebuddy:byte-buddy-agent:1.8.22'
    //        }
    //    }

    configurations.all {
        resolutionStrategy {
            force(Libs.rxjava)
        }
    }

    //    applicationVariants.all { variant ->
    //        variant.outputs.all { output ->
    //            outputFileName = output.outputFile.name
    //                .replace(".apk", "-${variant.versionName}-${variant.versionCode}.apk")
    //        }
    //    }

    /*
    applicationVariants.all { variant ->
        variant.outputs.all { output ->
            output.outputFile.name.replace(".apk", "-${variant.versionName}-${variant.versionCode}.apk")
            true
        }
    }
    */
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":core"))
    implementation(project(":network"))

    testImplementation(Libs.junit_junit)
    testImplementation(Libs.robolectric)
    testImplementation(Libs.mockwebserver)
    testImplementation(Libs.androidx_test_core)
    testImplementation(Libs.androidx_test_runner)
    testImplementation(Libs.androidx_test_rules)
    testImplementation(Libs.androidx_test_ext_junit)
    testImplementation(Libs.espresso_core, {
        this.exclude(group = "com.google.code.findbugs")
    })
    // testImplementation 'com.google.code.findbugs:jsr305:3.0.2'
    testImplementation(Libs.mockito_inline)
    testImplementation(Libs.mockito_kotlin)

    androidTestImplementation(Libs.mockito_android)
    androidTestImplementation(Libs.androidx_test_core)
    androidTestImplementation(Libs.androidx_test_runner)
    androidTestImplementation(Libs.androidx_test_rules)
    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.androidx_test_ext_truth)
    androidTestImplementation(Libs.com_google_truth_truth)
    androidTestImplementation(Libs.espresso_core, {
        this.exclude(group = "com.google.code.findbugs")
    })
    androidTestImplementation(Libs.espresso_contrib)


    releaseImplementation(Libs.sherlock_no_op)
    debugImplementation(Libs.sherlock) {
        isTransitive = true
    }
    implementation(Libs.crashlytics) {
        isTransitive = true
    }

    debugImplementation(Libs.stetho)

    implementation(Libs.legacy_support_v4)
    implementation(Libs.appcompat)
    implementation(Libs.androidx_core_core)
    implementation(Libs.core_ktx)
    implementation(Libs.vectordrawable_animated)
    implementation(Libs.cardview)
    implementation(Libs.constraintlayout)
    implementation(Libs.annotation)
    implementation(Libs.gridlayout)
    implementation(Libs.multidex)
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_reactivestreams)
    implementation(Libs.room_runtime)
    kapt(Libs.room_compiler)
    implementation(Libs.room_rxjava2)

    implementation(Libs.material)
    implementation(Libs.firebase_database)
    implementation(Libs.navigation_fragment)
    implementation(Libs.navigation_ui)
    implementation(Libs.work_runtime_ktx)
    implementation(Libs.rxjava)
    implementation(Libs.rxkotlin)
    implementation(Libs.rxandroid)

    //implementation(Libs.kotlin_stdlib_jdk7)
    //implementation(Libs.kotlin_reflect)
    implementation(Libs.anko)
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)

    implementation(Libs.rxpermissions)
    implementation(Libs.butterknife)
    kapt(Libs.butterknife_compiler)
    implementation(Libs.gson)
    implementation(Libs.timber)

    kapt(Libs.dagger_compiler)
    implementation(Libs.dagger)
    kapt(Libs.dagger_android_processor)
    implementation(Libs.dagger_android_support)
}
// apply(plugin = "com.google.gms.google-services")
apply(from = "../tools/spotless.gradle")
