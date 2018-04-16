package com.affwl.exchange.alerts;


import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.affwl.exchange.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class SixtySecAlertFragment extends Fragment implements View.OnClickListener {

    TextView sixtysec_sounds,ringtone_sixtysec;
    private int TONE_PICKER=905;
    Ringtone currentRingtone;
    Uri currentTone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sixty_sec_alert, container, false);

        sixtysec_sounds=view.findViewById(R.id.sixtysec_sounds);
        ringtone_sixtysec=view.findViewById(R.id.ringtone_sixtysec);
        sixtysec_sounds.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sixtysec_sounds:
                currentTone= RingtoneManager.getActualDefaultRingtoneUri(v.getContext(), RingtoneManager.TYPE_NOTIFICATION);
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currentTone);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                startActivityForResult(intent, TONE_PICKER);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("Request Code"," "+requestCode);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == 905) {
                    currentTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    ringtone_sixtysec.setVisibility(View.VISIBLE);
                    currentRingtone = RingtoneManager.getRingtone(getActivity(), currentTone);
                    ringtone_sixtysec.setText("Current Ringtone:" + currentRingtone.getTitle(getActivity()));
                }
                break;
            case RESULT_CANCELED:
//            currentTone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI);
                Toast.makeText(getContext(), "Select Ringtone", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
