package com.wahidabd.library.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.BaseView
import com.wahidabd.library.presentation.fragment.BaseFragment

/**
 * The BaseActivity class is an abstract base class for activities that use ViewBinding.
 * It provides common functionality for setting up the activity's layout, managing fragments,
 * and configuring toolbars.
 *
 * @param VB The type of ViewBinding associated with this activity.
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), BaseView,
    BaseFragment.Callback {

    /**
     * The ViewBinding instance associated with this activity.
     */
    lateinit var binding: VB

    /**
     * Returns the ViewBinding instance for this activity.
     *
     * @return The ViewBinding instance.
     */
    abstract fun getViewBinding(): VB

    /**
     * Called when the activity is starting. Sets up the ViewBinding and calls onReady.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onReady()
    }

    /**
     * Replaces a fragment in the specified view with the given fragment.
     *
     * @param viewRes The resource ID of the view where the fragment will be placed.
     * @param fragment The fragment to place in the view.
     * @param addToBackStack Whether to add the transaction to the back stack.
     */
    fun setFragment(viewRes: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(viewRes, fragment)
            if (addToBackStack) addToBackStack(null)
            commit()
        }
    }

    /**
     * Finishes the activity.
     */
    override fun finishActivity() {
        this.finish()
    }

    override fun setupToolbar(title: String, isChild: Boolean) {
        val actionBar = this.supportActionBar
        if (actionBar != null) {
            actionBar.title = title
            actionBar.setDisplayHomeAsUpEnabled(isChild)
        }
    }

    /**
     * Sets up the toolbar with the back button visibility.
     *
     * @param toolbar The Toolbar instance to set up.
     * @param isChild Whether the toolbar should show a back button.
     */
    override fun setupToolbar(toolbar: Toolbar?, isChild: Boolean) {
        if (toolbar != null) setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(isChild)
    }

    /**
     * Sets up the toolbar with the given title and back button visibility.
     *
     * @param toolbar The Toolbar instance to set up.
     * @param title The title to set on the toolbar.
     * @param isChild Whether the toolbar should show a back button.
     */
    override fun setupToolbar(toolbar: Toolbar?, title: String, isChild: Boolean) {
        if (toolbar != null) setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(isChild)
    }

    /**
     * Called when the activity is ready. Initializes the activity's intent, UI, actions, processes, and observers.
     */

    private fun onReady() {
        initIntent()
        initUI()
        initAction()
        initProcess()
        initObservers()
    }

    /**
     * Shows a loading indicator. Override this method to provide a custom implementation.
     */
    override fun showLoading() {}

    /**
     * Hides the loading indicator. Override this method to provide a custom implementation.
     */
    override fun hideLoading() {}

    /**
     * Called when a fragment is attached to this activity.
     */
    override fun onFragmentAttached() {}

    /**
     * Called when a fragment is detached from this activity.
     *
     * @param tag The tag of the fragment that was detached.
     */
    override fun onFragmentDetached(tag: String) {}

    /**
     * Initializes the intent data. This method should be implemented by subclasses.
     */
    abstract fun initIntent()

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
