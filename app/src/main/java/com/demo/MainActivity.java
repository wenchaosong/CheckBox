package com.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.GridView;

import com.ms.checkbox.CheckBoxGroupView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mData = new ArrayList<>();
    private TestAdapter mTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gv);
        final CheckBoxGroupView checkBoxGroupView = (CheckBoxGroupView) findViewById(R.id.checkBoxGroupView);
        checkBoxGroupView.setNewData(initList());

        mTestAdapter = new TestAdapter(this, mData, checkBoxGroupView);
        gridView.setAdapter(mTestAdapter);

        checkBoxGroupView.setCheckedListener(new CheckBoxGroupView.CheckedChangeListener() {
            @Override
            public void onCheckedChange(int position, String data) {
                if (!TextUtils.isEmpty(data)) {
                    mData.add(data);
                    mTestAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private List<String> initList() {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < 12; index++) {
            list.add("中" + index);
        }
        return list;
    }
}
