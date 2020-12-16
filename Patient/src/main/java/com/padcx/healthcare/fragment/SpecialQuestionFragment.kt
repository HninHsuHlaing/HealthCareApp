package com.padcx.healthcare.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcx.healthcare.R
import com.padcx.healthcare.adapters.QuestionAndAnswerAdapter
import com.padcx.healthcare.adapters.SpecialQuestionAdapter
import com.padcx.healthcare.delegate.CaseSummaryDelegate
import com.padcx.healthcare.mvp.presenter.CaseSummaryPresenter
import com.padcx.healthcare.mvp.presenter.Impl.CaseSummaryPresenterImpl
import com.padcx.healthcare.mvp.view.CaseSummaryView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.activities.Basefragment
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.data.vo.SpecialquestionVO
import kotlinx.android.synthetic.main.casesummy_confirm_dialog.view.*
import kotlinx.android.synthetic.main.fragment_special_question.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
private const val ARG_PARAM = "speciality"
class SpecialQuestionFragment :Basefragment(),CaseSummaryView{
    lateinit var listener : CaseSummaryDelegate
    private var speciality: String? = null
    private lateinit var mPresenter: CaseSummaryPresenter
    private lateinit var adapter: SpecialQuestionAdapter
    private lateinit var questionAnswerAdapter: QuestionAndAnswerAdapter
    private  var questionAnswerList : ArrayList<QuestionAndAnswerVO> = arrayListOf()
    companion object {

        @JvmStatic
        fun newInstance(mSpeciality : String, listener : CaseSummaryDelegate) =
            SpecialQuestionFragment().apply {
                this.listener =listener
                this.speciality=mSpeciality
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view= inflater.inflate(R.layout.fragment_special_question, container, false)
        arguments?.let {
            speciality = it.getString(ARG_PARAM)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListener()
    }

    private fun setUpActionListener() {
        btn_confirm.setOnClickListener {
            listener.onSpecitalQuestionCallBack()
            var patientVO = PatientVO(
            id= SessionManager.patient_id.toString(),
                name= SessionManager.patient_name.toString(),
                deviceId=SessionManager.patient_deviceId,
                email=SessionManager.patient_email.toString(),
                photo=SessionManager.patient_photo,
                dob=SessionManager.patient_dob,
                height=SessionManager.patient_height,
                blood_type= SessionManager.patient_bloodType.toString(),
                allergic_medicine=SessionManager.patient_allergicMedicine,
                weight =SessionManager.patient_weight,
                blood_pressure=SessionManager.patient_bloodPressure.toString())
            showCaseSummaryConfirmDialog(patientVO)
        }
    }

    private fun setUpRecyclerView() {
        rc_specialquestion.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = SpecialQuestionAdapter(mPresenter)
        rc_specialquestion.adapter = adapter
    }

    private fun setUpPresenter() {
        activity?.let{
            mPresenter = getPresenter<CaseSummaryPresenterImpl, CaseSummaryView>()
            mPresenter.onUiReadyforSpecialQuestion(it, speciality.toString(),this)
        }
    }

    override fun displaySpecialQuestions(list: List<SpecialquestionVO>) {
        adapter.setNewData(list.toMutableList())
        questionAnswerList.clear()
        for(item in list)
        {
            questionAnswerList.add(QuestionAndAnswerVO(item.id,item.question,""))
        }
        adapter.setQuestionAnswerList(questionAnswerList)
    }

    override fun displayOnceGeneralQuestion() {

    }

    override fun displayAlwaysGeneralQuestion() {

    }

    override fun replaceQuestionAnswerList(position: Int, questionAnswerVO: QuestionAndAnswerVO) {
        questionAnswerList.set(position,questionAnswerVO)
    }
    private  fun showCaseSummaryConfirmDialog(patientVO: PatientVO)
    {
        val view = layoutInflater.inflate(R.layout.casesummy_confirm_dialog, null)
        val dialog = context?.let { Dialog(it) }
        val pdateofBirth = view?.findViewById<TextView>(R.id.pdateofBirth)
        val pname = view?.findViewById<TextView>(R.id.pname)
        val pheight = view?.findViewById<TextView>(R.id.pheight)
        val pbloodtype = view?.findViewById<TextView>(R.id.pBloodType)
        val pweight = view?.findViewById<TextView>(R.id.pweight)
        val pbloodpressure = view?.findViewById<TextView>(R.id.pBloodPressure)
        val pcomment = view?.findViewById<TextView>(R.id.palergicMedicine)
        val rc_question_answer = view?.findViewById<RecyclerView>(R.id.rc_question_answer)

        pname?.text = patientVO.name
        pdateofBirth?.text = patientVO.dob
        pheight?.text = patientVO.height + "ft"
        pbloodtype?.text  =patientVO.blood_type
        pweight?.text  =patientVO.weight + " lb"
        pbloodpressure?.text  =patientVO.blood_pressure + " mmHg"
        pcomment?.text  =patientVO.allergic_medicine

        rc_question_answer?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        questionAnswerAdapter = QuestionAndAnswerAdapter(mPresenter,"")
        rc_question_answer?.adapter = questionAnswerAdapter

        rc_question_answer?.setHasFixedSize(false)
        questionAnswerAdapter.setNewData(questionAnswerList)

        rc_specialquestion.adapter = adapter

        dialog?.apply {
            setCancelable(false)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }


        view.cs_btn_confirm.setOnClickListener {
            activity?.let { it ->
                mPresenter.onTapSendBroadCast(it,speciality.toString(),questionAnswerList,patientVO) }
            dialog?.dismiss()
            activity?.finish()
        }
        dialog?.show()
    }
}