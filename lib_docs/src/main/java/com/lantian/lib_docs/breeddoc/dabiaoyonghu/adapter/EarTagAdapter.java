package com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_docs.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sherlock·Holmes on 2020/4/6
 */
public class EarTagAdapter extends BaseQuickAdapter<EarTag, BaseViewHolder> {

    public EarTagAdapter(int layoutResId, @Nullable List<EarTag> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final EarTag item) {
        helper.setText(R.id.eraTag,item.getNumber())
                .addOnClickListener(R.id.action_clickable);

    }

    /**搜索耳标**/
    public void eartagserch(EditText view, final List<EarTag> earTags){
            view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.e("beforeTextChanged","之前");
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.e("onTextChanged","实时"+" "+s);
                    Pattern pattern = Pattern.compile(String.valueOf(s));
                    List<EarTag> earTagList = new ArrayList<>();
                    for (int i = 0;i<earTags.size();i++){
                        Matcher matcher = pattern.matcher(earTags.get(i).getNumber());
                        if (matcher.find()){
                            earTagList.add(earTags.get(i));
                        }
                    }
                    EarTagAdapter.this.setNewData(earTagList);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.e("afterTextChanged","之后");
                }
            });
    }

}
