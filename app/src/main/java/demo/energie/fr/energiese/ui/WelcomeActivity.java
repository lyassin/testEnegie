package demo.energie.fr.energiese.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import demo.energie.fr.energiese.R;

/**
 * Created by yassin on 17/05/2017.
 */

public class WelcomeActivity extends Activity {

    Button go2registerpage;
    PopupWindow popUpWindow;
    ImageView imgView;
    boolean isClicked = true;
    private LinearLayout mLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        go2registerpage=(Button) findViewById(R.id.btnRegister);
        imgView=(ImageView) findViewById(R.id.image);
        mLinearLayout=(LinearLayout)findViewById(R.id.ln1);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popup_view = inflater.inflate(R.layout.popup_view,null);
        popUpWindow = new PopupWindow( popup_view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        go2registerpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    isClicked = false;

                    popUpWindow.showAtLocation(mLinearLayout,Gravity.CENTER,0,0);
                   // popUpWindow.update(50, 50, 320, 90);
                } else {
                    isClicked = true;
                    popUpWindow.dismiss();
                }
            }

        });
    }
}
