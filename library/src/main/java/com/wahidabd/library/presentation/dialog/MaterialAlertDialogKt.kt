@file:OptIn(ExperimentalContracts::class)

package com.wahidabd.library.presentation.dialog

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * Created by Wahid on 10/10/2023.
 * Github github.com/wahidabd.
 */


/**
 * Instantiates a [MaterialAlertDialogBuilder] for the [Context], applies the [dialogConfig] lambda to
 * it, then creates an [AlertDialog] from the builder, and returns it, so you can call
 * [AlertDialog.show] on the created dialog.
 */
fun Context.materialAlertDialog(dialogConfig: MaterialAlertDialogBuilder.() -> Unit): AlertDialog {
    contract { callsInPlace(dialogConfig, InvocationKind.EXACTLY_ONCE) }
    return MaterialAlertDialogBuilder(this)
        .apply(dialogConfig)
        .create()
}

/**
 * Instantiates a [MaterialAlertDialogBuilder] for the [Context], sets the passed [title], [message] and
 * [iconResource], applies the [dialogConfig] lambda to it, then creates an [AlertDialog] from
 * the builder, and returns it, so you can call [AlertDialog.show] on the created dialog.
 */
fun Context.materialAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    @DrawableRes iconResource: Int = 0,
    isCancelable: Boolean = true,
    dialogConfig: MaterialAlertDialogBuilder.() -> Unit = {}
): AlertDialog{
    contract { callsInPlace(dialogConfig, InvocationKind.EXACTLY_ONCE) }
    return materialAlertDialog(
        title = title,
        message = message,
        icon = null,
        isCancellable = isCancelable
    ) {
        setIcon(iconResource)
        dialogConfig()
    }
}

/**
 * Instantiates a [MaterialAlertDialogBuilder] for the [Context], sets the passed [title], [message] and
 * [icon], applies the [dialogConfig] lambda to it, then creates an [AlertDialog] from
 * the builder, and returns it, so you can call [AlertDialog.show] on the created dialog.
 */
fun Context.materialAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    icon: Drawable? = null,
    isCancellable: Boolean = true,
    dialogConfig: MaterialAlertDialogBuilder.() -> Unit = {}
): AlertDialog {
    contract { callsInPlace(dialogConfig, InvocationKind.EXACTLY_ONCE) }
    return MaterialAlertDialogBuilder(this).apply {
        this.setTitle(title)
        this.setMessage(message)
        setIcon(icon)
        setCancelable(isCancellable)
        dialogConfig()
    }.create()
}