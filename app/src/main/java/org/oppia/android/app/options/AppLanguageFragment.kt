package org.oppia.android.app.options

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.oppia.android.app.drawer.NAVIGATION_PROFILE_ID_ARGUMENT_KEY
import org.oppia.android.app.fragment.FragmentComponentImpl
import org.oppia.android.app.fragment.InjectableFragment
import org.oppia.android.app.model.AppLanguageFragmentArguments
import org.oppia.android.app.model.AppLanguageFragmentStateBundle
import org.oppia.android.app.model.OppiaLanguage
import org.oppia.android.util.extensions.getProto
import org.oppia.android.util.extensions.putProto
import javax.inject.Inject

<<<<<<< HEAD
private const val APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_ARGUMENT_KEY =
  "AppLanguageFragment.app_language_preference_summary_value"
private const val SELECTED_LANGUAGE_SAVED_KEY = "AppLanguageFragment.selected_language"

=======
>>>>>>> a0deeea74289c94797dd9d3729ee7c157030ab67
/** The fragment to change the language of the app. */
class AppLanguageFragment : InjectableFragment(), AppLanguageRadioButtonListener {

  @Inject
  lateinit var appLanguageFragmentPresenter: AppLanguageFragmentPresenter
  private var profileId: Int? = -1

  companion object {
<<<<<<< HEAD
    fun newInstance(prefsSummaryValue: String): AppLanguageFragment {
      val fragment = AppLanguageFragment()
      val args = Bundle()
      args.putString(APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_ARGUMENT_KEY, prefsSummaryValue)
      fragment.arguments = args
      return fragment
=======
    private const val FRAGMENT_ARGUMENTS_KEY = "AppLanguageFragment.arguments"
    private const val FRAGMENT_SAVED_STATE_KEY = "AppLanguageFragment.saved_state"

    /** Returns a new [AppLanguageFragment] instance. */
    fun newInstance(oppiaLanguage: OppiaLanguage, profileId: Int): AppLanguageFragment {
      return AppLanguageFragment().apply {
        arguments = Bundle().apply {
          val args = AppLanguageFragmentArguments.newBuilder().apply {
            this.oppiaLanguage = oppiaLanguage
          }.build()
          putProto(FRAGMENT_ARGUMENTS_KEY, args)
          putInt(NAVIGATION_PROFILE_ID_ARGUMENT_KEY, profileId)
        }
      }
    }

    private fun Bundle.retrieveLanguageFromArguments(): OppiaLanguage {
      return getProto(
        FRAGMENT_ARGUMENTS_KEY, AppLanguageFragmentArguments.getDefaultInstance()
      ).oppiaLanguage
    }

    private fun Bundle.retrieveLanguageFromSavedState(): OppiaLanguage {
      return getProto(
        FRAGMENT_SAVED_STATE_KEY, AppLanguageFragmentStateBundle.getDefaultInstance()
      ).oppiaLanguage
>>>>>>> a0deeea74289c94797dd9d3729ee7c157030ab67
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (fragmentComponent as FragmentComponentImpl).inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
<<<<<<< HEAD
    val args =
      checkNotNull(arguments) { "Expected arguments to be passed to AppLanguageFragment" }
    val prefsSummaryValue = if (savedInstanceState == null) {
      args.getStringFromBundle(APP_LANGUAGE_PREFERENCE_SUMMARY_VALUE_ARGUMENT_KEY)
    } else {
      savedInstanceState.get(SELECTED_LANGUAGE_SAVED_KEY) as String
    }
    return appLanguageFragmentPresenter.handleOnCreateView(
      inflater,
      container,
      prefsSummaryValue!!
=======

    val oppiaLanguage =
      checkNotNull(
        savedInstanceState?.retrieveLanguageFromSavedState()
          ?: arguments?.retrieveLanguageFromArguments()
      ) { "Expected arguments to be passed to AppLanguageFragment" }
    profileId = arguments?.getInt(NAVIGATION_PROFILE_ID_ARGUMENT_KEY, -1)

    return appLanguageFragmentPresenter.handleOnCreateView(
      inflater,
      container,
      oppiaLanguage!!,
      profileId!!
>>>>>>> a0deeea74289c94797dd9d3729ee7c157030ab67
    )
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    val state = AppLanguageFragmentStateBundle.newBuilder().apply {
      oppiaLanguage = appLanguageFragmentPresenter.getLanguageSelected()
    }.build()
    outState.putProto(FRAGMENT_SAVED_STATE_KEY, state)
  }

<<<<<<< HEAD
  override fun onLanguageSelected(appLanguage: String) {
    appLanguageFragmentPresenter.onLanguageSelected(appLanguage)
=======
  override fun onLanguageSelected(selectedLanguage: OppiaLanguage) {
    appLanguageFragmentPresenter.onLanguageSelected(selectedLanguage)
>>>>>>> a0deeea74289c94797dd9d3729ee7c157030ab67
  }
}
