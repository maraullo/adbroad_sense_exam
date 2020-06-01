package com.mars.customview;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mars.R;

public class ParentViewHolder extends RecyclerView.ViewHolder
{
    ExpandableListView exp_lv_item;
    UICheckBox ui_checkbox;
    ImageButton ib_more;


    public ParentViewHolder(@NonNull View itemView)
    {
        super(itemView);
        exp_lv_item = itemView.findViewById(R.id.exp_lv_item);
        ui_checkbox = itemView.findViewById(R.id.ui_checkbox);
        ib_more = itemView.findViewById(R.id.ib_more);

    }

    public UICheckBox getUi_checkbox() { return ui_checkbox; }

    public ExpandableListView getExp_lv_item() {
        return exp_lv_item;
    }

    public ImageButton getIb_more() { return ib_more; }
}
