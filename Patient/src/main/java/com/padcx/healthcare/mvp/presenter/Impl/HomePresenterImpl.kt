package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.HomePresenter
import com.padcx.healthcare.mvp.view.HomeView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.SpecialitiesVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/5/2020
 */
class HomePresenterImpl : HomePresenter, AbstractBaseePresenter<HomeView>() {
    private val mHealthCareModel = HealthCareModelImpl
   init {

//        mHealthCareModel.getRecentlyConsultatedDoctor("0b9f8e00-3176-11eb-977a-57c697311f58")
//        mHealthCareModel.getGeneralQuestionFromNetwork()
//        mHealthCareModel.getConsultationChatFromNetwork("0bda7420-3176-11eb-977a-57c697311f58")
    }


    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ) {
        mHealthCareModel.getSpecilitiesFromNetwork()
        mHealthCareModel.getSpecilitiesFromDB()
            .observe(owner, Observer {
                mView.displaySpecialityList(it)
            })

        mHealthCareModel.getRecentlyConsultatedDoctor(SessionManager.patient_id.toString())
        mHealthCareModel.getRecentlyConsultatedDoctorFromDB()
            .observe(owner, Observer {
                mView.displayRecentDoctorList(it)
            })

        mHealthCareModel.getConsultationAccepts(SessionManager.patient_id.toString(),onSuccess = {},onError = {})
        mHealthCareModel.getConsultationAcceptsFromDB().
                observe(owner, Observer {
//                    var data =it.filter{
//                      //  it.doctor.id.isNotEmpty()
//
//                        Log.d("Consultation id", it.toString())
//                      //  it.consultation_id.toString().isNotEmpty()
//                     //   it.consultation_id.toString()== "none"
//                    }
                    mView?.displayConsultationRequest(it)
                })
    }

    override fun onTap() {

    }

    override fun onTapStarted(
        consultationChatId: String,
        consultationRequestVO: ConsulationRequestVO
    ) {
        mView.nextPageToChatRoom(consultationChatId,consultationRequestVO)
    }

    override fun onTapSpeciality(context: Context, specialitiesVO: SpecialitiesVO) {

    }

    override fun onTapSpeciality(specialitiesVO: SpecialitiesVO) {
        mView?.nextPageToCaseSummary(specialitiesVO)
    }

    override fun navigateToNextScreen() {

    }

    override fun statusUpdateForCompleteType(
        context: Context,
        cosultation_chat_id: String,
        consultationRequestVO: ConsulationRequestVO
    ) {
        mHealthCareModel.joinedChatRoomPatient(cosultation_chat_id,consultationRequestVO,onSuccess = {},onError = {})
    }

//    override fun onReadyStage(lifecycleOwner: LifecycleOwner) {
//       // mHealthCareModel.getSpecilitiesFromNetwork()
//        mHealthCareModel.getSpecilitiesFromDB().observe(lifecycleOwner, Observer {
//            Log.d("Specilities" , it.toString())
//        })
//
//        mHealthCareModel.getRecentlyConsultatedDoctorFromDB().observe(lifecycleOwner, Observer {
//            Log.d("Recently Doctor", it.toString())
//        })
//
//        mHealthCareModel.getRecentlyConsultatedDoctorFromDB().observe(lifecycleOwner, Observer {
//            Log.d("General Question", it.toString())
//        })
//    }
}