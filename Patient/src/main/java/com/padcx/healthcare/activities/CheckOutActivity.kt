package com.padcx.healthcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.padcx.healthcare.R
import com.padcx.healthcare.adapters.CheckOutAdapter
import com.padcx.healthcare.adapters.ShippingAddressAdapter
import com.padcx.healthcare.dialog.CheckOutDialog
import com.padcx.healthcare.mvp.presenter.CheckOutPresenter
import com.padcx.healthcare.mvp.presenter.Impl.CheckOutPresenterImpl
import com.padcx.healthcare.mvp.view.CheckOutView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.PrescriptionVO
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.add_shipping_bottonsheet.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class CheckOutActivity :BaseActivity() , CheckOutView{

    private lateinit var mPresenter: CheckOutPresenter
    private lateinit var adapter: CheckOutAdapter
    private lateinit var shippingAdapter: ShippingAddressAdapter
    private lateinit var consultation_chat_id: String
    private lateinit var mConsultationChatVO: ConsulationChatVO
    private lateinit var prescriptionList : List<PrescriptionVO>
    private lateinit var totalPrice : String
    private lateinit var state : String
    private lateinit var township : String
    private lateinit var address: String
    private var previousPosition : Int = -1
    private lateinit var shippingList : List<String>

    companion object {
        const val PARM_CONSULTATION_CHAT_ID = "chat id"
        const val ConsultationCHAT = "ConsultationCHAT"
        fun newIntent(context: Context, consultation_chat_id: String , consultationChatVO: String) : Intent {
            val intent = Intent(context, CheckOutActivity::class.java)
            intent.putExtra(PARM_CONSULTATION_CHAT_ID, consultation_chat_id)
            intent.putExtra(ConsultationCHAT, consultationChatVO)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        consultation_chat_id = intent.getStringExtra(PARM_CONSULTATION_CHAT_ID).toString()
        var data = intent?.getStringExtra(ConsultationCHAT)
        mConsultationChatVO = Gson().fromJson(data, ConsulationChatVO::class.java)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()


    }
    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    private fun setUpActionListeners() {
        checkoutback.setOnClickListener {
            onBackPressed()
        }
        add_address.setOnClickListener {
            showBottomSheetDialog()
        }
        btn_order.setOnClickListener {

            mConsultationChatVO?.let {
                mPresenter.onTapCheckout(prescriotionList = prescriptionList,
                        deliveryAddressVO = address,
                        doctorVO = it?.doctor,
                        patientVO = it?.patient,
                        total_price = totalPrice
                )
            }

        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<CheckOutPresenterImpl, CheckOutView>()
        mPresenter.onUiReady(this, this)
        mPresenter.onUiReadyCheckout(consultation_chat_id ,this)
    }

    private fun setUpRecyclerView() {
        prescription_rct?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = CheckOutAdapter(mPresenter)
        prescription_rct?.adapter = adapter
        prescription_rct?.setHasFixedSize(false)

//        address_rc?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        shippingAdapter = ShippingAddressAdapter(mPresenter, previousPosition)
//        address_rc?.adapter = shippingAdapter
//        address_rc?.setHasFixedSize(false)
    }
    override fun displayPrescription(list: List<PrescriptionVO>) {
        if(list.isNotEmpty()) {
            prescriptionList= list
            adapter.setNewData(list.toMutableList())
            var totalamount : Int =0
            for( item in list)
            {
                totalamount += item.price.toInt()
            }
            total_amount.text = "${totalamount} ကျပ်"
            totalPrice =  "${totalamount} ကျပ်"

        }
    }
    private fun showBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.add_shipping_bottonsheet, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        view.state_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                state = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        view.township_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                township = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        view.btn_add.setOnClickListener {
            address= "${view.ed_address.text }  ၊ ${township} ၊ ${state} "
            var patientVO = SessionManager.getPatientInfo()
            patientVO.perment_address= address
            mPresenter.onTapAddShipping(patientVO)
            dialog.dismiss()
        }
        view.shipping_cancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    override fun displayShippingAddress(list: String) {
        if(list.isNotEmpty())
        {
            ed_address.setText(list)
          //  shippingList= list
           // shippingAdapter.setNewData(list)
        }else{
        }
    }

    override fun displayConfirmDialog(list: List<PrescriptionVO>, total_price: String, address: String) {
        var data=  Gson().toJson(prescriptionList)
        val dialog: CheckOutDialog = CheckOutDialog.newInstance(data,address,total_price)
        dialog.show(supportFragmentManager, "")
    }

    override fun selectedShippingAddress(maddress: String, mpreviousPostion: Int) {
        Toast.makeText(this, maddress, Toast.LENGTH_SHORT).show()
        Log.d("previous",previousPosition.toString())
        address= maddress
        btn_order.visibility = View.VISIBLE
        previousPosition = mpreviousPostion
        shippingAdapter = ShippingAddressAdapter(mPresenter, previousPosition)
        shippingList?.let {
            shippingAdapter.setNewData(shippingList.toMutableList())
          //  address_rc?.adapter = shippingAdapter
        }
    }

    override fun showError(error: String) {

    }
}