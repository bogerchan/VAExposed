package com.lody.virtual.client.ipc;

import android.os.IBinder;
import android.os.RemoteException;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.server.IPermissionManager;

/**
 * Created by hb.chen on 2018/4/9.
 */
public class VPermissionManager {

    private static final VPermissionManager sInstance = new VPermissionManager();
    private IPermissionManager mRemote;

    private VPermissionManager() {
    }

    public static VPermissionManager get() {
        return sInstance;
    }

    public IPermissionManager getRemote() {
        if (mRemote == null ||
                (!mRemote.asBinder().pingBinder() && !VirtualCore.get().isVAppProcess())) {
            synchronized (this) {
                Object remote = getRemoteInterface();
                mRemote = LocalProxyUtils.genProxy(IPermissionManager.class, remote);
            }
        }
        return mRemote;
    }

    private Object getRemoteInterface() {
        final IBinder binder = ServiceManagerNative.getService(ServiceManagerNative.VIRTUAL_PERMISSION);
        return IPermissionManager.Stub.asInterface(binder);
    }

    public int checkBackgroundPermission(int uid, String pkg, String action) {
        try {
            return getRemote().checkBackgroundPermission(uid, pkg, action);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }
}
