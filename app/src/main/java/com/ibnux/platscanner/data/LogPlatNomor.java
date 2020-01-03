package com.ibnux.platscanner.data;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class LogPlatNomor {
    @Id
    public long id;
    public String plat="",siapa="";
    public long tanggal = 0L;
}

