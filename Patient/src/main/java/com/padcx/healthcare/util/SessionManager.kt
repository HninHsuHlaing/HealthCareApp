package com.padcx.healthcare.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.util.*
import java.sql.Timestamp

/**
 * Created by Hnin Hsu Hlaing
 * on 12/10/2020
 */
object SessionManager {

    private val NAME = sharePreferencePatient
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var login_status: Boolean

        get() = preferences.getBoolean(sharePreferenceLoginStatus, false)

        set(value) = preferences.edit {
            it.putBoolean(sharePreferenceLoginStatus, value)
        }

    var patient_name: String?

        get() = preferences.getString(sharePreferencePatientName, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientName, value)
        }

    var patient_email: String?

        get() = preferences.getString(sharePreferencePatientEmail, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientEmail, value)
        }
    var patient_id: String?

        get() = preferences.getString(sharePreferencePatientID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientID, value)
        }

    var patient_deviceId: String?

        get() = preferences.getString(sharePreferencePatientDeviceID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientDeviceID, value)
        }

    var patient_dob: String?

        get() = preferences.getString(sharePreferencePatientDateOfBirth, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientDateOfBirth, value)
        }

    var patient_height: String?

        get() = preferences.getString(sharePreferencePatientHeight, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientHeight, value)
        }

    var patient_bloodType: String?

        get() = preferences.getString(sharePreferencePatientBloodType, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBloodType, value)
        }

    var patient_allergicMedicine: String?

        get() = preferences.getString(sharePreferencePatientAllergicMedicine, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientAllergicMedicine, value)
        }

    var patient_weight: String?

        get() = preferences.getString(sharePreferencePatientBodyWeight, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBodyWeight, value)
        }

    var patient_bloodPressure: String?

        get() = preferences.getString(sharePreferencePatientBloodPressure, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientBloodPressure, value)
        }

    var patient_photo: String?

        get() = preferences.getString(sharePreferencePatientPhoto, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientPhoto, value)
        }

    var patient_createDate: String?

        get() = preferences.getString(sharePreferencePatientCreateDate, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientCreateDate, value)
        }
    var patient_address : String?

        get() = preferences.getString(sharePreferencePatientAddress, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientAddress, value)
        }
    var patient_perment_address : String?

        get() = preferences.getString(sharePreferencePatientPERMMENTAddress, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientPERMMENTAddress, value)
        }
    fun getPatientInfo() : PatientVO
    {
        val gson = Gson()
      //  var addressList = gson.fromJson(SessionManager.patient_perment_address, Array<String>::class.java).toMutableList()

        return  PatientVO(
                id = SessionManager.patient_id.toString(),
                deviceId = SessionManager.patient_deviceId.toString(),
                name =  SessionManager.patient_name.toString(),
                email  = SessionManager.patient_email.toString(),
                photo = SessionManager.patient_photo.toString(),
                blood_type =SessionManager.patient_bloodType.toString(),
                blood_pressure=  SessionManager.patient_bloodPressure.toString(),
                dob =  SessionManager.patient_dob.toString(),
                weight =  SessionManager.patient_weight.toString(),
                height =  SessionManager.patient_height.toString(),
                allergic_medicine =  SessionManager.patient_allergicMedicine.toString(),
              //  phone =  SessionManager.patient_phone.toString(),
                perment_address=  SessionManager.patient_perment_address.toString()
              //  address = addressList as ArrayList<String>
        )
    }

    fun addPatientInfo( patientVO: PatientVO)
    {
        patient_name = patientVO.name
        patient_id = patientVO.id
        patient_deviceId = patientVO.deviceId
        patient_email = patientVO.email
        patient_photo = patientVO.photo.toString()
        patient_dob =patientVO.dob
        patient_height = patientVO.height
        patient_bloodType = patientVO.blood_type
        patient_allergicMedicine = patientVO.allergic_medicine
        patient_weight = patientVO.weight
        patient_bloodPressure = patientVO.blood_pressure
      //  patient_address =  Gson().toJson(patientVO.address)
       // patient_phone = patientVO.phone
//        if(patientVO.address.size>0) {
//            for(item in patientVO.address) {
//                if(item.default_address ==true) {
//                    patient_address = item.address
//                }
//            }
//        }
    }
}