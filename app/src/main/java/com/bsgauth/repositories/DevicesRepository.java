package com.bsgauth.repositories;

import com.bsgauth.model.Account;
import com.bsgauth.model.Device;

import java.io.IOException;
import java.util.List;

public class DevicesRepository {
    public void deleteDevice (Device device) {

    }

    public List<Device> getAllByAccountToken (int token) throws IOException {
        for (Account account : MockRepositoryFabric.getInstance().getMockData().execute().body().get("accounts"))
            if (account.getTotp() == token)
                return account.getConnectedDevices();

        throw new IllegalArgumentException("Account with this id doesn't exist");
    }
}
