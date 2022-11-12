object AndroidX {
    const val CORE = "androidx.core:core-ktx:1.7.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.1"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val MATERIAL = "com.google.android.material:material:1.6.0"

    // Activity, Fragment 확장 함수 라이브러리
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.4.0"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.4.1"

    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.5.3"
}

object AndroidLifeCycle {
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
    const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"
}

object AndroidTest {
    const val EXT_JUNIT = "androidx.test.ext:junit:1.1.3"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
}

object DaggerHilt {
    private const val version = "2.44.1"

    const val PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$version"

    const val SDK = "com.google.dagger:hilt-android:$version"
    const val COMPILER = "com.google.dagger:hilt-android-compiler:$version"
}

object Glide {
    private const val version = "4.13.0"

    const val SDK = "com.github.bumptech.glide:glide:$version"
    const val COMPILER = "com.github.bumptech.glide:compiler:$version"
}

object Room {
    private const val version = "2.4.2"

    const val SDK = "androidx.room:room-ktx:$version"
    const val COMPILER = "androidx.room:room-compiler:$version"
}

object CalendarView {
    const val KIZITONWOSE_CALENDAR_VIEW = "com.github.kizitonwose:CalendarView:1.0.4"
}