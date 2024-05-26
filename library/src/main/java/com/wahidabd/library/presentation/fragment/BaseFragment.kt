package com.wahidabd.library.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


/**
 * The BaseFragment class is an abstract base class for fragments that use ViewBinding.
 * It provides common functionality for setting up the fragment's layout and initializing UI components,
 * actions, processes, and observers.
 *
 * @param VB The type of ViewBinding associated with this fragment.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    /**
     * The ViewBinding instance associated with this fragment.
     */
    lateinit var binding: VB

    /**
     * Returns the ViewBinding instance for this fragment.
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
     * Called when the fragment is starting. Sets whether this fragment has options menu.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setHasOptionsMenu(false)
    }

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
        initAction()
        initProcess()
        initObservers()
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

    /**
     * A callback interface to be implemented by activities that contain this fragment
     * to be notified of fragment attachment and detachment events.
     */
    interface Callback {
        /**
         * Called when the fragment is attached to an activity.
         */
        fun onFragmentAttached()

        /**
         * Called when the fragment is detached from an activity.
         *
         * @param tag The tag of the fragment that was detached.
         */
        fun onFragmentDetached(tag: String)
    }
}