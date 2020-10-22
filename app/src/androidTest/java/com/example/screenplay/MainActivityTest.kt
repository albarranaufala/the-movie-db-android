package com.example.screenplay

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.screenplay.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = DataDummy.dummyMovies
    private val dummyTvshows = DataDummy.dummyTvShows

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
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()))
        onView(withId(R.id.tvYear)).check(matches(withText(dummyMovies[0].releaseYear.toString())))
        onView(withId(R.id.tvSynopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSynopsis)).check(matches(withText(dummyMovies[0].synopsis)))
        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText(dummyMovies[0].genre)))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvProductionCompany)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.tvProductionCompany)).check(matches(withText(dummyMovies[0].director)))
    }

    @Test
    fun loadTvshows(){
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.rvTvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvshows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size-1))
    }

    @Test
    fun loadDetailTvshow(){
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.rvTvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.tvTitleTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleTvshow)).check(matches(withText(dummyTvshows[0].title)))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(withText("${dummyTvshows[0].releaseYear} ∙ ${dummyTvshows[0].nSeasons} Seasons")))
        onView(withId(R.id.tvSynopsisTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSynopsisTvshow)).check(matches(withText(dummyTvshows[0].synopsis)))
        onView(withId(R.id.tvGenreTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenreTvshow)).check(matches(withText(dummyTvshows[0].genre)))
        onView(withId(R.id.imgPosterTvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.tvProductionCompany)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.tvProductionCompany)).check(matches(withText(dummyTvshows[0].creator)))
    }
}