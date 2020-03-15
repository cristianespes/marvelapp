package com.cristianespes.marvelapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.cristianespes.marvelapp.R
import com.cristianespes.marvelapp.data.server.MarvelDb
import com.cristianespes.marvelapp.ui.main.MainActivity
import com.cristianespes.marvelapp.utils.fromJson
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.get

class UiTest : KoinTest {

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson(
                ApplicationProvider.getApplicationContext(),
                "popularheroes.json"
            )
        )

        val resource = OkHttp3IdlingResource.create("OkHttp", get<MarvelDb>().okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun clickAHeroNavigatesToDetail() {
        activityTestRule.launchActivity(null)

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewHeroes)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                ViewActions.click()
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.heroDetailToolbar))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Abomination (Emil Blonsky)"))))

    }
}