package com.padcx.shared.network

import android.graphics.Bitmap
import com.padcx.shared.data.vo.*
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
interface FirebaseApi {
    fun uploadPhotoToFirebaseStorage(image : Bitmap, onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit)

    ///////Doctor //////
    fun addOrUpdateDoctorData(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getDoctorByEmail(email :String,
        onSuccess: (doctorList:DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    )
//    fun updateDoctorData(doctorVO: DoctorVO ,onSuccess: () -> Unit,
//                         onFailure: (String) -> Unit
//    )

    fun getBroadCastConsultation(documentId: String,
                                 onSuccess: (consultationrequestVo : ConsulationRequestVO) -> Unit,
                                 onFailure: (String) -> Unit)

    fun getBroadCastConsultationBySpeciality(speciality : String,
                                             onSuccess: (list : List<ConsulationRequestVO>) -> Unit,
                                             onFailure: (String) -> Unit)

    fun acceptRequest(
        status: String,
        consulationId: String,
        questionAnswerList: List<QuestionAndAnswerVO>,
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        patientId: String,
        documentId: String,
        cr_id :String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit)

    fun getBroadcastConsultationRequestByPatient(
        patientId :String,
        onSuccess: (consulationRequest : List<ConsulationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

   // fun finishConsultation()

    fun preSubscribeMedicine(
        documentid: String,
        medicineVO: PrescriptionVO,
        onSuccess: () -> Unit ,
        onFailure: (String) -> Unit)



    /////Patient//////////
    fun addOrUpdatePatientData(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getPatientByEmail(email:String,
        onSuccess: (patientList: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun updatePatientData(patientVO: PatientVO ,onSuccess: () -> Unit,
                          onFailure: (String) -> Unit
    )
    fun sendBroadCastConsultationRequest(
        speciality:String,
        questionAnswerList: List<QuestionAndAnswerVO>,
        patientVO: PatientVO,
        dateTime : String,
        onSuccess: () -> Unit, onFailure: (String) -> Unit
    )

    fun sendDirectRequest(
        patientVO: PatientVO,
        doctorVO: DoctorVO,
        caseSummary: List<GeneralQuestionTemplateVO>,
        dateTime: String,
         onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkoutMedicine(prescriptionList : List<PrescriptionVO>,deliveryAddressVO: String,
                         doctorVO: DoctorVO, patientVO: PatientVO , total_price : String,
                         onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getRecentlyConsultationDoctor(
        documentId: String,
        onSuccess: (doctorVo : List<RecentlyDoctorVO>) -> Unit, onFailure: (String) -> Unit)

    fun getGeneralQuestion(onSuccess: (listGeneralQuestion : List<GeneralQuestionTemplateVO>) -> Unit, onFailure: (String) -> Unit)

    fun getSpecialQuestion(specialityName: String,
                           onSuccess: (specialQuestion : List<SpecialquestionVO>) -> Unit, onFailure: (String) -> Unit)

    fun startConsultationChatPatient(consulationChatId: String, consultationRequestVO: ConsulationRequestVO ,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    //////both Doctor and Patient////////
    fun getSpecialities(
        onSuccess: (SpecialitiesVO : List<SpecialitiesVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSpecialQuestionsBySpeciality(
        speciality : String,
        onSuccess: (specialQuestionList : List<SpecialquestionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    //fun getCaseSummary(onSuccess: (caseSummary : List<QuestionAndAnswerVO>) -> Unit, onFailure: (String) -> Unit)

    fun sendMessage(consulationChatId: String, messageVO: MessageVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun startConsultation(
            consulationId: String,
            dateTime: String,
            questionAnswerList: List<QuestionAndAnswerVO>,
            patientVO: PatientVO,
            doctorVO: DoctorVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit)

    fun getConsultationChat(
        patientid: String,
        onSuccess: (chatMessageVo :List <MessageVO>) -> Unit, onFailure: (String) -> Unit)

    fun getAllCheckMessage(
        documentid: String,
        onSuccess: (consultationChatMessage : List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit)


    fun getPrescriptionMedicine(documentId: String,onSuccess: (List<PrescriptionVO>) -> Unit,onFailure: (String) -> Unit)

    fun getAllMedicine(speciality: String,onSuccess: (List<FrequentlyMedicineVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsulationChatById(consulationId : String ,onSuccess: (List<ConsulationChatVO>) -> Unit,onFailure: (String) -> Unit)

    fun getAllChatMessage(consulationId: String ,onSuccess: (List<MessageVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsulationChatForDoctor(doctorId: String ,onSuccess: (List<ConsulationChatVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsultedPatient(doctorId : String,onSuccess: (List<ConsultatedPatientVO>) -> Unit,onFailure: (String) -> Unit)

    fun getConsulationChatByPatientId(patientId : String ,
                                      onSuccess: (List<ConsulationChatVO>) -> Unit,onFailure: (String) -> Unit)

    fun finishConsultation(consultationChatVO: ConsulationChatVO, prescriptionList : List<PrescriptionVO>, onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun saveMedicalRecord(consultationChatVO: ConsulationChatVO ,onSuccess: () -> Unit,onFailure: (String) -> Unit)

    fun getPrescription(consulationId: String ,onSuccess: (List<PrescriptionVO>) -> Unit,onFailure: (String) -> Unit)
}