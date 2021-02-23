package com.englishcard.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.englishcard.R
import org.w3c.dom.Text

class MainCardSetDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.card_set_dialog_layout, null)

            builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.yes, null)
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create().apply {
                setOnShowListener {
                    val button: Button =
                        (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                    button.setOnClickListener {
                      val name = view.findViewById<EditText>(R.id.card_set_name).text
                      val language = view.findViewById<EditText>(R.id.card_set_language).text
                        if (name.isNotEmpty() && language.isNotEmpty()) {
                            (activity as? MainView.DialogCallback)?.onDialogYes(name.toString(), language.toString())
                            dismiss()
                        } else {
                            view.findViewById<TextView>(R.id.card_set_error).visibility = View.VISIBLE
                        }

                    }
                }
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
