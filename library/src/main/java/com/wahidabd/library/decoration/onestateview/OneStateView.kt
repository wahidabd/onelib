package com.wahidabd.library.decoration.onestateview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.wahidabd.library.R


/**
 * Created by wahid on 5/23/2024.
 * Github github.com/wahidabd.
 */


class OneStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val VIEW_STATE_CONTENT = 0
        private const val VIEW_STATE_LOADING = 1
        private const val VIEW_STATE_ERROR = 2
        private const val VIEW_STATE_EMPTY = 3
    }

    private var contentView: View? = null
    private var loadingView: View? = null
    private var errorView: View? = null
    private var emptyView: View? = null

    var listener: OneStateListener? = null
    var animateChanges: Boolean = false

    var viewState: OneViewState = OneViewState.CONTENT
        set(value) {
            val previousField = field

            if (value != previousField) {
                field = value
                setView(previousField)
                listener?.onStateChanged(value)
            }
        }

    init {
        setAttrs(attrs)
    }

    private fun setAttrs(attrs: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.OneStateView, 0, 0)

        val loadingId = attributes.getResourceId(R.styleable.OneStateView_osv_loading, -1)
        if (loadingId > -1) {
            val inflatedLoading = inflater.inflate(loadingId, this, false)
            loadingView = inflatedLoading
            addView(inflatedLoading, inflatedLoading.layoutParams)
        }

        val emptyId = attributes.getResourceId(R.styleable.OneStateView_osv_empty, -1)
        if (emptyId > -1) {
            val inflatedEmpty = inflater.inflate(emptyId, this, false)
            emptyView = inflatedEmpty
            addView(inflatedEmpty, inflatedEmpty.layoutParams)
        }

        val errorId = attributes.getResourceId(R.styleable.OneStateView_osv_error, -1)
        if (errorId > -1) {
            val inflatedError = inflater.inflate(errorId, this, false)
            errorView = inflatedError
            addView(inflatedError, inflatedError.layoutParams)
        }

        viewState =
            when (attributes.getInt(R.styleable.OneStateView_osv_default, VIEW_STATE_CONTENT)) {
                VIEW_STATE_ERROR -> OneViewState.ERROR
                VIEW_STATE_EMPTY -> OneViewState.EMPTY
                VIEW_STATE_LOADING -> OneViewState.LOADING
                else -> OneViewState.CONTENT
            }

        animateChanges = attributes.getBoolean(R.styleable.OneStateView_osv_animateChanges, false)
        attributes.recycle()
    }

    fun getView(state: OneViewState): View? {
        return when (state) {
            OneViewState.CONTENT -> contentView
            OneViewState.LOADING -> loadingView
            OneViewState.ERROR -> errorView
            OneViewState.EMPTY -> emptyView
        }
    }

    fun setViewForState(view: View, state: OneViewState, switchToState: Boolean = false) {
        when (state) {
            OneViewState.LOADING -> {
                if (loadingView != null) removeView(loadingView)
                loadingView = view
                addView(view)
            }

            OneViewState.EMPTY -> {
                if (emptyView != null) removeView(emptyView)
                emptyView = view
                addView(view)
            }

            OneViewState.ERROR -> {
                if (errorView != null) removeView(errorView)
                errorView = view
                addView(view)
            }

            OneViewState.CONTENT -> {
                if (contentView != null) removeView(contentView)
                contentView = view
                addView(view)
            }
        }

        if (switchToState) viewState = state
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (contentView == null) throw IllegalArgumentException("Content view is not defined")

        when (viewState) {
            OneViewState.CONTENT -> setView(OneViewState.CONTENT)
            else -> contentView?.visibility = View.GONE
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        return when (val superState = super.onSaveInstanceState()) {
            null -> superState
            else -> SavedState(superState, viewState)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            viewState = state.state
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    /* All of the addView methods have been overridden so that it can obtain the content view via XML
    It is NOT recommended to add views into MultiStateView via the addView methods, but rather use
    any of the setViewForState methods to set views for their given ViewState accordingly */
    override fun addView(child: View) {
        if (isValidContentView(child)) contentView = child
        super.addView(child)
    }

    override fun addView(child: View, index: Int) {
        if (isValidContentView(child)) contentView = child
        super.addView(child, index)
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        if (isValidContentView(child)) contentView = child
        super.addView(child, index, params)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        if (isValidContentView(child)) contentView = child
        super.addView(child, params)
    }

    override fun addView(child: View, width: Int, height: Int) {
        if (isValidContentView(child)) contentView = child
        super.addView(child, width, height)
    }

    override fun addViewInLayout(child: View, index: Int, params: ViewGroup.LayoutParams): Boolean {
        if (isValidContentView(child)) contentView = child
        return super.addViewInLayout(child, index, params)
    }

    override fun addViewInLayout(
        child: View,
        index: Int,
        params: ViewGroup.LayoutParams,
        preventRequestLayout: Boolean
    ): Boolean {
        if (isValidContentView(child)) contentView = child
        return super.addViewInLayout(child, index, params, preventRequestLayout)
    }


    /**
     * Checks if the given [View] is valid for the Content View
     *
     * @param view The [View] to check
     * @return
     */
    private fun isValidContentView(view: View): Boolean {
        return if (contentView != null && contentView !== view) {
            false
        } else view != loadingView && view != errorView && view != emptyView
    }

    private fun setView(previousState: OneViewState) {
        when (viewState) {
            OneViewState.LOADING -> {
                requireNotNull(loadingView).apply {
                    contentView?.visibility = View.GONE
                    errorView?.visibility = View.GONE
                    emptyView?.visibility = View.GONE

                    if (animateChanges) {
                        animateLayoutChange(getView(previousState))
                    } else {
                        visibility = View.VISIBLE
                    }
                }
            }

            OneViewState.EMPTY -> {
                requireNotNull(emptyView).apply {
                    contentView?.visibility = View.GONE
                    errorView?.visibility = View.GONE
                    loadingView?.visibility = View.GONE

                    if (animateChanges) {
                        animateLayoutChange(getView(previousState))
                    } else {
                        visibility = View.VISIBLE
                    }
                }
            }

            OneViewState.ERROR -> {
                requireNotNull(errorView).apply {
                    contentView?.visibility = View.GONE
                    loadingView?.visibility = View.GONE
                    emptyView?.visibility = View.GONE

                    if (animateChanges) {
                        animateLayoutChange(getView(previousState))
                    } else {
                        visibility = View.VISIBLE
                    }
                }
            }

            OneViewState.CONTENT -> {
                requireNotNull(contentView).apply {
                    loadingView?.visibility = View.GONE
                    errorView?.visibility = View.GONE
                    emptyView?.visibility = View.GONE

                    if (animateChanges) {
                        animateLayoutChange(getView(previousState))
                    } else {
                        visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun animateLayoutChange(previousView: View?) {
        if (previousView == null) {
            requireNotNull(getView(viewState)).visibility = View.VISIBLE
            return
        }

        ObjectAnimator.ofFloat(previousView, "alpha", 1.0f, 0.0f).apply {
            duration = 250L
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    previousView.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator) {
                    previousView.visibility = View.GONE
                    val currentView = requireNotNull(getView(viewState))
                    currentView.visibility = View.VISIBLE
                    ObjectAnimator.ofFloat(currentView, "alpha", 0.0f, 1.0f).setDuration(250L)
                        .start()
                }
            })
        }.start()
    }

    enum class OneViewState {
        CONTENT,
        LOADING,
        ERROR,
        EMPTY,
    }

    private class SavedState : BaseSavedState {
        val state: OneViewState

        constructor(superState: Parcelable, state: OneViewState) : super(superState) {
            this.state = state
        }

        constructor(parcel: Parcel) : super(parcel) {
            state = parcel.readSerializable() as OneViewState
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeSerializable(state)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}