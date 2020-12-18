package com.padcx.healthcare.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.padcx.healthcare.R
import com.padcx.healthcare.adapters.CheckOutAdapter
import com.padcx.healthcare.mvp.presenter.CheckOutPresenter
import com.padcx.healthcare.mvp.presenter.Impl.CheckOutPresenterImpl
import com.padcx.healthcare.mvp.view.CheckOutView
import com.padcx.shared.activities.BaseeDialogFragment
import com.padcx.shared.data.vo.PrescriptionVO
import kotlinx.android.synthetic.main.checkout_dialog.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class CheckOutDialog : BaseeDialogFragment()  {

    private lateinit var mPresenter: CheckOutPresenter

    companion object {

        private const val KEY_prescripitonList = "prescripitonList"
        private const val KEY_SHIPPING_ADDRESS = "address"
        private const val KEY_TOTAL = "total"


        fun newInstance(prescripitonList: String, shippingAddress: String,total_price: String): CheckOutDialog {
            val args = Bundle()

            args.putString(KEY_prescripitonList, prescripitonList)
            args.putString(KEY_SHIPPING_ADDRESS, shippingAddress)
            args.putString(KEY_TOTAL, total_price)

            val fragment = CheckOutDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.checkout_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.apply {
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun setupView(view: View) {

        view.fulladdress.text =  arguments?.getString(KEY_SHIPPING_ADDRESS)
        view.total_amounts.text =  arguments?.getString(KEY_TOTAL)
        view.subtotal.text =  arguments?.getString(KEY_TOTAL)

        view.prescription_rcy?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mPresenter = getPresenter<CheckOutPresenterImpl, CheckOutView>()
        var adapter = CheckOutAdapter(mPresenter )
        view.prescription_rcy?.adapter = adapter
        view.prescription_rcy?.setHasFixedSize(false)

        var data = arguments?.getString(KEY_prescripitonList)
        val gson = Gson()
        var list = gson.fromJson(data, Array<PrescriptionVO>::class.java).toList()
        adapter.setNewData(list.toMutableList())

    }

    private fun setupClickListeners(view: View) {
        view.btn_payment.setOnClickListener {
            Toast.makeText(view.context,"ဆေးညွန်းမှာယူ ခြင်း အောင်မြင်ပါသည်", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

}