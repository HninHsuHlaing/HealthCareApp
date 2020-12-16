package com.padcx.doctor.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.doctor.R
import com.padcx.doctor.adapters.QuestionTemplateAdapter
import com.padcx.doctor.mvp.presenter.GeneralQuestionTemplatePresenter
import com.padcx.doctor.mvp.presenter.Impl.GeneralQuestionTemplatePresenterImpl
import com.padcx.doctor.mvp.view.GeneralQuestionTemplateView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.GeneralQuestionTemplateVO
import kotlinx.android.synthetic.main.activity_question_template.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class QuestionTemplateActivity  : BaseActivity() , GeneralQuestionTemplateView
{

    private lateinit var mPresenter: GeneralQuestionTemplatePresenter
    private lateinit var adapter: QuestionTemplateAdapter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, QuestionTemplateActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_template)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        tex_back.setOnClickListener { onBackPressed() }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<GeneralQuestionTemplatePresenterImpl, GeneralQuestionTemplateView>()
        mPresenter.onUiReady(this,this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        sendDataToPreviousPage("")
    }

    private fun setUpRecyclerView() {
        rc_general_questions?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = QuestionTemplateAdapter(mPresenter)
        rc_general_questions?.adapter = adapter
        rc_general_questions?.setHasFixedSize(false)
    }
    fun sendDataToPreviousPage(questions : String)
    {
        val intent = Intent()
        intent.putExtra("questions" , questions)
        setResult(Activity.RESULT_OK , intent)
        finish()
    }

    override fun displayGeneralQuestions(list: List<GeneralQuestionTemplateVO>) {
        adapter.setNewData(list.toMutableList())
    }

    override fun navigateToToChatRoom(questions: String) {
        sendDataToPreviousPage(questions)
    }

    override fun showError(error: String) {

    }
}