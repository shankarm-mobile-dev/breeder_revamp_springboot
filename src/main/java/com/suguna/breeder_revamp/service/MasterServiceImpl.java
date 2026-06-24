package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.MasterResultDto;
import com.suguna.breeder_revamp.utils.ResultSetMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class MasterServiceImpl implements MasterServices {
    @Autowired
    EntityManager entityManager;

    @Override
    public MasterResultDto getfvalueeight() throws SQLException {
        MasterResultDto masterResultDto=new MasterResultDto();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gppsmob_pkg.getfvalueeight");
        MasterResultDto.fvalueeight appinfo = new MasterResultDto.fvalueeight();
        ArrayList<MasterResultDto.fvalueeight> Result = new ArrayList<MasterResultDto.fvalueeight>();
        storedProcedureQuery.registerStoredProcedureParameter(1, ArrayList.class, ParameterMode.REF_CURSOR);
        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(1);
        storedProcedureQuery.execute();

        while (resultSet.next()) {
            MasterResultDto.fvalueeight pojo = null;
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet, MasterResultDto.fvalueeight.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        masterResultDto.setFvaluemst(Result);
        return masterResultDto;
    }

    @Override
    public MasterResultDto getitemmaster(String branchId) throws SQLException {
        MasterResultDto masterResultDto=new MasterResultDto();
        MasterResultDto.itemmaster appinfo = new MasterResultDto.itemmaster();
        ArrayList<MasterResultDto.itemmaster> Result = new ArrayList<MasterResultDto.itemmaster>();
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("sug_mai_gpps_mob_pkg.getitemmaster");
        storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        //storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, ArrayList.class, ParameterMode.REF_CURSOR);
        storedProcedureQuery.setParameter(1, branchId);

        ResultSet resultSet = (ResultSet) storedProcedureQuery.getOutputParameterValue(2);
        storedProcedureQuery.execute();
        System.out.println(branchId);
        while (resultSet.next()) {
            MasterResultDto.itemmaster pojo = null;
            try {
                appinfo = ResultSetMapper.mapResultSetToObject(resultSet, MasterResultDto.itemmaster.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Result.add(appinfo);
        }
        masterResultDto.setItemmst(Result);
        return masterResultDto;
    }

}
