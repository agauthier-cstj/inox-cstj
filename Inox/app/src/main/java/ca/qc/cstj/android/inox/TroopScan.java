package ca.qc.cstj.android.inox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.zxing.Result;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import ca.qc.cstj.android.inox.services.ServicesURI;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class TroopScan extends Activity implements ZXingScannerView.ResultHandler {
    private static final String TAG = "";
    private ZXingScannerView mScannerView;
    private CharSequence mTitle;

    @Override
    public void onCreate(Bundle state) {
        getActionBar().setTitle("Scanneur de troop");
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);    // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        Ion.with(getApplicationContext())
                .load(ServicesURI.SCANNING_SERVICE_URI+ "/"+ rawResult.getText())
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonObject>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonObject> jsonObjectResponse) {
                        if(jsonObjectResponse.getResult() != null) {
                            Toast message = Toast.makeText(getApplicationContext(), jsonObjectResponse.getResult().toString(), Toast.LENGTH_SHORT);
                            message.show();
                        } else {
                            Toast message = Toast.makeText(getApplicationContext(), "Erreur lors de la requête", Toast.LENGTH_SHORT);
                            message.show();
                        }
                    }
                });
    }
}
