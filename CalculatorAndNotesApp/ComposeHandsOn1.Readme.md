# Compose Hands-On : Part 1: Setting up an app

A Basic App with no other dependency requires the following changes in the code: 

1. make sure to support AGP v8.1.2 and gradle wrapper v8.0-bin.zip
2. `app/build.gradle` : in android block, we add:
```
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
```

3. `app/build.gradle` : in dependencies block, we add
```groovy
    dependencies {
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}
```

checkout the exact library and version in [libs](/gradle/libs.versions.toml) file  . with these changes, the app is ready to support jetpack compose framework

4. in code, any activity that extends `ComponentActivity` supports setting composable functions as content. This could be a starting point for your app, replacing appcompat activities with component activities one by one. it has a comoposable function setContent, that can take a composable component instead of an xml setContentRes or setContentView


