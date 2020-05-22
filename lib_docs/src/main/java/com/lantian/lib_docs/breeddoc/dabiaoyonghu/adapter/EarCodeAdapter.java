package com.lantian.lib_docs.breeddoc.dabiaoyonghu.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lantian.lib_base.entity.module.response.breeds.BreedsList;
import com.lantian.lib_docs.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sherlock·Holmes on 2020-04-04
 */
public class EarCodeAdapter extends BaseQuickAdapter<BreedsList, BaseViewHolder> {

    public EarCodeAdapter(int layoutResId, @Nullable List<BreedsList> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, BreedsList item) {

            helper.setText(R.id.earTitle,item.getBreed().getTitle())
                    .setText(R.id.earAge,item.getAge()+"天")
                    .setText(R.id.earBreed,item.getBreed().getMother()+"个")
                    .setText(R.id.earWeight,item.getWeight()+"kg");

            if (item.getType().equals("2")){
                helper.setText(R.id.sexOfBreed,"母");
            }else {
                helper.setText(R.id.sexOfBreed,"公-");
            }
            if (item.getEartag() !=null){
                helper.setText(R.id.earCode,item.getEartag().getNumber());
            }else {
                helper.setText(R.id.earCode,"此"+item.getBreed().getTitle()+"没有耳标");
            }
                    //.addOnClickListener(R.id.earHeader);
               //ImageLoaderManager.getInstance().displayImageForView((ImageView)helper.getView(R.id.earHeader),"");
           // Glide.with(Utils.getContext()).load(item.getImg()).into((ImageView)helper.getView(R.id.earHeader));

    }

    /**实时搜索牲畜信息**/
    public void breedsearch(EditText view, final List<BreedsList> breeds){
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("beforeTextChanged","之前");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("onTextChanged","实时"+" "+s);
                Pattern pattern = Pattern.compile(String.valueOf(s));
                List<BreedsList> breedsLists = new ArrayList<>();
                for (int i = 0;i<breeds.size();i++){
                    Matcher matcher = pattern.matcher(breeds.get(i).getEartag().getNumber());
                    if (matcher.find()){
                        breedsLists.add(breeds.get(i));
                    }
                }
                EarCodeAdapter.this.setNewData(breedsLists);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("afterTextChanged","之后");
            }
        });
    }

}
