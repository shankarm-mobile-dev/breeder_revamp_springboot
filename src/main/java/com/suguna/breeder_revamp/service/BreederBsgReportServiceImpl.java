package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.dto.BreederBsgRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BreederBsgReportServiceImpl implements BreederBsgReportService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public String getBreederBsgReport(BreederBsgRequestDto req) {

        try {
            // Step 1: Call procedure
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sug_tst_d7_pkg.breeder_bsg_rpt");

            // Input parameter
            query.registerStoredProcedureParameter("ps_flock_no", String.class, ParameterMode.IN);

            query.setParameter("ps_flock_no", req.getFlockNo());

            query.execute();

            // Step 2: Read HTML from GTT
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
            throw new RuntimeException("Error executing BSG report: " + e.getMessage());
        }
    }
}
