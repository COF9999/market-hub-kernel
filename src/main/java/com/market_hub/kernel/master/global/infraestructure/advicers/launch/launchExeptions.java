package com.market_hub.kernel.master.global.infraestructure.advicers.launch;

import java.util.function.Supplier;

public class launchExeptions {
    public static <T extends Exception> void launchExeption(Supplier<T> supplier) throws T {
        throw supplier.get();
    }
}
