package com.padcx.shared.data.model

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.padcx.shared.data.vo.*
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO
import com.padcx.shared.network.FirebaseApi

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
interface HealthCareModel {
    var mFirebaseApi : FirebaseApi

    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    /***
     * get Doctor By Email
     */
    fun getDoctorByEmail(email :String, onSuccess: (List<DoctorVO>) -> Unit, onFaiure: (String) -> Unit)
    fun getDoctorByEmailFromDB(email: String) : LiveData<DoctorVO>
    fun addDoctorInfo(doctorVO: DoctorVO, onSuccess: () -> Unit, onError: (String) -> Unit)

    /***
     * getpatient By Email
     */
    fun getPatientByEmail(email: String, onSuccess: () -> Unit, onFaiure: (String) -> Unit)
    fun getPatientByEmailFromDB(email: String) : LiveData<PatientVO>

    fun registerNewPatient(patientVO: PatientVO ,onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun registerNewDoctor(doctorVO: DoctorVO,onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addPatientInfo(patientVO: PatientVO, onSuccess: () -> Unit, onError: (String) -> Unit)

    /////Specilities/////
    fun getSpecilitiesFromNetwork()
    fun getSpecilitiesFromDB() : LiveData<List<SpecialitiesVO>>

    ////Recently Consultated Doctor/////
    fun getRecentlyConsultatedDoctor(documentId : String)
    fun getRecentlyConsultatedDoctorFromDB() : LiveData<List<RecentlyDoctorVO>>

    //////general Question//////
    fun getGeneralQuestionFromNetwork( onSuccess: () -> Unit, onError: (String) -> Unit)
    fun getGeneralQuestionFromDB() : LiveData<List<GeneralQuestionTemplateVO>>

//    fun getGeneralQuestionTemplate( onSuccess: () -> Unit, onError: (String) -> Unit)
//
//    fun getGeneralQuestionTemplateFromDB () : LiveData<List<GeneralQuestionTemplateVO>>

    ///////Consultation Chat/////

    fun getConsultationChatFromNetwork(patientId : String)
    fun getConsultationChatFromDB() : LiveData<List<ConsulationChatVO>>

    /////Prescription Medicine//////
    fun getPrescriptionMedicineFromNetwork()
   // fun getPrescriptionMedicineFromDB() : LiveData<List<PrescriptionVO>>

    fun joinedChatRoomPatient(consultation_chat_id: String, consultationRequestVO: ConsulationRequestVO,
                              onSuccess: () -> Unit,
                              onError: (String) -> Unit)


    /**
     * BroadCast Request for Doctor
     */
    fun getBrodcastConsultationRequests(speciality: String ,
                                        onSuccess: () -> Unit,
                                        onFailure: (String) -> Unit)

    fun getBrodcastConsultationRequestsFromDB(speciality: String) : LiveData<List<ConsulationRequestVO>>

   // fun getConsultationAcceptListFromDB(speciality: String) : LiveData<List<ConsulationRequestVO>>

   fun deleteConsultationRequestById(consulationId : String) : LiveData<List<ConsulationRequestVO>>

    fun acceptRequest(
        status: String,
        consulationId: String,
        questionAnswerList: List<QuestionAndAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        patientId: String,
        doctorId : String,
        cr_id : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun  getConsultationAccepts(
        patientId: String,
        onSuccess: (List<ConsulationRequestVO>) -> Unit,
        onError: (String) -> Unit)

    fun  getConsultationAcceptsFromDB() : LiveData<List<ConsulationRequestVO>>

    fun getSpecialQuestionBySpeciality(
        speciality : String,
        onSuccess: (List<SpecialquestionVO>) -> Unit,
        onError: (String) -> Unit
    )

    fun getSpecialQuestionBySpecialityFromDB() : LiveData<List<SpecialquestionVO>>

    fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAndAnswerVO>,
        patientVO: PatientVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit)

    fun startConsultation(
            consulationId: String,
            dateTime: String,
            questionAnswerList: List<QuestionAndAnswerVO>,
            patientVO: PatientVO,
            doctorVO: DoctorVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    )
    fun  getConsultationByConsulationRequestId(consultation_request_id : String ,
                                               onSuccess: (consultationRequestVO :ConsulationRequestVO) -> Unit,
                                               onFailure: (String) -> Unit)
    fun  getConsultationByConsulationRequestIdFromDB(consultation_request_id : String) : LiveData<ConsulationRequestVO>


    fun getConsultationChat(consulationId:  String,  onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getChatMessage(consulationId: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun getConsultationChatFromDB(consulationId : String) : LiveData<ConsulationChatVO>

    fun getAllChatMessageFromDB () : LiveData<List<MessageVO>>

    fun sendChatMessage( messageVO: MessageVO , consulationId: String ,
                         onSuccess: () -> Unit,
                         onError: (String) -> Unit)

    fun getConsultationByDoctorId(doctorId: String, onSuccess: () -> Unit, onError: (String) -> Unit)
    fun getConsultationByDoctorIdFromDB(doctorId : String) : LiveData<List<ConsulationChatVO>>

    fun getConsultedPatient(doctorId: String ,
                            onSuccess: () -> Unit,
                            onError: (String) -> Unit)

    fun  getConsultedPatientFromDB() : LiveData<List<ConsultatedPatientVO>>

    fun getConsultationChatByPatientId(patientId:  String,  onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getConsultationChatByPatientIdFromDB(patientId : String) : LiveData<List<ConsulationChatVO>>
}