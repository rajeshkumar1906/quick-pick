package com.quickpick.views.ui.customviews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.easydialog.EasyDialog;
import com.quickpick.R;
import com.quickpick.views.ui.dashboard.DashBoardActivityNew;
import com.quickpick.views.ui.dashboard.GetCategory_Id;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 07-03-2018.
 */

public class CustomDialog {
    public static CustomDialog customDialog;


    public static CustomDialog getInstance(){

        if(customDialog==null){
            customDialog = new CustomDialog();
        }
        return customDialog;

    }


    public void showCategory_Dialog(final Context activity){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.pay_dialog);
       final RadioGroup radio_group = (RadioGroup)dialog.findViewById(R.id.radio_group);
        String[] payement_array = activity.getResources().getStringArray(R.array.payment_type);
        TextView txt_cancel = (TextView)dialog.findViewById(R.id.txt_cancel);
        Button btn_proceed = (Button) dialog.findViewById(R.id.btn_proceed);

        for (int i = 0; i < payement_array.length ; i++) {
            RadioButton rbn = new RadioButton(activity);
            rbn.setId(i + 1000);
            rbn.setText(payement_array[i]);
            radio_group.addView(rbn);
        }
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_group.getCheckedRadioButtonId() == -1)
                {
                    Toast.makeText(activity,"Please select atleast one payment option",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public void showTooltip(final Context context, ArrayList<String > category_items, View target_view){
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.categoriesdialog, null);
        final EasyDialog dialog=   new EasyDialog(context)
                // .setLayoutResourceId(R.layout.layout_tip_content_horizontal)//layout resource id
                .setLayout(view)
                .setBackgroundColor(context.getResources().getColor(R.color.white))
                // .setLocation(new location[])//point in screen
                .setLocationByAttachedView(target_view)
                .setGravity(EasyDialog.GRAVITY_TOP)
                .setAnimationTranslationShow(EasyDialog.DIRECTION_Y, 1000, -600, 100, -50, 50, 0)
                .setAnimationAlphaShow(1000, 0.3f, 1.0f)
                .setAnimationTranslationDismiss(EasyDialog.DIRECTION_Y, 500, -50, 800)
                .setAnimationAlphaDismiss(500, 1.0f, 0.0f)
                .setTouchOutsideDismiss(true)
                .setMatchParent(true)
                .setMarginLeftAndRight(24, 24)
                .setOutsideColor(context.getResources().getColor(R.color.black_transprient))
                .show();
        ListView listView = (ListView)view.findViewById(R.id.ll_categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,category_items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetCategory_Id getCategory_id =(GetCategory_Id)context;
                getCategory_id.getId(position);
                dialog.dismiss();
            }
        });


    }





}
