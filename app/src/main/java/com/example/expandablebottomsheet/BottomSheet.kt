package com.example.expandablebottomsheet

import android.app.Dialog
import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.expandablebottomsheet.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private lateinit var binding: BottomSheetBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog: BottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        //inflating layout
        val view: View = View.inflate(context, R.layout.bottom_sheet, null)
        //binding views to data binding
        binding = DataBindingUtil.bind(view)!!

        //set layout with bottom sheet
        bottomSheetDialog.setContentView(view)

        bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        //setting Peek at the 16:9 ratio key line of its parent.
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        //setting max height of the bottom sheet
        binding.extraSpace.minimumHeight = (Resources.getSystem().displayMetrics.heightPixels) / 2

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(view: View, state: Int) {
                if (state == BottomSheetBehavior.STATE_EXPANDED) {
                    showView(binding.appBarLayout, getActionBarSize() as Int)
                    hideAppBar(binding.moreBtn)
                }
                if (state == BottomSheetBehavior.STATE_COLLAPSED) {
                    hideAppBar(binding.appBarLayout)
                    showView(binding.listItem, getActionBarSize() as Int)
                }
                if (state == BottomSheetBehavior.STATE_HIDDEN) {
                    dismiss()
                }
            }

            override fun onSlide(view: View, p1: Float) {
            }
        })


        return bottomSheetDialog
    }

    private fun hideAppBar(view: View) {
        val params: ViewGroup.LayoutParams = view.layoutParams
        params.height = 0
        view.layoutParams
    }

    private fun showView(view: View, size: Int) {
        val params: ViewGroup.LayoutParams = view.layoutParams
        params.height = size
        view.layoutParams
    }

    private fun getActionBarSize(): Int? {
        val array: TypedArray? =
            context?.theme?.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        return array?.getDimension(0, 0F)?.toInt()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val view: View = inflater.inflate(R.layout.see_logs, container, false)
//        adapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            android.R.id.text1,
//            messages
//        )
//        val button = view.findViewById<Button>(R.id.clear_logs)
//        button.setOnClickListener { clearMessages() }
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val listView = view.findViewById<ListView>(android.R.id.list)
//        listView.adapter = adapter
//        adapter?.let { adapters.add(it) }
//
//    }

}

