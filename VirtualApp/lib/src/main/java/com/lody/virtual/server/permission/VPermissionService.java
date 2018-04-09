package com.lody.virtual.server.permission;

import android.content.pm.PackageManager;
import android.os.RemoteException;

import com.lody.virtual.helper.collection.SparseArray;
import com.lody.virtual.server.IPermissionManager;

import java.util.List;

/**
 * Created by hb.chen on 2018/4/9.
 */
public class VPermissionService extends IPermissionManager.Stub {

    private static VPermissionService sInstance = new VPermissionService();
    private final SparseArray<List<String>> mGrantedBackgroundPerms = new SparseArray<>();
    private PermissionManagerPersistenceLayer mPersistenceLayer = new PermissionManagerPersistenceLayer(this);

    private VPermissionService() {
        mPersistenceLayer.read();
    }

    public static VPermissionService get() {
        return sInstance;
    }

    @Override
    public int checkBackgroundPermission(int uid, String pkg, String action) throws RemoteException {
        if (!needBackgroundPermission(action)) {
            return PackageManager.PERMISSION_GRANTED;
        }
        synchronized (mGrantedBackgroundPerms) {
            List<String> grantedPerms = mGrantedBackgroundPerms.get(uid, null);
            if (grantedPerms == null) {
                return PackageManager.PERMISSION_GRANTED;
            }
            if (!grantedPerms.contains(pkg)) {
                return PackageManager.PERMISSION_DENIED;
            }
        }
        return PackageManager.PERMISSION_GRANTED;
    }

    private boolean needBackgroundPermission(String action) {
        // todo
        return false;
    }
}
