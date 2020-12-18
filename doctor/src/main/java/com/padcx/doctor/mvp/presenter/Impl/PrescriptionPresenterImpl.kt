package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenter.PrescriptionPresenter
import com.padcx.doctor.mvp.view.PrescriptionView
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.FrequentlyMedicineVO
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class PrescriptionPresenterImpl: PrescriptionPresenter, AbstractBaseePresenter<PrescriptionView>() {

    private val mHealthCareModel : HealthCareModel = HealthCareModelImpl
    lateinit var mOwner: LifecycleOwner


    override fun onTapFinishConsulation(list: List<PrescriptionVO>, consultationChatVO: ConsulationChatVO) {
        mHealthCareModel.finsishConsultation(consultationChatVO,list, onSuccess = {} , onError = {})
        mView.finishConsulation()
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner =owner
    }

    override fun onTapSelectMedicine(medicineVO: FrequentlyMedicineVO) {
        mView?.displayRoutinechooseDialog(medicineVO)
    }

    override fun onTapRemoveMedicine(medicineVO: FrequentlyMedicineVO) {
        mView?.removeMedicine(medicineVO)
    }

    override fun onUiReadyForPrescription(speciality: String) {

        mHealthCareModel.getAllMedicine(speciality, onSuccess = {}, onError = {})

        mHealthCareModel.getAllMedicineFromDB()
                .observe(mOwner, Observer {
                    it?.let{
                        mView?.displayMedicineList(it)
                    }

                })
    }
}

