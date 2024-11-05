package com.market_hub.kernel.master.utils;

import java.util.function.Supplier;

public class ThrowableActions {

    public static <T extends RuntimeException> void launchRuntimeExeption(Supplier<T> exeption){
        throw exeption.get();
    }
}
