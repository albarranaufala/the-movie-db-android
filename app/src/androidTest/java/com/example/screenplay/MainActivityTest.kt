package com.example.screenplay

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.screenplay.utils.DataDummy
import com.example.screenplay.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = DataDummy.dummyMovies

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size-1))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSynopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvProductionCompany)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows(){
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.rvTvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size-1))
    }

    @Test
    fun loadDetailTvShow(){
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.rvTvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.tvTitleTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSynopsisTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenreTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPosterTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.tvProductionCompany)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
    }
}