package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.MasterResultDto;

import java.sql.SQLException;

public interface MasterServices {
    MasterResultDto getfvalueeight() throws SQLException;

    MasterResultDto getitemmaster(String branchId) throws SQLException ;
}
