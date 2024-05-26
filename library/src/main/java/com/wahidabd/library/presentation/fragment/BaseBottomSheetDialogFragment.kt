package com.wahidabd.library.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * The BaseBottomSheetDialogFragment class is an abstract base class for bottom sheet dialog fragments that use ViewBinding.
 * It provides common functionality for setting up the fragment's layout and initializing UI components,
 * actions, processes, and observers.
 *
 * @param VB The type of ViewBinding associated with this bottom sheet dialog fragment.
 */
abstract class BaseBottomSheetDialogFragment<VB : ViewBinding> : BottomSheetDialogFragment() {

    /**
     * The ViewBinding instance associated with this bottom sheet dialog fragment.
     */
    lateinit var binding: VB

    /**
     * The theme resource ID for the bottom sheet dialog fragment.
     */
    abstract val bottomSheetTheme: Int

    /**
     * The tag name for this bottom sheet dialog fragment.
     */
    abstract val tagName: String

    /**
     * Returns the ViewBinding instance for this bottom sheet dialog fragment.
     *
     * @param layoutInflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
     * @param attachRoot Whether the inflated hierarchy should be attached to the root parameter.
     * @return The ViewBinding instance.
     */
    abstract fun getViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachRoot: Boolean
    ): VB

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(layoutInflater, container, false)
        return binding.root
    }

    /**
     * Called immediately after onCreateView has returned, but before any saved state has been restored in to the view.
     *
     * @param view The View returned by onCreateView.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initProcess()
        initObservers()
        initAction()
    }

    /**
     * Returns the theme resource ID for the bottom sheet dialog fragment.
     *
     * @return The theme resource ID.
     */
    override fun getTheme(): Int {
        return this.bottomSheetTheme
    }

    /**
     * Displays the bottom sheet dialog fragment.
     *
     * @param fragmentManager The FragmentManager this fragment will be added to.
     */
    fun showBottomSheet(fragmentManager: FragmentManager) {
        this.show(fragmentManager, tagName)
    }

    /**
     * Initializes the UI components. This method should be implemented by subclasses.
     */
    abstract fun initUI()

    /**
     * Initializes the action listeners. This method should be implemented by subclasses.
     */
    abstract fun initAction()

    /**
     * Initializes the data processing logic. This method should be implemented by subclasses.
     */
    abstract fun initProcess()

    /**
     * Initializes the observers. This method should be implemented by subclasses.
     */
    abstract fun initObservers()
}