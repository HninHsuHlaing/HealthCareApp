package com.padcx.shared.network

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.padcx.shared.data.vo.*
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO
import com.padcx.shared.util.*
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
object CloudFireStoreFireBaseApiImpl : FirebaseApi {

    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference


    override fun uploadPhotoToFirebaseStorage(
        image: Bitmap,
        onSuccess: (photoUrl: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            onFailure("Update Profile Failed")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d(ContentValues.TAG, "User profile updated.")
        }


        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            val imageUrl = task.result?.toString()
            imageUrl?.let {

                onSuccess(it)
            }
        }
    }

    //////Patient////////

    override fun addOrUpdatePatientData(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val patientMap = hashMapOf(
            "id" to patientVO.id,
            "name" to patientVO.name,
            "photo" to patientVO.photo,
            "dob" to patientVO.dob,
            "blood_type" to patientVO.blood_type,
            "blood_pressure" to patientVO.blood_pressure,
            "email" to patientVO.email,
            "deviceId" to patientVO.deviceId,
            "height" to patientVO.height,
            "weight" to patientVO.weight,
            "allergic_medicine" to patientVO.allergic_medicine
           // "created_date" to patientVO.created_date
        )
        patientVO?.let {
            db.collection(PATIENT)
                .document(patientVO.id.toString())
                .set(patientMap)
                .addOnSuccessListener {
                    Log.d("Success", "Successfully add patient")
                    onSuccess()
                }
                .addOnFailureListener {
                    Log.d("Failure", "Failed to register")
                    onFailure("Failed to add patient")
                }
        }
    }

    override fun getPatientByEmail(
        email: String,
        onSuccess: (patientList: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(PATIENT)
            .whereEqualTo("email",email)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val patientList: MutableList<PatientVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id)
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<PatientVO>(Data, PatientVO::class.java)
                        patientList.add(docsData)
                    }
                    onSuccess(patientList[0])
                }
            }
    }

    override fun updatePatientData(patientVO: PatientVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection(PATIENT)
                .document(patientVO.id)
                .set(patientVO)
                .addOnSuccessListener {
                    Log.d("Success", "Successfully")
                onSuccess()}
                .addOnFailureListener {
                    Log.d("Failure", "Failed ") }
    }

    override fun sendBroadCastConsultationRequest(
        speciality: String,
        questionAnswerList: List<QuestionAndAnswerVO>,
        patientVO: PatientVO,
        dateTime: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val uuid = UUID.randomUUID()
         val consultationrequestMap = hashMapOf(
//             "cr_id" to uuid.toString(),
//             "caseSummary" to questionAnswerList,
//             "patient" to patientVO,
//                 "doctor" to DoctorVO(),
//             "speciality" to speciality,
//                 "status" to "none",
//                 "d_id" to " ",
//                 "p_id" to " ",
//                 "consultation_id" to " "
                "cr_id" to uuid.toString(),
                 "patient" to patientVO,
                 "doctor" to DoctorVO(),
                 "speciality" to speciality,
                 "status" to "none",
                 "d_id" to "",
                 "p_id" to patientVO.id,
                 "caseSummary" to questionAnswerList,
                 "consultation_id" to "",
                 "date_time" to ""
         )

        db.collection(CONSULTATION_REQUEST)
                .document(uuid.toString())
                .set(consultationrequestMap)
                .addOnSuccessListener {
                    Log.d("Success", "Successfully add consultation Request")
                    onSuccess()
                }
                .addOnFailureListener {
                    Log.d("Failure", "Fail to add consultation request.")
                    onFailure("Failed to add ")
                }

        db.collection(PATIENT)
                .document(patientVO.id)
                .set(patientVO)
                .addOnSuccessListener { Log.d("Success", "Successfully") }
                .addOnFailureListener { Log.d("Failure", "Failed ") }

    }

    override fun sendDirectRequest(patientVO: PatientVO, doctorVO: DoctorVO,
                                   caseSummary: List<GeneralQuestionTemplateVO>,
                                   dateTime: String, onSuccess: () -> Unit,
                                   onFailure: (String) -> Unit) {


    }


    override fun checkoutMedicine(
            prescriptionList: List<PrescriptionVO>,
            deliveryAddressVO: String,
            doctorVO: DoctorVO,
            patientVO: PatientVO,
            total_price: String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        val deliveryRoutineVO = DeliveryRoutineVO(
            "", DateUtil().getDaysAgo(3).toString()
        )
        val uuid = UUID.randomUUID().toString()
        val checkoutMap = hashMapOf(
            "id" to uuid,
            "address" to deliveryAddressVO,
            "patient" to patientVO,
            "doctor" to doctorVO,
            "delivery_routine" to deliveryRoutineVO,
            "prescription" to prescriptionList,
            "total_price" to total_price
        )
        db.collection(CHECKOUT_MEDICINE)
            .document(uuid.toString())
            .set(checkoutMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?:"Please check internet connection.")
            }
    }

    override fun getRecentlyConsultationDoctor(
            documentId: String,
            onSuccess: (doctorVo: List<RecentlyDoctorVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("$PATIENT/$documentId/$RECENTLY_DOCTOR")
        //db.collection("patient/${documentId}/recently_doctor")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message?: "Please Check Connection")
                }?: run{
                    var doctorVo : MutableList<RecentlyDoctorVO> = arrayListOf()
                    val result = value?.documents?: arrayListOf()
                    for (document in result){
                        val hashMap = document.data
                        hashMap?.put("id",document.id.toString())
                        val  Data = Gson().toJson(hashMap)
                        val docsData = Gson().fromJson<RecentlyDoctorVO>(Data, RecentlyDoctorVO::class.java)
                        doctorVo.add(docsData)
                    }
                    onSuccess(doctorVo)
                }
            }
    }

    override fun getGeneralQuestion(
        onSuccess: (listGeneralQuestion: List<GeneralQuestionTemplateVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(GENERAL_QUESTION_TEMPLATE)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message?:"Please Check Connection")
                }?: run {
                    val generalQuestion :MutableList<GeneralQuestionTemplateVO> = arrayListOf()
                    val result = value?.documents?: arrayListOf()
                    for(document in result){
                        val hashMap = document.data
                        hashMap?.put("id", document.id.toString())
                        val Data  = Gson().toJson(hashMap)
                        val docsData = Gson().fromJson<GeneralQuestionTemplateVO>(Data,GeneralQuestionTemplateVO::class.java)
                        generalQuestion.add(docsData)
                    }
                    onSuccess(generalQuestion)
                }

            }
    }

    override fun getSpecialQuestion(
            specialityName: String,
            onSuccess: (specialQuestion: List<SpecialquestionVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("$SPECILITIES/$specialityName/$SPECIAL_QUESTION")
       // db.collection("specialities/${specilityName}/special_questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message?:"Please Check Connection.")
                }?: run {
                    val specialQuestion : MutableList<SpecialquestionVO> = arrayListOf()
                    val result  = value?.documents?: arrayListOf()
                    for (document in result){
                        val hashMap = document.data
                        hashMap?.put("id",document.id)
                        val Data = Gson().toJson(hashMap)
                        val docsData = Gson().fromJson<SpecialquestionVO>(Data,SpecialquestionVO::class.java)
                        specialQuestion.add(docsData)
                    }
                    onSuccess(specialQuestion)
                }
            }
    }

    override fun startConsultationChatPatient(
        consulationChatId: String,
        consultationRequestVO: ConsulationRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val consultationRequestMap = hashMapOf(
            "status" to "complete",
            "speciality" to consultationRequestVO.doctor.speciality,
            "caseSummary" to consultationRequestVO.caseSummary,
            "patient" to consultationRequestVO.patient,
            "doctor" to consultationRequestVO.doctor,
            "cr_id" to consultationRequestVO.cr_id,
            "date_time" to consultationRequestVO.date_time,
                "consultation_id" to consulationChatId,
                "d_id" to consultationRequestVO.d_id,
                "p_id" to consultationRequestVO.p_id
        )
        db.collection(CONSULTATION_REQUEST)
            .document(consultationRequestVO.cr_id)
            .set(consultationRequestMap)
            .addOnSuccessListener { Log.d("Success", "Successfully ")  }
            .addOnFailureListener { Log.d("Failure", "Failed")  }
    }


    /////Doctor//////////
    override fun addOrUpdateDoctorData(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val doctorMap = hashMapOf(
                "id" to doctorVO.id,
            "name" to doctorVO.name,
            "photo" to doctorVO.photo,
            "biography" to doctorVO.biography,
            "degree" to doctorVO.degree,
            "experience" to doctorVO.experience,
            "address" to doctorVO.address,
            "email" to doctorVO.email,
            "speciality" to doctorVO.speciality,
                "gender" to doctorVO.gender,
                "dob" to doctorVO.dob,
                "deviceId" to doctorVO.deviceId,
                "phone" to doctorVO.phone
        )
        doctorVO.name?.let {
            db.collection(DOCTOR)
                .document(doctorVO?.phone.toString())
                .set(doctorMap)
                .addOnSuccessListener { Log.d("Success", "Successfully") }
                .addOnFailureListener { Log.d("Failure", "Failed") }
        }

    }


    override fun getDoctorByEmail(
        email: String,
        onSuccess: (doctorList: DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(DOCTOR)
            .whereEqualTo("email", email)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val doctorList: MutableList<DoctorVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<DoctorVO>(Data, DoctorVO::class.java)
                        doctorList.add(docsData)
                    }
                    onSuccess(doctorList[0])
                }
            }

    }

    override fun getBroadCastConsultation(
        documentId: String,
        onSuccess: (consultationrequestVo: ConsulationRequestVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(CONSULTATION_REQUEST)
                .whereEqualTo("cr_id",documentId)
       // db.collection("$CONSULTATION_REQUEST/$documentId")
      //  db.collection("consultation_request/${documentId}")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check Connection")
                } ?: run {
                    var consultationrequestVo: ConsulationRequestVO = ConsulationRequestVO()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<ConsulationRequestVO>(
                            Data,
                            ConsulationRequestVO::class.java
                        )
                        consultationrequestVo = docsData
                    }
                    onSuccess(consultationrequestVo)

                }
            }
    }

    override fun getBroadCastConsultationBySpeciality(
        speciality: String,
        onSuccess: (list: List<ConsulationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(CONSULTATION_REQUEST)
            .whereEqualTo("speciality", speciality)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check Connection")
                } ?: run {
                    var consultationrequestVo: MutableList<ConsulationRequestVO>  = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("cr_id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<ConsulationRequestVO>(
                            Data,
                            ConsulationRequestVO::class.java
                        )
                        consultationrequestVo.add(docsData)
                    }
                    onSuccess(consultationrequestVo)

                }
            }
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
        val consultationRequestMap = hashMapOf(

               "cr_id" to cr_id,
                "status" to status,
                "d_id" to doctorId,
                "p_id" to patientId,
                "doctor" to doctorVO,
                "speciality" to doctorVO.speciality,
                "patient" to patientVO,
                "caseSummary" to questionAnswerList,
                "consultation_id" to  consulationId,
                "date_time" to "134"
        )
        db.collection(CONSULTATION_REQUEST)
            .document(consulationId)
            .set(consultationRequestMap)
            .addOnSuccessListener {
                onSuccess()
                Log.d("Success", "Successfully ") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
    }

    override fun getBroadcastConsultationRequestByPatient(
        patientId: String,
        onSuccess: (consulationRequest: List<ConsulationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(CONSULTATION_REQUEST)
           // .whereEqualTo("id",patientId)
            .whereEqualTo("p_id",patientId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val list: MutableList<ConsulationRequestVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<ConsulationRequestVO>(Data, ConsulationRequestVO::class.java)
                        list.add(docsData)
                    }
                    onSuccess(list)
                }
            }
    }

