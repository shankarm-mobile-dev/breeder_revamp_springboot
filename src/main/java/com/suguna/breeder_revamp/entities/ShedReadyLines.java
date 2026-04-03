package com.suguna.breeder_revamp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUG_MAI_GPPS_SHED_READY_LINES", schema = "SUG")
public class ShedReadyLines {
    @Id
    @Column(name = "TRANS_LINE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shed_gpps_line_seq")
    @SequenceGenerator(sequenceName = "SUG_MAI_GPPS_SHED_READY_LINES_S", allocationSize = 1, name = "shed_gpps_line_seq")
    private Long transLineId;
    @Column(name = "TRANS_ID")
    private Long transId;
    @Column(name = "ACTIVITY_ID")
    private Long activityId;
    @Column(name = "FARMER_STATUS")
    private String farmerStatus;
    @Column(name = "MANAGER_STATUS")
    private String managerStatus;
    @Column(name = "IMAGE_PATH")
    private String imagePath;
    @Column(name = "AUDIO_PATH")
    private String audioPath;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "UPDATED_DATE")
    private Date updationDate;
    @Column(name = "REMARK")
    private String remarks;
    @Column(name = "MANAGER_COMMENTS")
    private String managerComments;
}
