package com.padcx.shared.persistent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcx.shared.data.vo.*
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO
import com.padcx.shared.persistent.daos.*
import com.padcx.shared.util.DATABASE_NAME

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
@Database(
    entities = [PatientVO::class, DoctorVO::class,FrequentlyMedicineVO::class,
    ConsulationChatVO::class,ConsulationRequestVO::class,GeneralQuestionTemplateVO::class,
    SpecialitiesVO::class,SpecialquestionVO::class,PrescriptionVO::class,CheckoutVO::class,
    MessageVO::class,RecentlyDoctorVO::class,ConsultatedPatientVO::class
        ],
    version = 26,
    exportSchema = false
)
abstract class HealthCareDatabase : RoomDatabase(){
    companion object {

        var dbInstance: HealthCareDatabase? = null

        fun getDBInstance(context: Context): HealthCareDatabase {
            when (dbInstance) {
                null -> {
                    dbInstance =
                        Room.databaseBuilder(context, HealthCareDatabase::class.java, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun doctorDao(): DoctorDao
    abstract fun patientDao(): PatientDao
    abstract fun ConsultationChatDao(): ConsultationChatDao
    abstract fun ConsultationRequestDao(): ConsultationRequestDao
    abstract fun FrequentlyMedicineDao(): FrequentlyMedicineDao
    abstract fun GeneralQuestionDao(): GeneralQuestionDao
    abstract fun RecentDoctorDao(): RecentDoctorDao
    abstract fun SpecialQuestionDao(): SpecialQuestionDao
    abstract fun SpecilitiesDao(): SpecilitiesDao
    abstract fun ChatMessageDao() : ChatMessageDao
    abstract fun ConsultatedPatientDao() : ConsultatedPatientDao
    abstract fun PrescriptionDao() : PrescriptionDao
}