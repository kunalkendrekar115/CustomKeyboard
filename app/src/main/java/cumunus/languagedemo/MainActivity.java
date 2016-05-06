package cumunus.languagedemo;

import android.content.res.Configuration;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relLayKeyboard;
    private EditText edt,edt1;
    String text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String languageToLoad  = "ar"; // your language
//        Locale locale = new Locale(languageToLoad);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);


        final KeyboardView kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        final Keyboard keyboard = new Keyboard(this, R.xml.qwerty);
         kv.setKeyboard(keyboard);


        kv.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {

            }

            @Override
            public void onRelease(int primaryCode) {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {

                Log.v("TAG"," Key is "+primaryCode+" ");
                switch(primaryCode){
                    case Keyboard.KEYCODE_DELETE :
                      try {
                          text = text.substring(0, text.length() - 1);
                          edt.setText(text);
                          break;
                      }catch (Exception e){}

                    case 32:

                        try {
                            text = text+" ";
                            edt.setText(text);
                            break;
                        }catch (Exception e){}

                    default:
                        try {
                        char code = (char)primaryCode;
                        text=text+code;
                        edt.setText(text);

                            Log.d("TAG", "Arabic  "+ text);
                        }catch (Exception e){}

                }


                edt.setSelection(edt.length());
            }

            @Override
            public void onText(CharSequence text) {

                Log.v("TAG","text "+text);
            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });

        edt= (EditText) findViewById(R.id.edt1);
        edt1= (EditText) findViewById(R.id.edt2);

        relLayKeyboard = (RelativeLayout)findViewById(R.id.rl);

        relLayKeyboard.addView(kv);
        relLayKeyboard.setVisibility(View.GONE);

         final View.OnTouchListener exitSoftKeyBoard = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(
                        android.content.Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if(v.equals(edt)){
                    edt.requestFocus();
                    relLayKeyboard.setVisibility(View.VISIBLE);
                }
                return true;
            }
        };

        edt.setOnTouchListener(exitSoftKeyBoard);

        edt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                    relLayKeyboard.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed() {

      if(relLayKeyboard.getVisibility()==View.VISIBLE)
          relLayKeyboard.setVisibility(View.GONE);
        else
        super.onBackPressed();
    }
}
