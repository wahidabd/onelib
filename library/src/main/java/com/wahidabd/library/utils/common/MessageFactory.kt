package com.wahidabd.library.utils.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showAlertDialog(context: Context, message: String) {
    AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

fun showAlertDialog(context: Context, title: String, message: String) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

fun showAlertDialog(
    context: Context,
    message: String,
    positive: String,
    positiveListener: DialogInterface.OnClickListener
) {
    AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton(positive) { dialog, wich ->
            positiveListener
        }
        .show()
}

fun showAlertDialog(
    context: Context,
    title: String,
    message: String,
    positive: String,
    positiveListener: DialogInterface.OnClickListener
) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positive) { dialog, wich ->
            positiveListener
        }
        .show()
}

fun showAlertDialog(
    context: Context,
    title: String,
    message: String,
    positive: String,
    positiveListener: DialogInterface.OnClickListener,
    negative: String,
    negativeListener: DialogInterface.OnClickListener
) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positive) { dialog, wich ->
            positiveListener
        }
        .setNegativeButton(negative){ _, _ ->
            negativeListener
        }
        .show()
}

fun showSnackbarMessage(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun showSnackbarMessage(
    view: View,
    message: String,
    actionName: String,
    actionListener: View.OnClickListener
) {
    val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
    snackbar.setAction(actionName, actionListener)
    snackbar.show()
}

fun showToast(message: String) {
    Toast.makeText(ContextProvider.get(), message, Toast.LENGTH_SHORT).show()
}

fun showLongToast(message: String) {
    Toast.makeText(ContextProvider.get(), message, Toast.LENGTH_LONG).show()
}