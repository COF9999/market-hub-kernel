package com.market_hub.kernel.master.global.domain.dtos.token;

import java.util.Date;

public record TokenInfo(String email, Date issueAtDate, Date expDate){
}
