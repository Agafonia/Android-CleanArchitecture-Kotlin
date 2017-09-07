package com.fernandocejas.sample.navigation

import android.content.Context
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import com.fernandocejas.sample.features.login.Authenticator
import com.fernandocejas.sample.features.login.LoginActivity
import com.fernandocejas.sample.features.movies.MovieDetailsActivity
import com.fernandocejas.sample.features.movies.MoviesActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showMovies(context)
            false -> showLogin(context)
        }
    }

    fun showMovieDetails(activity: FragmentActivity, movieId: Int, sharedView: View) {
        val intent = MovieDetailsActivity.callingIntent(activity, movieId)
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, "moviePoster")
        activity.startActivity(intent, activityOptions.toBundle())
    }

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))
    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))
}


