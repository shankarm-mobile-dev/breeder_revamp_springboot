package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BodyWeightReportRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BodyWeightReportServiceImpl implements BodyWeightReportService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public String getBodyWeight(BodyWeightReportRequestDto req) {

        try {
            // Step 1: Call procedure
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sug_rpt_gpps_pkg.sug_df_bdywt");

            query.registerStoredProcedureParameter("errbuf", String.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("retcode", Integer.class, ParameterMode.OUT);

            query.registerStoredProcedureParameter("p_ledger", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_region_id", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_plant_code", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_flock", String.class, ParameterMode.IN);

            query.setParameter("p_ledger", req.getLedger());
            query.setParameter("p_region_id", req.getRegionId());
            query.setParameter("p_plant_code", req.getPlantCode());
            query.setParameter("p_flock", req.getFlock());

            query.execute();

            // Step 2: Read HTML from table
            String html = jdbcTemplate.query(
                    "SELECT data_clob FROM sug_clob_gtt ORDER BY id",
                    rs -> {
                        StringBuilder sb = new StringBuilder();
                        while (rs.next()) {
                            sb.append(rs.getString("data_clob"));
                        }
                        return sb.toString();
                    }
            );

            // System.out.println(html);
            return html;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing report: " + e.getMessage());
        }
    }

}
