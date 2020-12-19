package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenter.HomePresenter
import com.padcx.doctor.mvp.view.HomView
import com.padcx.doctor.util.SessionManager
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class HomePresenterImpl: HomePresenter, AbstractBaseePresenter<HomView>() {

    private var mHealthCareModel = HealthCareModelImpl
    lateinit var mOwner: LifecycleOwner

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner=  owner
        mHealthCareModel.getBrodcastConsultationRequests(
            SessionManager.doctor_specility.toString(),
            onSuccess = { }, onFailure = {}
        )
        mHealthCareModel.getBrodcastConsultationRequestsFromDB(
            SessionManager.doctor_specility.toString())
            .observe(owner, Observer {consultationRequest->
                consultationRequest?.let {
                    val data=  consultationRequest.filter {
                        it.status.toString() == "none"
                    }
                    mView.displayConsultationRequests(data)
                }
            })
        mHealthCareModel.getConsultationByDoctorId(SessionManager.doctor_id.toString(), onSuccess = {}, onError = {})

        mHealthCareModel.getConsultationByDoctorIdFromDB(SessionManager.doctor_id.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayConsultationAcceptList(data) }
                })

        mHealthCareModel.getConsultedPatient(SessionManager.doctor_id.toString(),onSuccess = {}, onError = {})

        mHealthCareModel.getConsultedPatientFromDB()
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayConsultedPatient(data) }
                })

    }

    override fun onTapNext(consultationRequestVO: ConsulationRequestVO) {
        mHealthCareModel.deleteConsultationRequestById(consultationRequestVO.cr_id)
            .observe(mOwner, Observer {consultationRequest->
                consultationRequest?.let {
                    mView.displayConsultationRequests(consultationRequest)
                }
            })
    }

    override fun onTapSkip(consultationRequestVO: ConsulationRequestVO) {
        mHealthCareModel.deleteConsultationRequestById(consultationRequestVO.cr_id)
            .observe(mOwner, Observer {consultationRequest->
                consultationRequest?.let {
                    mView.displayConsultationRequests(consultationRequest)
                }
            })
    }

    override fun onTapPostpone(consultationRequestVO: ConsulationRequestVO) {
//        AcceptRequest("postpone", consultationRequestVO)
        mView?.displayPostPoneChooserDialog(consultationRequestVO)
    }

    override fun onTapAccept(consultationRequestVO: ConsulationRequestVO) {
        AcceptRequest("accept", consultationRequestVO)
    }

    override fun onTapPostponeTime(postPoneTime: String, consultationRequestVO: ConsulationRequestVO) {
        AcceptRequest("postpone $postPoneTime", consultationRequestVO)
    }

    override fun onTapMedicalRecord(consultationRequestVO: ConsulationChatVO) {
        mView?.displayPatientInfoDialog(consultationRequestVO)
    }

    override fun onTapPrescription(consultationRequestVO: ConsulationChatVO) {
        mView?.displayPrescriptionDialog(consultationRequestVO.id, consultationRequestVO.patient?.name.toString(),
                consultationRequestVO.start_consultation_date.toString())
    }

    override fun onTapSendMessage(consultationRequestVO: ConsulationChatVO) {
        mView?.nextPageChatRoom(consultationRequestVO.id)
    }


    override fun onTapDoctorComment(consultationRequestVO: ConsulationChatVO) {
        mHealthCareModel.getConsultationChatFromDB(consultationRequestVO.id)
                .observe(mOwner, Observer { data ->
                    data?.let {
                        mView?.displayMedicalCommentDialog(it)
                    }
                })
    }
    private fun AcceptRequest(status: String,  consultationRequestVO: ConsulationRequestVO){
        var doctorVo = DoctorVO(
            id = SessionManager.doctor_id.toString(),
            deviceId = SessionManager.doctor_deviceId.toString(),
            name = SessionManager.doctor_name,
            phone = SessionManager.doctor_phone,
            degree = SessionManager.doctor_degree,
            email = SessionManager.doctor_email,
            biography = SessionManager.doctor_biography,
            photo = SessionManager.doctor_photo,
            speciality = SessionManager.doctor_specility,
            address = SessionManager.doctor_address,
            experience = SessionManager.doctor_experience
        )

        mHealthCareModel.acceptRequest(
                status,
                consultationRequestVO.cr_id,
                consultationRequestVO.caseSummary,
                consultationRequestVO.patient,
                doctorVo, consultationRequestVO.patient.id, doctorVo.id,consultationRequestVO.cr_id, onSuccess = {}, onFailure = {})

        Log.d("consulation",consultationRequestVO.cr_id)
        mView?.nextPagePatientInfo(consultationRequestVO.cr_id)
//        mHealthCareModel.acceptRequest(status,consultationRequestVO.cr_id,consultationRequestVO.caseSummary,
//            consultationRequestVO.patient,doctorVo,onSuccess = {},onFailure = {}        )
    }
}