package com.padcx.doctor.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.padcx.doctor.R
import com.padcx.doctor.adapters.MedicalAdapter
import com.padcx.doctor.mvp.presenter.Impl.PrescriptionPresenterImpl
import com.padcx.doctor.mvp.view.PrescriptionView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.FrequentlyMedicineVO
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.data.vo.RoutineVO
import kotlinx.android.synthetic.main.activity_prescription.*
import kotlinx.android.synthetic.main.routine_dialog.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class PrescriptionActivity:BaseActivity(), PrescriptionView {
    private lateinit var mPresenter: PrescriptionPresenterImpl
    private lateinit var adapter: MedicalAdapter
    private lateinit var speciality : String
    private lateinit var list : List<FrequentlyMedicineVO>
    private  var filterlist : ArrayList<FrequentlyMedicineVO> = arrayListOf()
    private  var prescriptionList : ArrayList<PrescriptionVO> = arrayListOf()

    private lateinit var mConsultationChatVO: ConsulationChatVO
    companion object {
        const val PARM_SPECIALITY = "speciality"
        private const val ConsultationCHAT = "ConsultationCHAT"

        fun newIntent(context: Context, speciality : String, consultationChatVO: String) : Intent {
            val intent = Intent(context, PrescriptionActivity::class.java)
            intent.putExtra(PARM_SPECIALITY, speciality)
            intent.putExtra(ConsultationCHAT, consultationChatVO)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
        var data = intent?.getStringExtra(ConsultationCHAT)
      //  Log.d("Consultation Chat", data.toString())
        mConsultationChatVO = Gson().fromJson(data, ConsulationChatVO::class.java)

    }

    private fun setUpActionListeners() {
        search_medicine.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

        tex_back.setOnClickListener {
            onBackPressed()
        }

        btn_finish_consultation.setOnClickListener {
            if(prescriptionList.size>0) {
                mPresenter.onTapFinishConsulation(prescriptionList, mConsultationChatVO)
            }
        }
    }

    private fun setUpRecyclerView() {
        medical_recyclerview?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MedicalAdapter(mPresenter)
        medical_recyclerview?.adapter = adapter
        medical_recyclerview?.setHasFixedSize(false)
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<PrescriptionPresenterImpl, PrescriptionView>()
        mPresenter.onUiReady(this,this)
        speciality = intent.getStringExtra(PARM_SPECIALITY).toString()
        speciality?.let {
            mPresenter.onUiReadyForPrescription(speciality)
        }
    }

    override fun displayMedicineList(list: List<FrequentlyMedicineVO>) {
        this.list = list
        adapter.setNewData(list.toMutableList())
    }

    override fun displayRoutinechooseDialog(medicineVO: FrequentlyMedicineVO) {
        showMedicineDialog(medicineVO)
    }

    override fun removeMedicine(medicineVO: FrequentlyMedicineVO) {
        var index=0
        for(item in prescriptionList)
        {
            if(item.medicine_name == medicineVO.name)
            {
                prescriptionList.removeAt(index)
            }
            index++
        }
    }

    override fun finishConsulation() {
        onBackPressed()
//        mConsultationChatVO?.let {
//            startActivity(ChatRoomActivity.newIntent(this,consultation_chat_id = mConsultationChatVO.id.toString()))
//        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        var data = intent?.getStringExtra(ConsultationCHAT)
//        Log.d("Consultation Chat", data.toString())
//        mConsultationChatVO = Gson().fromJson(data, ConsulationChatVO::class.java)
        mConsultationChatVO?.let{
            startActivity(ChatRoomActivity.newIntent(this,consultation_chat_id = mConsultationChatVO.id))
        }
        this.finish()
    }

    override fun showError(error: String) {

    }
    fun filter(text : String)
    {
        filterlist.clear()
        list?.let{

            for( item in list)
            {
                var st = item.name.toString().toLowerCase()
                if(st.contains(text))
                {
                    filterlist.add(item)
                }
            }
            adapter.setMedicineList(filterlist)
        }

    }
    private fun showMedicineDialog(medicineVO: FrequentlyMedicineVO)
    {
        var morningstatus =true
        var afternoonstatus =true
        var nightstatus =true

        var number =1
        var daycount : Int = 0
        var tabcount : String = "1"
        var eatingtime : String =""
        var daystemp : String =""
        var count =0

        val view = layoutInflater.inflate(R.layout.routine_dialog, null)
        val txt_tabcount = view?.findViewById<TextView>(R.id.txt_tabcount)
        val pt_comment = view?.findViewById<EditText>(R.id.pt_comment)

        val dialog = this?.let { Dialog(it) }

        dialog?.apply {
            setCancelable(false)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.txt_medicine_name.text  =  medicineVO.name
        txt_tabcount?.text =  tabcount

        view.morning.setOnClickListener {

            morningstatus = if(morningstatus) {
                view.morning.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
                view.morning.setTextColor(Color.WHITE)
                count++
                false
            }else{
                view.morning.setBackgroundResource(R.drawable.bg_rounded_border_grey)
                view.morning.setTextColor(Color.BLACK)
                count--
                true
            }

            if(count > -1)
            {
                var result = number * daycount * count
                txt_tabcount?.text = result.toString()
                tabcount = result.toString()
            }
        }

        view.afternoon.setOnClickListener {
            afternoonstatus = if(afternoonstatus) {
                view.afternoon.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
                view.afternoon.setTextColor(Color.WHITE)
                count++
                false
            }else{
                view.afternoon.setBackgroundResource(R.drawable.bg_rounded_border_grey)
                view.afternoon.setTextColor(Color.BLACK)
                count--
                true
            }
            if(count > -1)
            {
                var result = number * daycount * count
                txt_tabcount?.text = result.toString()
                tabcount = result.toString()
            }
        }

        view.night.setOnClickListener {
            nightstatus = if(nightstatus) {
                view.night.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
                view.night.setTextColor(Color.WHITE)
                count++
                false
            }else{
                view.night.setBackgroundResource(R.drawable.bg_rounded_border_grey)
                view.night.setTextColor(Color.BLACK)
                count--
                true
            }
            if(count > -1)
            {
                var result = number * daycount * count
                txt_tabcount?.text = result.toString()
                tabcount = result.toString()
            }
        }

        view.before_eating.setOnClickListener {
            view.before_eating.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
            view.before_eating.setTextColor(Color.WHITE)
            view.after_eating.setBackgroundResource(R.drawable.bg_rounded_border_grey)
            view.after_eating.setTextColor(Color.BLACK)
            eatingtime= "အစားမစားမှီသောက်ရန်"
        }

        view.after_eating.setOnClickListener {
            view.after_eating.setBackgroundResource(R.drawable.rounded_corner_bluecolor)
            view.after_eating.setTextColor(Color.WHITE)
            view.before_eating.setBackgroundResource(R.drawable.bg_rounded_border_grey)
            view.before_eating.setTextColor(Color.BLACK)
            eatingtime = "အစားစားပြီးမှ သောက်ရန်"
        }

        view.day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                var day = parent.getItemAtPosition(position).toString()
                if(day == "Days")
                {
                    daycount =1
                    daystemp= " Days"
                }else{
                    daycount = 7
                    daystemp=" Week"
                }

                var result = number * daycount * count
                txt_tabcount?.text = result.toString()
                tabcount = result.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        view.ed_day.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                var data= s.toString()
                if(data.isNotEmpty())
                {
                    number = data.toInt()
                    var result = number * daycount * count
                    view.txt_tabcount.text = result.toString()
                    tabcount = result.toString()
                }
            }
        })


        view.confirm.setOnClickListener {
            // prescription list add
            var days : String =""
            if(morningstatus)
            {
                days += " မနက် ၊ "
            }
            if(afternoonstatus)
            {
                days += "နေ့  ၊  "
            }
            if(nightstatus)
            {
                days += "ည"
            }

            var medicaltime : String =""

            var routineVO = RoutineVO(
                    id= "0",
                    amount = medicineVO.price.toString(),
                    days = view.ed_day.text.toString() + daystemp,
                    comment = pt_comment?.text.toString(),
                    quantity = tabcount,
                    times = days,
                    repeat = eatingtime

            )

            var prescriptionVO = PrescriptionVO(
                    id = UUID.randomUUID().toString(),
                    count = tabcount,
                    medicine_name = medicineVO.name.toString(),
                    price =  medicineVO.price.toString(),
                    routineVO= routineVO
            )
            if(pt_comment?.text.toString().isNotEmpty()) {
                prescriptionList.add(prescriptionVO)
                dialog?.dismiss()
            }else{
                Toast.makeText(this,"အချက်အလက် အားလုံး ပြည့်စုံအောင် ဖြည့်စွက်ပေးရန် လိုနေပါသေး သည်", Toast.LENGTH_SHORT).show()
            }
        }

        dialog?.show()
    }
}