//    override fun finishConsultation() {
//
//    }

    override fun preSubscribeMedicine(
            documentid: String,
            medicineVO: PrescriptionVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        val uuid = UUID.randomUUID()
        val prescriptionMedicineMap = hashMapOf(
            "id" to uuid.toString(),
            "medicine_name" to medicineVO.medicine_name,
            "routine" to medicineVO.routineVO
        )
        db.collection(CONSULTATION_CHAT)
            .document(documentid)
            .set(prescriptionMedicineMap)
            .addOnSuccessListener {
                Log.d("Success", "Successfully add prescription Medicine")
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?: "Check internet Connection")
            }
    }



/////////Both///////////

    override fun getSpecialities(
        onSuccess: (SpecialitiesVO: List<SpecialitiesVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
            db.collection(SPECILITIES)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message?: " Please Check Connection")
                    }?: run{
                        val specialitiesList : MutableList<SpecialitiesVO> = arrayListOf()
                        val result = value?.documents?: arrayListOf()
                        for(document in result){
                            val hashMap = document.data
                            hashMap?.put("id", document.id)
                            val Data = Gson().toJson(hashMap)
                            val docsData = Gson().fromJson<SpecialitiesVO>(Data, SpecialitiesVO::class.java)
                            specialitiesList.add(docsData)
                        }
                        onSuccess(specialitiesList)
                    }
                }
    }

    override fun getSpecialQuestionsBySpeciality(
        speciality: String,
        onSuccess: (specialQuestionList: List<SpecialquestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$SPECILITIES/$speciality/$SPECIAL_QUESTION")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val specialQuestionList: MutableList<SpecialquestionVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<SpecialquestionVO>(Data, SpecialquestionVO::class.java)
                        specialQuestionList.add(docsData)
                    }
                    onSuccess(specialQuestionList)
                }
            }
    }

