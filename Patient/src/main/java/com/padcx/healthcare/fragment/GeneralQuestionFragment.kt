package com.padcx.healthcare.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.CaseSummaryDelegate
import com.padcx.healthcare.mvp.presenter.CaseSummaryPresenter
import com.padcx.healthcare.mvp.presenter.Impl.CaseSummaryPresenterImpl
import com.padcx.healthcare.mvp.view.CaseSummaryView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.activities.Basefragment
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.data.vo.SpecialquestionVO
import kotlinx.android.synthetic.main.fragment_general_question.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */

private const val ARG_PARAM = "email"
class GeneralQuestionFragment: Basefragment(), CaseSummaryView {
    lateinit var listener: CaseSummaryDelegate
    private lateinit var mPresenter: CaseSummaryPresenter
    private var email: String? = null
    private var year: String? = null
    private var month: String? = null
    private var day: String? = null
    private var bloodType: String? = null
    companion object {

        @JvmStatic
        fun newInstance(email: String, listener: CaseSummaryDelegate) =
            GeneralQuestionFragment().apply {
                this.listener = listener
                this.email = email
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_general_question, container, false)
        arguments?.let {
            email = it.getString(ARG_PARAM)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpActionListener()
        setUpItemSelectedListener()
    }
    private fun setUpItemSelectedListener() {
        year_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                year = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        month_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                month = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                day = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        bloodtype_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                bloodType = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
    private fun setUpPresenter() {
        activity?.let {
            mPresenter = getPresenter<CaseSummaryPresenterImpl, CaseSummaryView>()
            mPresenter.onUiReadyforGeneralQuestion(it, email.toString(), this)
        }
    }
    private fun setUpActionListener() {
        btn_next.setOnClickListener {

            if(SessionManager.patient_bloodType.toString().isNotEmpty())
            {
                ed_height.text = Editable.Factory.getInstance().newEditable( SessionManager.patient_height)
                ed_comment.text = Editable.Factory.getInstance().newEditable( SessionManager.patient_allergicMedicine)
            }

            val height = ed_height.text.toString()
            val comment = ed_comment.text.toString()
            val weight = ed_weight.text.toString()
            val blood_pressure = ed_bloodpressure.text.toString()


            if (height.isNotEmpty() && comment.isNotEmpty() && weight.isNotEmpty() && blood_pressure.isNotEmpty()) {

                if(SessionManager.patient_bloodType.toString().isEmpty()) {
                    SessionManager.patient_dob = "$day $month $year"
                    SessionManager.patient_bloodType = bloodType
                }

                SessionManager.patient_height = height
                SessionManager.patient_allergicMedicine = comment
                SessionManager.patient_weight = weight
                SessionManager.patient_bloodPressure = blood_pressure
                listener.onGeneralQuestionCallBack()

            } else {
                val snackBar = Snackbar.make(
                    it, resources.getString(R.string.general_form),
                    Snackbar.LENGTH_SHORT
                ).setAction(resources.getString(R.string.know), null)
                snackBar.setActionTextColor(Color.BLACK)
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(Color.WHITE)
                val textView =
                    snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.BLACK)
                snackBar.show()
            }
        }
    }

    override fun displaySpecialQuestions(list: List<SpecialquestionVO>) {

    }

    override fun displayOnceGeneralQuestion() {
        card_userinfo.visibility = View.GONE
        ly_onetime_fil.visibility = View.VISIBLE
    }

    override fun displayAlwaysGeneralQuestion() {
        card_userinfo.visibility = View.VISIBLE
        userinfotext.text = resources.getString(R.string.patient_name) + "  :  " + SessionManager.patient_name +"\n" + resources.getString(R.string.patient_dateOfbirth) + "  :  " + SessionManager.patient_dob +"\n" + resources.getString(R.string.patient_height) + "  :  " + SessionManager.patient_height +"\n"+ resources.getString(R.string.patient_blood_type) + "  :  " + SessionManager.patient_bloodType +"\n"+ resources.getString(R.string.patient_reactionalert) + "  :  " + SessionManager.patient_allergicMedicine
        ly_onetime_fil.visibility = View.GONE
    }

    override fun replaceQuestionAnswerList(position: Int, questionAnswerVO: QuestionAndAnswerVO) {

    }
}