package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.DailyFarmSummaryRequestDto;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyFarmSummaryServiceImpl implements DailyFarmSummaryService {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public String getDailyFarmSummary(DailyFarmSummaryRequestDto req) {

        try {

            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sug_rpt_gpps_pkg.brd_daily_sumry_rpt");

            // parameter
            query.registerStoredProcedureParameter("p_mode", String.class, ParameterMode.IN);

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