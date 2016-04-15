package tianqiw.myapplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import tianqiw.myapplication.R;
import tianqiw.myapplication.entity.ConfigManager;
import tianqiw.myapplication.model.ConfigData;
import tianqiw.myapplication.model.enums.VisaType;

public class WelcomeActivity extends AppCompatActivity {
    private Button f1Button, j1Button;
    private static final int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.ui_welcome);

        ConfigData config = ConfigManager.get(this).readConfig(0);
        if (config.getType() == VisaType.UNDEFINED) {
            // let user choose visa type
            showVisaTypeSelections();
        } else {
            // directly goto task flow activity
            startActivityWithDelay(DELAY, config.getType());
        }


    }

    private void startActivityWithDelay(int delay, VisaType type) {
        final Intent intent = new Intent();
        intent.putExtra(getString(R.string.visa_type), type.getValue());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //intent.setClass(WelcomeActivity.this, DocListActivity.class);
                intent.setClass(WelcomeActivity.this, TaskFlowActivity.class);
                startActivity(intent);
            }
        }, delay);
    }

    private void showVisaTypeSelections() {
        // show buttons
        f1Button = (Button) findViewById(R.id.f1_button);
        j1Button = (Button) findViewById(R.id.j1_button);
        f1Button.setVisibility(View.VISIBLE);
        j1Button.setVisibility(View.VISIBLE);

        bindButton(f1Button, VisaType.F1);
        bindButton(j1Button, VisaType.J1);
    }

    private void bindButton(Button button, final VisaType type) {
        final Intent intent = new Intent();
        intent.putExtra(getString(R.string.visa_type), type.getValue());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the config to db
                ConfigManager.get(WelcomeActivity.this).createConfig(new ConfigData(type));
                // start task flow activity
                intent.setClass(WelcomeActivity.this, TaskFlowActivity.class);
                startActivity(intent);
            }
        });
    }
}
