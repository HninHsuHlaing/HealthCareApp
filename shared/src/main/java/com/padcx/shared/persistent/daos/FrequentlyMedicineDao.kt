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
    @Query("SELECT * FROM medicine")
    fun getMedicineList() : LiveData<List<FrequentlyMedicineVO>>

    @Query("SELECT * FROM medicine WHERE id = :medicineId")
    fun getMedicineById(medicineId :String) : LiveData<FrequentlyMedicineVO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMedicine(detail: FrequentlyMedicineVO)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMedicineList(detailList: List<FrequentlyMedicineVO>)
}