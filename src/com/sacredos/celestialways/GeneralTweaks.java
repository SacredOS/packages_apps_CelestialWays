/*
 * Copyright (C) 2016 AospExtended ROM Project
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

package com.sacredos.celestialways;

import android.app.ActivityManagerNative;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.Context;
import android.content.ContentResolver;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManagerGlobal;
import android.view.IWindowManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Locale;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.Utils;
//import com.sacredos.celestialways.preference.ScreenshotEditPackageListAdapter;
//import com.sacredos.celestialways.preference.ScreenshotEditPackageListAdapter.PackageItem;

public class GeneralTweaks extends SettingsPreferenceFragment implements 
       OnPreferenceChangeListener {

//    private static final String HEADSET_CONNECT_PLAYER = "headset_connect_player";
//    private static final String RINGTONE_FOCUS_MODE = "ringtone_focus_mode";
//    private static final String INCALL_VIB_OPTIONS = "incall_vib_options";
//    private static final int DIALOG_SCREENSHOT_EDIT_APP = 1;

    private PreferenceCategory mLedsCategory;
    private Preference mChargingLeds;
//    private Preference mScreenshotEditAppPref;
//    private ListPreference mLaunchPlayerHeadsetConnection;
//    private ListPreference mHeadsetRingtoneFocus;
//    private ScreenshotEditPackageListAdapter mPackageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.general_tweaks);

        final ContentResolver resolver = getActivity().getContentResolver();
        final PreferenceScreen prefSet = getPreferenceScreen();

        mLedsCategory = (PreferenceCategory) findPreference("light_category");
        mChargingLeds = (Preference) findPreference("battery_charging_light");
        if (mChargingLeds != null
                && !getResources().getBoolean(
                        com.android.internal.R.bool.config_intrusiveBatteryLed)) {
            mLedsCategory.removePreference(mChargingLeds);
        }
          if (mChargingLeds == null) {
            prefSet.removePreference(mLedsCategory);
        }

//        mLaunchPlayerHeadsetConnection = (ListPreference) findPreference(HEADSET_CONNECT_PLAYER);
//        int mLaunchPlayerHeadsetConnectionValue = Settings.System.getIntForUser(resolver,
//                Settings.System.HEADSET_CONNECT_PLAYER, 0, UserHandle.USER_CURRENT);
//        mLaunchPlayerHeadsetConnection.setValue(Integer.toString(mLaunchPlayerHeadsetConnectionValue));
//        mLaunchPlayerHeadsetConnection.setSummary(mLaunchPlayerHeadsetConnection.getEntry());
//        mLaunchPlayerHeadsetConnection.setOnPreferenceChangeListener(this);
//
//        mHeadsetRingtoneFocus = (ListPreference) findPreference(RINGTONE_FOCUS_MODE);
//        int mHeadsetRingtoneFocusValue = Settings.Global.getInt(resolver,
//                Settings.Global.RINGTONE_FOCUS_MODE, 0);
//        mHeadsetRingtoneFocus.setValue(Integer.toString(mHeadsetRingtoneFocusValue));
//        mHeadsetRingtoneFocus.setSummary(mHeadsetRingtoneFocus.getEntry());
//        mHeadsetRingtoneFocus.setOnPreferenceChangeListener(this);
//
//        PreferenceCategory incallVibCategory = (PreferenceCategory) findPreference(INCALL_VIB_OPTIONS);
//        if (!Utils.isVoiceCapable(getActivity())) {
//            prefSet.removePreference(incallVibCategory);
//        }
//
//        mPackageAdapter = new ScreenshotEditPackageListAdapter(getActivity());
//        mScreenshotEditAppPref = findPreference("screenshot_edit_app");
//        mScreenshotEditAppPref.setOnPreferenceClickListener(this);
//
    }

//    @Override
//    public Dialog onCreateDialog(int dialogId) {
//        switch (dialogId) {
//            case DIALOG_SCREENSHOT_EDIT_APP: {
//                Dialog dialog;
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//                final ListView list = new ListView(getActivity());
//                list.setAdapter(mPackageAdapter);
//                alertDialog.setTitle(R.string.profile_choose_app);
//                alertDialog.setView(list);
//                dialog = alertDialog.create();
//                list.setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        // Add empty application definition, the user will be able to edit it later
//                        PackageItem info = (PackageItem) parent.getItemAtPosition(position);
//                        Settings.System.putString(getActivity().getContentResolver(),
//                                Settings.System.SCREENSHOT_EDIT_USER_APP, info.packageName);
//                        dialog.cancel();
//                    }
//                });
//               return dialog;
//            }
//         }
//        return super.onCreateDialog(dialogId);
//    }
//
//    @Override
//    public int getDialogMetricsCategory(int dialogId) {
//        switch (dialogId) {
//            case DIALOG_SCREENSHOT_EDIT_APP:
//                return MetricsEvent.CELESTIAL_WAYS;
//            default:
//                return 0;
//        }
//     }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CELESTIAL_WAYS;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
//        ContentResolver resolver = getActivity().getContentResolver();
//        if (preference == mLaunchPlayerHeadsetConnection) {
//            int mLaunchPlayerHeadsetConnectionValue = Integer.valueOf((String) newValue);
//            int index = mLaunchPlayerHeadsetConnection.findIndexOfValue((String) newValue);
//            mLaunchPlayerHeadsetConnection.setSummary(
//                    mLaunchPlayerHeadsetConnection.getEntries()[index]);
//            Settings.System.putIntForUser(resolver, Settings.System.HEADSET_CONNECT_PLAYER,
//                    mLaunchPlayerHeadsetConnectionValue, UserHandle.USER_CURRENT);
//            return true;
//        } else if (preference == mHeadsetRingtoneFocus) {
//            int mHeadsetRingtoneFocusValue = Integer.valueOf((String) newValue);
//            int index = mHeadsetRingtoneFocus.findIndexOfValue((String) newValue);
//            mHeadsetRingtoneFocus.setSummary(
//                    mHeadsetRingtoneFocus.getEntries()[index]);
//            Settings.Global.putInt(resolver, Settings.Global.RINGTONE_FOCUS_MODE,
//                    mHeadsetRingtoneFocusValue);
//            return true;
//        }
        return false;
    }

//    @Override
//    public boolean onPreferenceClick(Preference preference) {
//        // Don't show the dialog if there are no available editor apps
//        if (preference == mScreenshotEditAppPref && mPackageAdapter.getCount() > 0) {
//            showDialog(DIALOG_SCREENSHOT_EDIT_APP);
//        } else {
//           Toast.makeText(getActivity(), getActivity().getString(R.string.screenshot_edit_app_no_editor),
//                    Toast.LENGTH_LONG).show();
//        }
//        return true;
//    }
}