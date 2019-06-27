package com.igeek.textcheckgroupview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.checkbox.CheckBoxGroupView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBoxGroupView checkTextGroupView = (CheckBoxGroupView) findViewById(R.id.checkTextGroupView);
        checkTextGroupView.updateCheckTexts(initList());
        checkTextGroupView.setSelectEnable(4);
    }

    public List<CheckBoxGroupView.CheckText> initList() {

        List<CheckBoxGroupView.CheckText> list = new ArrayList<>();
        for (int index = 0; index < 5; index++) {
            CheckBoxGroupView.CheckText checkText = new CheckBoxGroupView.CheckText();
            checkText.setText("中" + index);
            list.add(checkText);
        }

        return list;
    }
}
