package com.taylorjoe.joepostingmodule.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.taylorjoe.joepostingmodule.R;

import java.util.LinkedList;

public class EditActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout contentLayout;
    private Button btn_AddText,btn_AddImg,btn_AddVideo;
    private LinkedList<View> linkedList= new LinkedList();
    private int CHILDVIEWNUM=1;
    private View childView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();
    }

    @Override
    public void initView() {
        contentLayout = (RelativeLayout) findViewById(R.id.content_layout);
        btn_AddText = (Button) findViewById(R.id.btn_add_text);
        btn_AddImg = (Button) findViewById(R.id.btn_add_img);
        btn_AddVideo = (Button) findViewById(R.id.btn_add_video);

        btn_AddText.setOnClickListener(this);
        btn_AddImg.setOnClickListener(this);
        btn_AddVideo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_add_text:
                addChildView(0);
                break;
            case R.id.btn_add_img:
                break;
            case R.id.btn_add_video:
                break;
        }
    }

    public void addChildView(int type){
        LayoutInflater inflater = LayoutInflater.from(this);
        switch (type){
            case 0:
                childView= inflater.inflate(R.layout.add_text_layout,null);
                Button move = (Button) childView.findViewById(R.id.btn_drag);
                Button delete = (Button) childView.findViewById(R.id.btn_delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view = (View) v.getParent();
                        deleteChildView(view.getId());
                    }
                });
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW,contentLayout.getChildAt(CHILDVIEWNUM-1).getId());
                childView.setLayoutParams(params);
                childView.setId(CHILDVIEWNUM);
                contentLayout.addView(childView,CHILDVIEWNUM);
                CHILDVIEWNUM++;
                break;
        }
    }

    public void deleteChildView(int tag){
        View view = contentLayout.findViewById(tag);
        if (view!=null){
            contentLayout.removeView(view);
            CHILDVIEWNUM--;
            View view1 = contentLayout.findViewById(tag+1);
            if (view1!=null){
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW,contentLayout.getChildAt(tag-1).getId());
                view1.setLayoutParams(params);
            }
        }
    }
}
