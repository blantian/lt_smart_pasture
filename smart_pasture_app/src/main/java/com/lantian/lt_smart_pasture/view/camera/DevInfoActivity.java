package com.lantian.lt_smart_pasture.view.camera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.lantian.lt_smart_pasture.R;
import com.lantian.lib_commin_ui.base.BaseActivity;
import com.lantian.lt_smart_pasture.view.camera.adapter.AddDevAdapter;

/**
 * Created by Sherlock·Holmes on 2020-03-13
 */
public class DevInfoActivity extends BaseActivity {
    private int m_iDevIndex = -1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_dev_info;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        m_iDevIndex = intent.getExtras().getInt("DevIndex");
        ListView lvMenu = findViewById(R.id.listview_devinfo);
        AddDevAdapter daMenu = new AddDevAdapter(this, m_iDevIndex);
        lvMenu.setAdapter(daMenu);
    }
}
