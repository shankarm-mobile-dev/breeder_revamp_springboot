package com.suguna.breeder_revamp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SUG_MAI_GPPS_ITEM_CONSUMPTION", schema = "SUG")
public class SugMaiGppsItemConsumption {
    long DEVICE_ID;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gpps_item_consumption")
    @SequenceGenerator(sequenceName = "SUG_GPPS_ITEM_CONSUMPTION_S", allocationSize = 1, name = "id_seq_gpps_item_consumption")
    long TRANS_ID;
    Date TRANS_DATE;
    long BRANCH_ID;
    String TRANS_TYPE;
    long INVENTORY_ITEM_ID;
    String INVENTORY_ITEM_CODE;
    String ITEM_DESCRIPTION ;
    double STK_QTY;
    double QUANTITY ;
    String UOM;
    String POSTED_FLAG;
    Date ENTRY_CREATION_DATE;
    Date CREATION_DATE;
    String ERROR_MSG ;
    long INVENTORY_LOCATION_ID;
    double FOR_LTR_WATER  ;
    String ADVISED_BY ;
    String ISSUED_BY ;
}
