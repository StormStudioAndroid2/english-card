package com.englishcard.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.englishcard.R
import com.englishcard.model.domain.cards.CardSet

interface TestResultFragmentListener {

    fun onCompleteButtonClick()
}

class TestResultFragment: Fragment() {

    private lateinit var titleSetTextView : TextView
    private lateinit var rightAnswerTextView: TextView
    private lateinit var wrongAnswerTextView: TextView
    private lateinit var completeButton: View
    private lateinit var testResultFragmentListener: TestResultFragmentListener
    private lateinit var testModel: TestModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        savedInstanceState?.getParcelable<TestModel>("TEST_KEY")?.let { testModel1 ->
            testModel = testModel1
        }

        testResultFragmentListener = (activity as TestResultFragmentListener)

        titleSetTextView = view.findViewById(R.id.card_set_title)
        titleSetTextView.text = getString(R.string.result_set_title, testModel.cardSetName)

        rightAnswerTextView = view.findViewById(R.id.right_answers_text)
        wrongAnswerTextView = view.findViewById(R.id.wrong_answers_text)

        completeButton = view.findViewById(R.id.complete_button)
        completeButton.setOnClickListener { testResultFragmentListener.onCompleteButtonClick() }
        rightAnswerTextView.text = getString(R.string.result_right_answers, testModel.rightAnswers.toString())
        wrongAnswerTextView.text = getString(R.string.result_wrong_fail, testModel.wrongAnswers.toString())

        return view
    }

    companion object {

        fun createInstance(testModel: TestModel): TestResultFragment =
            TestResultFragment().apply {
                this.testModel = testModel
                arguments = Bundle()
                    .also { args ->
                        // put in bundle info from activity
                    }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("TEST_KEY", testModel)
    }
}