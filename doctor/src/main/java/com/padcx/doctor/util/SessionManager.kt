package com.padcx.doctor.util

import android.content.Context
import android.content.SharedPreferences
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/10/2020
 */
object SessionManager {
    private const val NAME = sharePreferenceDoctor
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
        get() = preferences.getBoolean(sharePreferenceDoctorLoginStatus, false)
        set(value) = preferences.edit {
            it.putBoolean(sharePreferenceDoctorLoginStatus, value)
        }

    var doctor_name: String?
        get() = preferences.getString(sharePreferenceDoctorName, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorName, value)
        }

    var doctor_email: String?
        get() = preferences.getString(sharePreferenceDoctorEmail, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorEmail, value)
        }

    var doctor_id: String?
        get() = preferences.getString(sharePreferenceDoctorID, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorID, value)
        }

    var doctor_deviceId: String?
        get() = preferences.getString(sharePreferenceDoctorDeviceID, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorDeviceID, value)
        }

    var doctor_photo: String?
        get() = preferences.getString(sharePreferenceDoctorPhoto, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorPhoto, value)
        }

    var doctor_phone: String?
        get() = preferences.getString(sharePreferenceDoctorPhone, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorPhone, value)
        }

    var doctor_specility: String?
        get() = preferences.getString(sharePreferenceDoctorSpeciality, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorSpeciality, value)
        }

    var doctor_address: String?
        get() = preferences.getString(sharePreferenceDoctorAddress, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorAddress, value)
        }

    var doctor_experience: String?
        get() = preferences.getString(sharePreferenceDoctorExperience, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorExperience, value)
        }

    var doctor_degree: String?
        get() = preferences.getString(sharePreferenceDoctorDegree, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorDegree, value)
        }

    var doctor_biography: String?
        get() = preferences.getString(sharePreferenceDoctorBiography, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorBiography, value)
        }
    var doctor_dob: String?
        get() = preferences.getString(sharePreferencePatientDateOfBirth, "")
        set(value) = preferences.edit {
            it.putString(sharePreferencePatientDateOfBirth, value)
        }
    var doctor_gender: String?
        get() = preferences.getString(sharePreferenceDoctorGENDER, "")
        set(value) = preferences.edit {
            it.putString(sharePreferenceDoctorGENDER, value)
        }

    fun addDoctorInfo(doctorVO: DoctorVO)
    {
        doctor_name = doctorVO.name
        doctor_id = doctorVO.id
        doctor_deviceId = doctorVO.deviceId
        doctor_email = doctorVO.email.toString()
        doctor_photo = doctorVO.photo.toString()
        doctor_specility = doctorVO.speciality.toString()
       // doctor_specialityname = doctorVO.specialityname.toString()
        doctor_phone = doctorVO.phone
        doctor_degree = doctorVO.degree
        doctor_biography = doctorVO.biography
        doctor_dob = doctorVO.dob
        doctor_experience = doctorVO.experience
        doctor_gender = doctorVO.gender
        doctor_address = doctorVO.address
    }



}