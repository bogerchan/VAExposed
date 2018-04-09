package com.lody.virtual.server.permission;

import android.os.Parcel;

import com.lody.virtual.helper.PersistenceLayer;
import com.lody.virtual.os.VEnvironment;

import java.io.File;

/**
 * Created by hb.chen on 2018/4/9.
 */
public class PermissionManagerPersistenceLayer extends PersistenceLayer {

    private VPermissionService mService;

    public PermissionManagerPersistenceLayer(VPermissionService service) {
        super(VEnvironment.getPermissionManagerFile());
        mService = service;
    }

    @Override
    public int getCurrentVersion() {
        return 1;
    }

    @Override
    public void writePersistenceData(Parcel p) {

    }

    @Override
    public void readPersistenceData(Parcel p) {

    }
}
