package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.DailyFarmSummaryRequestDto;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class DailyFarmSummaryServiceImpl implements DailyFarmSummaryService {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public String getDailyFarmSummary(DailyFarmSummaryRequestDto req) {

        try {
            // CLEAR OLD TEMP DATA
            jdbcTemplate.execute("TRUNCATE TABLE sug_clob_gtt");

            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sug_rpt_gpps_pkg.brd_daily_sumry_rpt");

            // OUT params
            query.registerStoredProcedureParameter("errbuf", String.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("retcode", Integer.class, ParameterMode.OUT);

            // IN params
            query.registerStoredProcedureParameter("p_company", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_region_id", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_branch_CODE", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_from_date", java.sql.Timestamp.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_to_date", java.sql.Timestamp.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_mode", String.class, ParameterMode.IN);

            // SET values
            query.setParameter("p_company", req.getCompany());
            query.setParameter("p_region_id", req.getRegionId());
            query.setParameter("p_branch_CODE", req.getBranchCode());

            query.setParameter("p_from_date", Timestamp.valueOf(req.getFromDate() + " 00:00:00"));
            query.setParameter("p_to_date", Timestamp.valueOf(req.getToDate() + " 00:00:00"));

            query.setParameter("p_mode", req.getMode().trim());

            query.execute();

            // fetch HTML
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

            return html;

        } catch (Exception e) {
            throw new RuntimeException("Error executing Daily Farm Summary report: " + e.getMessage());
        }
    }
}