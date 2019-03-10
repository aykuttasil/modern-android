/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
  const val android_arch_navigation: String = "1.0.0-alpha11"

  const val work_runtime_ktx: String = "1.0.0"

  const val annotation: String = "1.0.2"

  const val appcompat: String = "1.0.2"

  const val cardview: String = "1.0.0"

  const val constraintlayout: String = "2.0.0-alpha2"

  const val core_ktx: String = "1.0.1"

  const val androidx_core_core: String = "1.1.0-alpha03"

  const val androidx_databinding: String = "3.3.2"

  const val gridlayout: String = "1.0.0"

  const val legacy_support_v4: String = "1.0.0"

  const val androidx_lifecycle: String = "2.0.0"

  const val multidex: String = "2.0.1"

  const val androidx_room: String = "2.1.0-alpha04"

  const val androidx_test_espresso: String = "3.1.1"

  const val androidx_test_ext: String = "1.1.0"

  const val androidx_test_core: String = "1.1.0"

  const val androidx_test_rules: String = "1.1.1"

  const val androidx_test_runner: String = "1.1.1"

  const val vectordrawable_animated: String = "1.0.0"

  const val aapt2: String = "3.3.2-5309881"

  const val com_android_tools_build_gradle: String = "3.3.2"

  const val lint_gradle: String = "26.3.2"

  const val crashlytics: String = "2.9.9"

  const val spotless_plugin_gradle: String = "3.18.0"

  const val com_facebook_stetho: String = "1.5.0"

  const val com_github_ajitsing: String = "1.0.4"

  const val ktlint: String = "0.30.0"

  const val material: String = "1.0.0"

  const val gson: String = "2.8.5"

  const val com_google_dagger: String = "2.21"

  const val firebase_database: String = "16.1.0"

  const val google_services: String = "4.2.0"

  const val com_google_truth_truth: String = "0.43"

  const val timber: String = "4.7.1"

  const val com_jakewharton: String = "10.1.0"

  const val mockito_kotlin: String = "2.1.0"

  const val com_readystatesoftware_chuck: String = "1.1.0"

  const val moshi_adapters: String = "1.8.0"

  const val logging_interceptor: String = "3.13.1"

  const val mockwebserver: String = "3.13.1"

  const val com_squareup_retrofit2: String = "2.5.0"

  const val rxpermissions: String = "0.9.4"

  const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2"

  const val io_fabric_tools_gradle: String = "1.27.1"

  const val io_reactivex_rxjava2_rxandroid: String = "2.1.1"

  const val io_reactivex_rxjava2_rxjava: String = "2.2.7"

  const val rxkotlin: String = "2.3.0"

  const val junit_junit: String = "4.12"

  const val anko: String = "0.10.8"

  const val org_jetbrains_kotlin: String = "1.3.21"

  const val org_jetbrains_kotlinx_kotlinx_coroutines_android: String = "1.0.0"
  // available: "1.1.1"

  const val org_jetbrains_kotlinx_kotlinx_coroutines_core: String = "1.0.0"
  // available: "1.1.1"

  const val org_mockito: String = "2.25.0"

  const val robolectric: String = "4.2"

  /**
   *
   *   To update Gradle, edit the wrapper file at path:
   *      ./gradle/wrapper/gradle-wrapper.properties
   */
  object Gradle {
    const val runningVersion: String = "5.0"

    const val currentVersion: String = "5.2.1"

    const val nightlyVersion: String = "5.4-20190310000057+0000"

    const val releaseCandidate: String = "5.3-rc-1"
  }
}