//    override fun getCaseSummary(
//        onSuccess: (caseSummary: List<QuestionAndAnswerVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        db.collection("consultation_chat")
//            .addSnapshotListener { value, error ->
//                error?.let {
//                    onFailure(it.message?:"Please Check Connection")
//                }?: run{
//                    val caseSummary : List<QuestionAndAnswerVO> = arrayListOf()
//                    val result = value?.documents?: arrayListOf()
//                    for(document in result){
//                        val hashMap = document.data
//                        hashMap?.put("id", document.id)
//                        val Data = Gson().toJson(hashMap)
//                        val docsData = Gson().fromJson<ConsulationChatVO
//                                >(Data,ConsulationChatVO::class.java::class.java)
//                        caseSummary
//                    }
//                }
//            }
//    }

    override fun sendMessage(
            consulationChatId: String,
            messageVO: MessageVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        val uuid = UUID.randomUUID()
        val chatmessageMap = hashMapOf(
            "id" to uuid.toString(),
            "messageText" to messageVO.messageText,
            "messageImage" to messageVO.messageImage,
            "sentBy" to messageVO.sendBy,
            "sendAt" to messageVO.sendAt,
                "type" to messageVO.type

        )

        db.collection("$CONSULTATION_CHAT/$consulationChatId/$MESSAGE")
       // db.collection("consultation_chat/${documentid}/chat_message")
            .document(uuid.toString())
            .set(chatmessageMap)
            .addOnSuccessListener {
                Log.d("Success", "Successfully add the chat message")
                onSuccess()
            }
            .addOnFailureListener {
              //  Log.d("Failuer", "Fail to add the chat message.")
                onFailure("Failed to add the chat message")
            }
    }

    override fun startConsultation(
            consulationId: String,
            dateTime: String,
            questionAnswerList: List<QuestionAndAnswerVO>,
            patientVO: PatientVO,
            doctorVO: DoctorVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        val uuid = UUID.randomUUID()
        val startMessageMap = hashMapOf(
             "id" to consulationId,
            "patient" to patientVO,
            "doctor" to doctorVO,
                "patient_id" to patientVO.id,
                "doctor_id" to doctorVO.id,
                "start_consultation_date" to DateUtil().getCurrentHourMinAMPM(),
            "caseSummary" to questionAnswerList,
                "status" to false
        )
        db.collection(CONSULTATION_CHAT)
            .document(consulationId)
            .set(startMessageMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure("Please Check Connection")
            }


        db.collection("$PATIENT/${patientVO.id}/$RECENTLY_DOCTOR")
       // db.collection("patients/${patientVO.id}/recentDoctor")
            .document(doctorVO.id)
            .set(doctorVO)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure("Please Check Connection")
            }


        for(QandAnswer in questionAnswerList){
            db.collection("$PATIENT/${patientVO.id}/$GENERAL_QUESTION")
          //  db.collection("patients/${patientVO.id}/caseSummary")
                .document(QandAnswer.id)
                .set(QandAnswer)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onFailure("please check Connection")
                }
        }
    }

    override fun getConsultationChat(
        patientid: String,
        onSuccess: (chatMessageVo: List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$CONSULTATION_CHAT/${patientid}/$MESSAGE")
      //  db.collection("consultation_chat/${patientid}/message")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message?:"Please Check Connection")
                }?:run {
                    val chatMessage : MutableList<MessageVO> = arrayListOf()
                    val result = value?.documents?: arrayListOf()
                    for(document in result){
                        val hashMap = document.data
                        val Data = Gson().toJson(hashMap)
                        val docsData = Gson().fromJson<MessageVO>(Data,MessageVO::class.java)
                        chatMessage.add(docsData)
                    }
                    onSuccess(chatMessage)
                }
            }
    }

    override fun getAllCheckMessage(
        documentid: String,
        onSuccess: (consultationChatMessage: List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$CONSULTATION_CHAT/$documentid/$MESSAGE")
      //  db.collection("consultation_chat/${documentid}/chat_message")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message?:"Please  Check Connection")
                }?: run {
                    val consultationChatMessage : MutableList<MessageVO> = arrayListOf()
                    val result = value?.documents?: arrayListOf()
                    for (document in result){
                        val hashMap = document.data
                        val Data = Gson().toJson(hashMap)
                        val docsData = Gson().fromJson<MessageVO>(Data,MessageVO::class.java)
                        consultationChatMessage.add(docsData)
                    }
                    onSuccess(consultationChatMessage)
                }
            }
    }

    override fun getPrescriptionMedicine(
        documentId: String,
        onSuccess: (List<PrescriptionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$CONSULTATION_CHAT/$documentId/$MESSAGE")
       // db.collection("consultation_chat/${documentId}/message")
            .get()
            .addOnSuccessListener { result ->
                val prescriptionList: MutableList<PrescriptionVO> = arrayListOf()
                for (document in result) {
                    val hashmap = document.data
                    hashmap?.put("id", document.id)
                    val Data = Gson().toJson(hashmap)
                    val docData = Gson().fromJson<PrescriptionVO>(Data, PrescriptionVO::class.java)
                    prescriptionList.add(docData)
                }
                onSuccess(prescriptionList)

            }.addOnFailureListener {
                onFailure(it.message ?: "Please check internet connection")
            }
    }

    override fun getAllMedicine(
        speciality: String,
        onSuccess: (List<FrequentlyMedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$SPECILITIES/$speciality/$FREQUENTLY_MEDICINE")
       // db.collection("specialities/${speciality}/frequently_medicine")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please Check internet connection")
                } ?: run {
                    val medicineList: MutableList<FrequentlyMedicineVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id)
                        val Data = Gson().toJson(hashmap)
                        val docData = Gson().fromJson<FrequentlyMedicineVO>(
                            Data,
                            FrequentlyMedicineVO::class.java
                        )
                        medicineList.add(docData)
                    }
                    onSuccess(medicineList)
                }
            }

    }

    override fun getConsulationChatById(consulationId: String, onSuccess: (List<ConsulationChatVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection(CONSULTATION_CHAT)
                .whereEqualTo("id",consulationId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message?:"Please Check internet connection")
                    }?:run {
                        val list: MutableList<ConsulationChatVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()
                        for (document in result) {
                            val hashmap = document.data
                            hashmap?.put("id", document.id.toString())
                            val Data = Gson().toJson(hashmap)
                            val docsData = Gson().fromJson<ConsulationChatVO>(Data, ConsulationChatVO::class.java)
                            list.add(docsData)
                        }
                        onSuccess(list)
                    }
                }
    }

    override fun getAllChatMessage(consulationId: String, onSuccess: (List<MessageVO>) -> Unit, onFailure: (String) -> Unit) {

        db.collection("$CONSULTATION_CHAT/$consulationId/$MESSAGE")
                .orderBy("sendAt")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: " Please Check internet connection")
                    } ?: run {
                        val list: MutableList<MessageVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val hashmap = document.data
                            hashmap?.put("id", document.id.toString())
                            val Data = Gson().toJson(hashmap)
                            val docsData = Gson().fromJson<MessageVO>(Data, MessageVO::class.java)
                            list.add(docsData)
                        }
                        onSuccess(list)
                    }
                }
    }

    override fun getConsulationChatForDoctor(doctorId: String, onSuccess: (List<ConsulationChatVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("$CONSULTATION_CHAT")
                .whereEqualTo("doctor_id",doctorId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val list: MutableList<ConsulationChatVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val hashmap = document.data
                            hashmap?.put("id", document.id.toString())
                            val Data = Gson().toJson(hashmap)
                            val docsData = Gson().fromJson<ConsulationChatVO>(Data, ConsulationChatVO::class.java)
                            list.add(docsData)
                        }
                        onSuccess(list)
                    }
                }
    }

    override fun getConsultedPatient(doctorId: String, onSuccess: (List<ConsultatedPatientVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("$DOCTOR/$doctorId/$CONSULTATED_PATIENT")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val list: MutableList<ConsultatedPatientVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val hashmap = document.data
                            hashmap?.put("id", document.id.toString())
                            val Data = Gson().toJson(hashmap)
                            val docsData = Gson().fromJson<ConsultatedPatientVO>(Data, ConsultatedPatientVO::class.java)
                            list.add(docsData)
                        }
                        onSuccess(list)
                    }
                }
    }

    override fun getConsulationChatByPatientId(patientId: String, onSuccess: (List<ConsulationChatVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("$CONSULTATION_CHAT")
                .whereEqualTo("patient_id",patientId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val list: MutableList<ConsulationChatVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val hashmap = document.data
                            hashmap?.put("id", document.id.toString())
                            val Data = Gson().toJson(hashmap)
                            val docsData = Gson().fromJson<ConsulationChatVO>(Data, ConsulationChatVO::class.java)
                            list.add(docsData)
                        }
                        onSuccess(list)
                    }
                }
    }

    override fun finishConsultation(consultationChatVO: ConsulationChatVO, prescriptionList: List<PrescriptionVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        consultationChatVO.doctor?.let {

            db.collection("$PATIENT/${consultationChatVO.patient_id}/$RECENTLY_DOCTOR")
                    .document(consultationChatVO.doctor_id.toString())
                    .set(it)
                    .addOnSuccessListener { Log.d("Success", "Successfully ") }
                    .addOnFailureListener { Log.d("Failure", "Failed") }
        }

        val consultationChatMap = hashMapOf(
                "status" to true,
                "id" to consultationChatVO.id,
                "patient_id" to consultationChatVO.patient_id,
                "doctor_id" to consultationChatVO.doctor_id,
                "caseSummary" to consultationChatVO.caseSummary,
                "patient" to consultationChatVO.patient,
                "start_consultation_date" to consultationChatVO.start_consultation_date,
                "medical_record" to consultationChatVO.medical_record,
                "doctor" to consultationChatVO.doctor)

        db.collection("$CONSULTATION_CHAT")
                .document(consultationChatVO.id)
                .set(consultationChatMap)
                .addOnSuccessListener { Log.d("Success", "Successfully ") }
                .addOnFailureListener { Log.d("Failure", "Failed") }

        for(item in prescriptionList) {
            db.collection("$CONSULTATION_CHAT/${consultationChatVO.id}/$PRESCRIPTION")
                    .document(item.id)
                    .set(item)
                    .addOnSuccessListener { Log.d("Success", "Successfully ") }
                    .addOnFailureListener { Log.d("Failure", "Failed") }
        }
    }

    override fun saveMedicalRecord(consultationChatVO: ConsulationChatVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection(CONSULTATION_CHAT)
                .document(consultationChatVO.id)
                .set(consultationChatVO)
                .addOnSuccessListener { Log.d("Success", "Successfully ") }
                .addOnFailureListener { Log.d("Failure", "Failed") }
    }

    override fun getPrescription(consulationId: String, onSuccess: (List<PrescriptionVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("$CONSULTATION_CHAT/$consulationId/$PRESCRIPTION")
                .get()
                .addOnSuccessListener { result ->
                    val list: MutableList<PrescriptionVO> = arrayListOf()
                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id.toString())
                        val Data = Gson().toJson(hashmap)
                        val docsData = Gson().fromJson<PrescriptionVO>(Data, PrescriptionVO::class.java)
                        list.add(docsData)
                    }
                    onSuccess(list)

                }
    }


}