package com.example.administrator.layout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import android.provider.Settings;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;


public class PermissionsAction extends AppCompatActivity {


    private int REQUEST_CODE_PERMISSION = 0x0099;
    private final String TAG = "MPermissions";


//检查权限是否被完全授予

    public boolean CheckPermissons(String[] permissions){
  //检查sdk版本
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
  //检查传入的每一个权限是否被授予
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }



//存储没有被许可的权限

    public List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission))

            {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


//检查权限是否被永久禁止显示

    public boolean CheckPermissionisBanedDisaplay(String[] permissions){

        for (String permission:permissions) {

        if (ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
        {

            new AlertDialog.Builder(this)
                    .setTitle("提示信息")
                    .setMessage("部分权限已禁止提示，点击确定进入设置手动开启")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startAppSettings();

                        }
                    }).show();

            return true;
        }
        else

            {

            return false;

        }
    }

    return false;
}


//启动设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }


//成功申请权限提示
    public void permissionSuccess(int requestCode) {
        Log.d(TAG, "获取权限成功，成功代码=" + requestCode);
    }


//失败获取权限提示
    public void permissionFail(int requestCode) {
        Log.d(TAG, "获取权限失败，错误代码=" + requestCode);
    }


//请求权限
    public void requestPermission(String[] permissions, int requestCode) {
        this.REQUEST_CODE_PERMISSION = requestCode;

        if (CheckPermissons(permissions)) {
            permissionSuccess(REQUEST_CODE_PERMISSION);
        }

        else

            {
                List<String> needPermissions = getDeniedPermissions(permissions);

            if (CheckPermissionisBanedDisaplay(permissions)) {

                permissionFail(REQUEST_CODE_PERMISSION);
                return;

            } else {

                ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);

            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        for (int grantResult:grantResults) {

            if (grantResult != PackageManager.PERMISSION_GRANTED) {

                permissionFail(REQUEST_CODE_PERMISSION);

            } else {

                permissionSuccess(REQUEST_CODE_PERMISSION);

            }

        }

    }

}
