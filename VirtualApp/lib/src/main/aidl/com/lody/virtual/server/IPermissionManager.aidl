// IPermissionManager.aidl
package com.lody.virtual.server;

interface IPermissionManager {

    int checkBackgroundPermission(int uid, String pkg, String action);

}
