package com.englishcard.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.englishcard.R

class MainCardSetDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.card_set_dialog_layout, null)

            builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener { dialog, id ->
                        val name = view.findViewById<TextView>(R.id.card_set_name).text.toString()
                        val language = view.findViewById<TextView>(R.id.card_set_language).text.toString()
                        if (name.isNotEmpty() && language.isNotEmpty()) {
                            (activity as? MainView.DialogCallback)?.onDialogYes(name, language)
                        } else {
                            Toast.makeText(context, R.string.error_card_set_dialog, Toast.LENGTH_LONG).show()
                        }
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
