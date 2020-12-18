package com.padcx.shared.data.model.impl

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import com.padcx.shared.data.model.BaseModel
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.vo.*
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO
import com.padcx.shared.network.CloudFireStoreFireBaseApiImpl
import com.padcx.shared.network.FirebaseApi

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
object HealthCareModelImpl : HealthCareModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFireStoreFireBaseApiImpl


    override fun uploadPhotoToFirebaseStorage(image: Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.uploadPhotoToFirebaseStorage(image ,
                onSuccess,
                onFailure)
    }

    override fun getDoctorByEmail(
        email: String,
        onSuccess: (List<DoctorVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorByEmail(email, onSuccess = {
            mTheDB.doctorDao().deleteAllDoctorData()
            mTheDB.doctorDao().insertDoctorData(it)
        }, onFailure = {})
    }

    override fun getDoctorByEmailFromDB(email: String): LiveData<DoctorVO> {
        return  mTheDB.doctorDao().getAllDoctorDataByEmail(email)
    }

    override fun addDoctorInfo(doctorVO: DoctorVO, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.addOrUpdateDoctorData(doctorVO, onSuccess = {}, onFailure = { onError(it) })
    }

    override fun getPatientByEmail(
        email: String,
        onSuccess: () -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientByEmail(email, onSuccess = {
            mTheDB.patientDao().deleteAllPatientData()
            mTheDB.patientDao().insertNewPatient(it)
        }, onFailure = {

        })
    }

    override fun getPatientByEmailFromDB(email: String): LiveData<PatientVO> {
        return mTheDB.patientDao().getAllPatientDataByEmail(email)
    }

    override fun registerNewPatient(patientVO: PatientVO,onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addOrUpdatePatientData(patientVO,onSuccess,onFailure)
    }

    override fun registerNewDoctor(doctorVO: DoctorVO,onSuccess: () -> Unit, onFailure: (String) -> Unit){
        mFirebaseApi.addOrUpdateDoctorData(doctorVO,onSuccess,onFailure)
    }

    override fun addPatientInfo(patientVO: PatientVO, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.updatePatientData(patientVO, onSuccess = {}, onFailure = { onError(it) })
    }

    ///////For Specilities//////
    override fun getSpecilitiesFromNetwork() {
        mFirebaseApi.getSpecialities(onSuccess = {
            Log.d("Specilities for DB", it.toString())
        mTheDB.SpecilitiesDao().insertSpecialitiesList(it)
        },
        onFailure = {

        })
    }

    override fun getSpecilitiesFromDB(): LiveData<List<SpecialitiesVO>> {
        return  mTheDB.SpecilitiesDao().getSpecialities()
    }


    ////For Recently Doctor/////
    override fun getRecentlyConsultatedDoctor(documentId: String) {
        mFirebaseApi.getRecentlyConsultationDoctor(documentId, onSuccess = {
            Log.d("Recently Doctor for DB", it.toString())
            mTheDB.RecentDoctorDao().deleteAllRecentDoctorData()
            mTheDB.RecentDoctorDao().insertRecentDoctorList(it)
        },
        onFailure = {

        })
    }

    override fun getRecentlyConsultatedDoctorFromDB(): LiveData<List<RecentlyDoctorVO>> {
        return mTheDB.RecentDoctorDao().getRecentDoctor()
    }

    override fun getGeneralQuestionFromNetwork(onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getGeneralQuestion(onSuccess = {
            Log.d("general Question", it.toString())
            mTheDB.GeneralQuestionDao().insertGeneralQuestionList(it)
        },
            onFailure = {

            }
        )
    }

    override fun getGeneralQuestionFromDB(): LiveData<List<GeneralQuestionTemplateVO>> {
        return mTheDB.GeneralQuestionDao().getAllGeneralQuestion()
    }

    override fun getConsultationChatFromNetwork(patientId: String) {
        mFirebaseApi.getConsultationChat(patientId,onSuccess = {
            Log.d("Consultation Chat",it.toString())
           // mTheDB.ConsultationChatDao().insertConsultationChatList(it)
        },
        onFailure = {

        })
    }

    override fun getConsultationChatFromDB(): LiveData<List<ConsulationChatVO>> {
        return mTheDB.ConsultationChatDao().getAllConsultationChatData()
    }



    override fun getPrescriptionMedicineFromNetwork() {

    }

    override fun joinedChatRoomPatient(
        consultation_chat_id: String,
        consultationRequestVO: ConsulationRequestVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.startConsultationChatPatient(consultation_chat_id,consultationRequestVO,
        onSuccess = {},onFailure = {})
    }

    override fun getBrodcastConsultationRequests(
        speciality: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mFirebaseApi.getBroadCastConsultationBySpeciality(speciality,onSuccess = {
            mTheDB.ConsultationRequestDao().deleteAllConsultationRequestData()
            mTheDB.ConsultationRequestDao().insertConsultationRequestData(it)
        },onFailure = {
            onFailure(it)
        })
    }

    override fun getBrodcastConsultationRequestsFromDB(speciality: String): LiveData<List<ConsulationRequestVO>> {
       return mTheDB.ConsultationRequestDao().getAllConsultationRequestDataBySpeciality(speciality)
    }

    ///////
    override fun deleteConsultationRequestById(consulationId: String): LiveData<List<ConsulationRequestVO>> {
            mTheDB.ConsultationRequestDao().deleteAllConsultationRequestDataById(consulationId)
        return mTheDB.ConsultationRequestDao().getAllConsultationRequestData()
    }

    override fun acceptRequest(
            status: String,
            consulationId: String,
            questionAnswerList: List<QuestionAndAnswerVO>,
            patientVO: PatientVO,
            doctorVO: DoctorVO,
            patientId: String,
            doctorId: String,
            cr_id: String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        mFirebaseApi.acceptRequest(status, consulationId, questionAnswerList, patientVO, doctorVO,patientId,doctorId, cr_id,onSuccess={ }, onFailure={})
    }

    override fun getConsultationAccepts(
        patientId: String,
        onSuccess: (List<ConsulationRequestVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getBroadcastConsultationRequestByPatient(
            patientId,
            onSuccess = {
                mTheDB.ConsultationRequestDao().deleteAllConsultationRequestData()
                mTheDB.ConsultationRequestDao().insertConsultationRequestData(it)
            }, onFailure =
            { onError(it) })
    }

    override fun getConsultationAcceptsFromDB(): LiveData<List<ConsulationRequestVO>>{
        return mTheDB.ConsultationRequestDao().getConsultationAcceptData("accept")
    }

    override fun getSpecialQuestionBySpeciality(
        speciality: String,
        onSuccess: (List<SpecialquestionVO>) -> Unit,
        onError: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialQuestionsBySpeciality(speciality,
            onSuccess = {
                mTheDB.SpecialQuestionDao().deleteSpecialQuestions()
                mTheDB.SpecialQuestionDao().insertSpecialQuestions(it)
            }, onFailure =
            { onError(it) })
    }

    override fun getSpecialQuestionBySpecialityFromDB(): LiveData<List<SpecialquestionVO>> {
        return mTheDB.SpecialQuestionDao().getAllSpecialQuestionsData()
    }

    override fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAndAnswerVO>,
        patientVO: PatientVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.sendBroadCastConsultationRequest(speciality,
            questionAnswerList,
            patientVO,
            dateTime,
            onSuccess = {

            }, onFailure = { onFailure(it) })
    }

    override fun startConsultation(consulationId: String, dateTime: String, questionAnswerList: List<QuestionAndAnswerVO>, patientVO: PatientVO, doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.startConsultation(consulationId, dateTime, questionAnswerList, patientVO, doctorVO,
                onSuccess = {}, onFailure = { onFailure(it) })
    }

    override fun getConsultationByConsulationRequestId(consultation_request_id: String, onSuccess: (consultationRequestVO: ConsulationRequestVO) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getBroadCastConsultation(consultation_request_id,
                onSuccess = {
                    mTheDB.ConsultationRequestDao().deleteAllConsultationRequestData()
                    mTheDB.ConsultationRequestDao().insertConsultationRequest(it)
                }, onFailure = { onFailure(it) })
    }

    override fun getConsultationByConsulationRequestIdFromDB(consultation_request_id: String): LiveData<ConsulationRequestVO> {
        return mTheDB.ConsultationRequestDao().getConsultationRequestByConsultationRequestId(consultation_request_id)
    }

    override fun getConsultationChat(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getConsulationChatById(consulationId,
                onSuccess = {
                    mTheDB.ConsultationChatDao().deleteAllConsultationChatData()
                    mTheDB.ConsultationChatDao().insertConsultationChatData(it)
                }, onFailure = { onError(it) })
    }

    override fun getChatMessage(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
            mFirebaseApi.getAllChatMessage(consulationId, onSuccess = {
                mTheDB.ChatMessageDao().deleteAllChatMessageData()
                mTheDB.ChatMessageDao().insertChatMessages(it)
            },onFailure = {})
    }

    override fun getAllChatMessageFromDB(): LiveData<List<MessageVO>> {
        return mTheDB.ChatMessageDao().getAllChatMessage()
    }

    override fun sendChatMessage(messageVO: MessageVO, consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.sendMessage(consulationId,messageVO, onSuccess = {}, onFailure= {})
    }

    override fun getConsultationByDoctorId(doctorId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getConsulationChatForDoctor(doctorId,
                onSuccess = {
                    mTheDB.ConsultationChatDao().deleteAllConsultationChatData()
                    mTheDB.ConsultationChatDao().insertConsultationChatData(it)

                }, onFailure = { onError(it) })
    }

    override fun getConsultationByDoctorIdFromDB(doctorId: String): LiveData<List<ConsulationChatVO>> {
        return mTheDB.ConsultationChatDao().getAllConsultationChatDataByDoctorId(doctorId)
    }

    override fun getConsultedPatient(doctorId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getConsultedPatient(doctorId,onSuccess = {
            mTheDB.ConsultatedPatientDao().deleteConsultedPatient()
            mTheDB.ConsultatedPatientDao().insertConsultedPatient(it)
        }, onFailure= {})
    }

    override fun getConsultedPatientFromDB(): LiveData<List<ConsultatedPatientVO>> {
        return mTheDB.ConsultatedPatientDao().getConsultedPatient()
    }

    override fun getConsultationChatByPatientId(patientId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getConsulationChatByPatientId(patientId,
                onSuccess = {
                    mTheDB.ConsultationChatDao().deleteAllConsultationChatData()
                    mTheDB.ConsultationChatDao().insertConsultationChatData(it)
                }, onFailure = { onError(it) })
    }

    override fun getConsultationChatByPatientIdFromDB(patientId: String): LiveData<List<ConsulationChatVO>> {
        return mTheDB.ConsultationChatDao().getAllConsultationChatDataByPatientId(patientId)
    }

    override fun finsishConsultation(consultationChatVO: ConsulationChatVO, prescriptionList: List<PrescriptionVO>, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.finishConsultation(consultationChatVO,prescriptionList, onSuccess = {
        }, onFailure = {})
    }

    override fun getAllMedicine(speciality: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getAllMedicine(speciality,  onSuccess = {
            mTheDB.FrequentlyMedicineDao().deleteAllMedicine()
            mTheDB.FrequentlyMedicineDao().insertMedicalDataList(it)
        }, onFailure = {})
    }

    override fun getAllMedicineFromDB(): LiveData<List<FrequentlyMedicineVO>> {
        return mTheDB.FrequentlyMedicineDao().getAllMedicine()
    }

    override fun getPrescription(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.getPrescription(consulationId,  onSuccess = {
            mTheDB.PrescriptionDao().deleteAllPrescriptionData()
            mTheDB.PrescriptionDao().insertPrescriptionList(it)
        }, onFailure = {})
    }

    override fun getPrescriptionFromDB(): LiveData<List<PrescriptionVO>> {
        return mTheDB.PrescriptionDao().getAllPrescriptionData()
    }

    override fun saveMedicalRecord(consultationChatVO: ConsulationChatVO, onSuccess: () -> Unit, onError: (String) -> Unit) {
        mFirebaseApi.saveMedicalRecord(consultationChatVO, onSuccess = {
        }, onFailure = {})    }

    override fun checkout(prescriptionList: List<PrescriptionVO>, delivery_address: String, doctorVO: DoctorVO, patientVO: PatientVO, total_price: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.checkoutMedicine(prescriptionList, delivery_address,doctorVO, patientVO,total_price, onSuccess = {}, onFailure = {})
    }

    override fun getConsultationChatFromDB(consulationId: String): LiveData<ConsulationChatVO> {
            return mTheDB.ConsultationChatDao().getAllConsultationChatDataBy(consulationId)
    }

//    override fun getConsultationAcceptListFromDB(speciality: String): LiveData<List<ConsulationRequestVO>> {
//        return  mTheDB.ConsultationRequestDao()
//    }

//    override fun getPrescriptionMedicineFromDB(): LiveData<List<PrescriptionVO>> {
//     return mTheDB.
//    }

}