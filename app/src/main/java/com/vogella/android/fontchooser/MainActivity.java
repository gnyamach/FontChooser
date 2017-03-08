package com.vogella.android.fontchooser;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView textView, tv_barProgress, tv_barProgress_R, tv_barProgress_G, tv_barProgress_B;
    private EditText fontSize;
    private static SeekBar seekBar, seekBar_R, seekBar_G, seekBar_B;
    private static int color_A, color_R, color_G, color_B;
    private static String TAG = MainActivity.class.getSimpleName();
    private Intent intent;
    private FloatingActionButton myFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        fontSize = (EditText)findViewById(R.id.et_fontsize);


        intent = getIntent();
        if (intent.getExtras() != null){
            handleIntent(intent);
        }

        myFAB = (FloatingActionButton) findViewById(R.id.fab_display);
        myFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getExtras() != null){
                    setResult(RESULT_OK, intent);
                    Log.i(TAG, "Returning to the calling application");
                    finish();
                }
            }
        });

        Spinner spinner = (Spinner)findViewById(R.id.spinnerStyle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.styles_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        seekBarMover();

    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (action.equals("msud.cs3013.ACTION_RETRIEVE_FONT")){
            Log.i(TAG, "Handling the action");
        }else {
            Log.i(TAG, "Missing or Unrecognized Action");
        }
    }

    private void seekBarMover() {
        seekBar = (SeekBar)findViewById(R.id.seekBarFont);
        seekBar.setMax(254);
        tv_barProgress = (TextView)findViewById(R.id.tv_seekbarProgress);
        tv_barProgress.setText("A: "+ seekBar.getProgress() + " / "+ seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color_A = progress;
                tv_barProgress.setText("A: "+ color_A + " / "+ seekBar.getMax());
                setColor_ARGB();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_barProgress.setText("A: "+ color_A + " / "+ seekBar.getMax());
                setColor_ARGB();
            }
        });

        seekBar_R= (SeekBar)findViewById(R.id.seekBarFont_R);
        seekBar_R.setMax(254);
        tv_barProgress_R = (TextView)findViewById(R.id.tv_seekbarProgress_R);
        tv_barProgress_R.setText("R: "+ seekBar_R.getProgress() + " / "+ seekBar_R.getMax());

        seekBar_R.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color_R = progress;
                tv_barProgress_R.setText("R: "+ color_R + " / "+ seekBar.getMax());
                setColor_ARGB();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_barProgress_R.setText("R: "+ color_R + " / "+ seekBar.getMax());
                setColor_ARGB();
            }
        });

        seekBar_G= (SeekBar)findViewById(R.id.seekBarFont_G);
        seekBar_G.setMax(254);
        tv_barProgress_G = (TextView)findViewById(R.id.tv_seekbarProgress_G);
        tv_barProgress_G.setText("G: "+ seekBar_G.getProgress() + " / "+ seekBar_G.getMax());

        seekBar_G.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color_G = progress;
                tv_barProgress_G.setText("G: "+ color_G + " / "+ seekBar.getMax());
                setColor_ARGB();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_barProgress_G.setText("G: "+ color_G + " / "+ seekBar.getMax());
                setColor_ARGB();
            }
        });

        seekBar_B= (SeekBar)findViewById(R.id.seekBarFont_B);
        seekBar_B.setMax(254);
        tv_barProgress_B = (TextView)findViewById(R.id.tv_seekbarProgress_B);
        tv_barProgress_B.setText("B: "+ seekBar_B.getProgress() + " / "+ seekBar_B.getMax());

        seekBar_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color_B = progress;
                tv_barProgress_B.setText("B: "+ color_B + " / "+ seekBar.getMax());
                setColor_ARGB();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_barProgress_B.setText("B: "+ color_B + " / "+ seekBar.getMax());
                setColor_ARGB();
            }
        });
    }

    private void setColor_ARGB() {
        intent.putExtra("Color A",color_A);
        intent.putExtra("Color R",color_R);
        intent.putExtra("Color G",color_G);
        intent.putExtra("Color B",color_B);
        textView.setTextColor(Color.argb(color_A, color_R, color_G, color_B));
    }

    /**
     * Used to set one color eg yellow. Not used in this program
     * @param value
     */
    private void setColor(int value){
        int theColor = value;     //alpha
        theColor <<= 8;  theColor += 250;  //red
        theColor <<= 8;  theColor += 250;  //green
        theColor <<= 8;  theColor +=  10;  //blue
        textView.setTextColor(theColor);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),
                "Style: " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();
        String setType = parent.getItemAtPosition(position).toString();
        if (setType.equals("DEFAULT")){
            textView.setTypeface(Typeface.DEFAULT);
            intent.putExtra("Typeface","DEFAULT");
        }else  if (setType.equals("DEFAULT_BOLD")){
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            intent.putExtra("Typeface","DEFAULT_BOLD");
        }else  if (setType.equals("MONOSPACE")){
            textView.setTypeface(Typeface.MONOSPACE);
            intent.putExtra("Typeface","MONOSPACE");
        }else  if (setType.equals("SANS_SERIF")){
            textView.setTypeface(Typeface.SANS_SERIF);
            intent.putExtra("Typeface","SANS_SERIF");
        }else  if (setType.equals("SERIF")){
            textView.setTypeface(Typeface.SERIF);
            intent.putExtra("Typeface","SERIF");
        }else  if (setType.equals("BOLD")){
            textView.setTypeface(null, Typeface.BOLD);
            intent.putExtra("Typeface","BOLD");
        }else  if (setType.equals("BOLD_ITALIC")){
            textView.setTypeface(null, Typeface.BOLD_ITALIC);
            intent.putExtra("Typeface","DEFAULT_BOLD");
        }else  if (setType.equals("ITALIC")){
            textView.setTypeface(null,Typeface.ITALIC);
            intent.putExtra("Typeface","ITALIC");
        }else if (setType.equals("NORMAL")){
            textView.setTypeface(null,Typeface.NORMAL);
            intent.putExtra("Typeface","NORMAL");
        }else{
            intent.putExtra("Typeface","NONE");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setFontSize(View view) {
        if (fontSize.getText().toString().matches("")){
            Log.i(TAG, "no valid font value entered");
            intent.putExtra("FontSize", 2012);
            Toast.makeText(this, "Enter an integer value please", Toast.LENGTH_LONG).show();
        }else{
            int font = Integer.valueOf(fontSize.getText().toString());
            textView.setTextSize(Integer.valueOf(fontSize.getText().toString()));
            intent.putExtra("FontSize", font);
        }
    }
}
