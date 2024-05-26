package com.wahidabd.library.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding


/**
 * The BaseDialogFragment class is an abstract base class for dialog fragments that use ViewBinding.
 * It provides common functionality for setting up the fragment's layout and initializing UI components,
 * actions, and processes.
 *
 * @param VB The type of ViewBinding associated with this dialog fragment.
 */
abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {

    /**
     * The ViewBinding instance associated with this dialog fragment.
     */
    lateinit var binding: VB

    /**
     * The tag name for this dialog fragment.
     */
    abstract val tagName: String

    /**
     * Indicates whether the dialog is cancelable.
     */
    abstract val isDialogCancelable: Boolean

    /**
     * Returns the ViewBinding instance for this dialog fragment.
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
        isCancelable = isDialogCancelable

        dialog?.window?.apply {
            setSoftInputMode(16)  // Adjust input mode
            setGravity(17)        // Center the dialog
            setLayout(-2, -2)     // Set dialog size to wrap content
        }

        initUI()
        initAction()
        initProcess()
    }

    /**
     * Displays the dialog fragment.
     *
     * @param fragmentManager The FragmentManager this fragment will be added to.
     */
    fun showDialog(fragmentManager: FragmentManager) {
        val manager = fragmentManager.findFragmentByTag(tagName)
        if (manager != null) {
            show(fragmentManager, tagName)
        }
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
}