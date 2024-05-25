package org.oppia.android.app.options

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.oppia.android.app.activity.ActivityComponentImpl
import org.oppia.android.app.activity.InjectableAutoLocalizedAppCompatActivity
import org.oppia.android.app.drawer.NAVIGATION_PROFILE_ID_ARGUMENT_KEY
import org.oppia.android.app.model.AppLanguageActivityParams
import org.oppia.android.app.model.AppLanguageActivityStateBundle
import org.oppia.android.app.model.OppiaLanguage
import org.oppia.android.app.model.ScreenName.APP_LANGUAGE_ACTIVITY
import org.oppia.android.util.extensions.getProto
import org.oppia.android.util.extensions.getProtoExtra
import org.oppia.android.util.extensions.putProto
import org.oppia.android.util.extensions.putProtoExtra
import org.oppia.android.util.logging.CurrentAppScreenNameIntentDecorator.decorateWithScreenName
import javax.inject.Inject

/** The activity to change the language of the app. */
class AppLanguageActivity : InjectableAutoLocalizedAppCompatActivity() {
  @Inject
  lateinit var appLanguageActivityPresenter: AppLanguageActivityPresenter
<<<<<<< HEAD
  private lateinit var prefSummaryValue: String
=======
  private var profileId: Int? = -1
>>>>>>> a0deeea74289c94797dd9d3729ee7c157030ab67

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (activityComponent as ActivityComponentImpl).inject(this)
<<<<<<< HEAD
    prefSummaryValue = if (savedInstanceState == null) {
      checkNotNull(intent.getStringExtra(APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_EXTRA_KEY)) {
        "Expected $APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_EXTRA_KEY to be in intent extras."
      }
    } else {
      savedInstanceState.get(SELECTED_LANGUAGE_EXTRA_KEY) as String
    }
    appLanguageActivityPresenter.handleOnCreate(prefSummaryValue)
  }

  companion object {
    const val APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_EXTRA_KEY =
      "AppLanguageActivity.app_language_preference_summary_value"
    internal const val SELECTED_LANGUAGE_EXTRA_KEY = "AppLanguageActivity.selected_language"

    /** Returns a new [Intent] to route to [AppLanguageActivity]. */
    fun createAppLanguageActivityIntent(context: Context, summaryValue: String?): Intent {
      return Intent(context, AppLanguageActivity::class.java).apply {
        putExtra(APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_EXTRA_KEY, summaryValue)
=======
    profileId = intent.getIntExtra(NAVIGATION_PROFILE_ID_ARGUMENT_KEY, -1)
    appLanguageActivityPresenter.handleOnCreate(
      savedInstanceState?.retrieveLanguageFromSavedState() ?: intent.retrieveLanguageFromParams(),
      profileId!!
    )
  }

  companion object {
    private const val ACTIVITY_PARAMS_KEY = "AppLanguageActivity.params"
    private const val ACTIVITY_SAVED_STATE_KEY = "AppLanguageActivity.saved_state"

    /** Returns a new [Intent] to route to [AppLanguageActivity]. */
    fun createAppLanguageActivityIntent(
      context: Context,
      oppiaLanguage: OppiaLanguage,
      profileId: Int?
    ): Intent {
      return Intent(context, AppLanguageActivity::class.java).apply {
        val arguments = AppLanguageActivityParams.newBuilder().apply {
          this.oppiaLanguage = oppiaLanguage
        }.build()
        putProtoExtra(ACTIVITY_PARAMS_KEY, arguments)
        putExtra(NAVIGATION_PROFILE_ID_ARGUMENT_KEY, profileId)
>>>>>>> a0deeea74289c94797dd9d3729ee7c157030ab67
        decorateWithScreenName(APP_LANGUAGE_ACTIVITY)
      }
    }

<<<<<<< HEAD
    fun getAppLanguagePreferenceSummaryValueExtraKey(): String {
      return APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_EXTRA_KEY
=======
    private fun Intent.retrieveLanguageFromParams(): OppiaLanguage {
      return getProtoExtra(
        ACTIVITY_PARAMS_KEY, AppLanguageActivityParams.getDefaultInstance()
      ).oppiaLanguage
    }

    private fun Bundle.retrieveLanguageFromSavedState(): OppiaLanguage {
      return getProto(
        ACTIVITY_SAVED_STATE_KEY, AppLanguageActivityStateBundle.getDefaultInstance()
      ).oppiaLanguage
>>>>>>> a0deeea74289c94797dd9d3729ee7c157030ab67
    }
  }

  override fun onBackPressed() = finish()

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    val state = AppLanguageActivityStateBundle.newBuilder().apply {
      oppiaLanguage = appLanguageActivityPresenter.getLanguageSelected()
    }.build()
    outState.putProto(ACTIVITY_SAVED_STATE_KEY, state)
  }
}
