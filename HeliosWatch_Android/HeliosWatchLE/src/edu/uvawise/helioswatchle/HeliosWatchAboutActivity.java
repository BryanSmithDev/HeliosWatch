/*
 * Copyright (C) 2014 Helios Watch - Open source smart watch project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uvawise.helioswatchle;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bryan on 5/3/2014.
 */
public class HeliosWatchAboutActivity extends Activity {

    TextView hVersion = null;
    String version = "1.0";
    Button heliosGitHub = null;
    Button retroGitHub = null;
    Button licenseButton = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        hVersion = (TextView)findViewById(R.id.heliosVersionTextView);
        try {
            version = getBaseContext().getPackageManager()
                    .getPackageInfo(getBaseContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            version = "1.0";
        }
        if (hVersion != null) hVersion.setText("Version: "+version);

        heliosGitHub = (Button)findViewById(R.id.helios_github_button);
        if (heliosGitHub != null){
            heliosGitHub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/BryanSmithDev/HeliosWatch"));
                    startActivity(browserIntent);
                }
            });
        }

        retroGitHub = (Button)findViewById(R.id.retro_github_button);
        if (retroGitHub != null) {
            retroGitHub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/godstale/retrowatch"));
                    startActivity(browserIntent);
                }
            });
        }

        licenseButton = (Button)findViewById(R.id.licenseButton);
        if (licenseButton != null) {
            licenseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://raw.githubusercontent.com/BryanSmithDev/HeliosWatch/Helios/LICENSE"));
                    startActivity(browserIntent);
                }
            });
        }
    }

}