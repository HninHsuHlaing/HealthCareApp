package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.FrequentlyMedicineVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Dao
interface FrequentlyMedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicalData(data: FrequentlyMedicineVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicalDataList(list: List<FrequentlyMedicineVO>)

    @Query("select * from medicine")
    fun getAllMedicine(): LiveData<List<FrequentlyMedicineVO>>

    @Query("select * from medicine WHERE id = :id")
    fun getAllMedicineByData(id: String): LiveData<FrequentlyMedicineVO>

    @Query("DELETE FROM medicine")
    fun deleteAllMedicine()
}