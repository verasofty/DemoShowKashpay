package onsigna.app.demoshowmpos;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();

    private EditText et_amount;
    private Button btn_pay;
    private TextView tv_result;

    public static final String USER_MAIL = "USER_MAIL";
    public static final String USER_PSW = "USER_PSW";
    public static final String AMOUNT = "AMOUNT";
    public static final String URL_PLAYSTORE = "https://play.google.com/store/apps/details?id=";
    public static final String PACKAGE_APP = "onsigna.kashpay.movil.LOGIN_ACTIVITY";
    public static final String APP_ID = "onsigna.kashpay.movil";
    public static final int REQUEST_CODE = 2022;

    public static final String LOTE = "LOTE";
    public static final String FOLIO = "FOLIO";
    public static final String PRODUCTNAME = "PRODUCTNAME";
    public static final String RRC = "RRC";
    public static final String AUTHORIZATION = "AUTHORIZATION";
    public static final String MASKED_CARD = "MASKED_CARD";
    public static final String AUTHORIZED_AMOUNT = "AUTHORIZED_AMOUNT";
    public static final String PROPINA = "PROPINA";
    public static final String TYPE_SIGN = "TYPE_SIGN";
    public static final String ERROR_MESSAGE_LAUNCH = "ERROR_MESSAGE_LAUNCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_amount = findViewById(R.id.et_amount);
        btn_pay = findViewById(R.id.btn_pay);
        tv_result = findViewById(R.id.tv_result);

        btn_pay.setOnClickListener(view -> {
            try{
                Intent intent = new Intent(PACKAGE_APP);
                intent.putExtra(USER_MAIL, "pepemucaj@em.com");
                intent.putExtra(USER_PSW, "Papemu1234");
                intent.putExtra(AMOUNT, et_amount.getText().toString()); //Formato Monto 0,000.00
                startActivityForResult(intent, REQUEST_CODE);

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(URL_PLAYSTORE + APP_ID)));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                String sResult =
                        "1.- " + data.getStringExtra(LOTE) + "\n" +
                        "2.- " + data.getStringExtra(FOLIO) + "\n" +
                        "3.- " + data.getStringExtra(PRODUCTNAME) + "\n" +
                        "4.- " + data.getStringExtra(RRC) + "\n" +
                        "5.- " + data.getStringExtra(AUTHORIZATION) + "\n" +
                        "6.- " + data.getStringExtra(MASKED_CARD) + "\n" +
                        "7.- " + data.getStringExtra(AUTHORIZED_AMOUNT) + "\n" +
                        "8.- " + data.getStringExtra(PROPINA) + "\n" +
                        "9.- " + data.getStringExtra(TYPE_SIGN);

                tv_result.setText(sResult);

            } else {
                tv_result.setText(data.getStringExtra(ERROR_MESSAGE_LAUNCH));
            }

        }
    }
}